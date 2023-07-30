package com.nts.notice.db.repository;

import com.nts.notice.api.response.BoardDetailRes;
import com.nts.notice.api.response.BoardRes;
import com.nts.notice.db.entity.Board;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Map;

import static com.nts.notice.db.entity.QBoard.board;
import static com.nts.notice.db.entity.QTag.tag;

@Repository
public class BoardRepositoryImpl implements BoardRepository {
    private final EntityManager em;
    private final JPAQueryFactory query;

    @Autowired
    public BoardRepositoryImpl(EntityManager em) {
        this.em = em;
        this.query = new JPAQueryFactory(em);
    }

    @Override
    public Board findById(long boardId) {
        return em.find(Board.class , boardId);
    }

    @Override
    public void save(Board board) {
        em.persist(board);
    }

    @Override
    public void delete(Board board) {
        em.remove(board);
    }

    @Override
    public List<Board> findAll(Map<String, String> params) {
        StringBuilder stringBuilder = new StringBuilder();
        String type = params.get("type");
        String word = stringBuilder.append('%').append(params.get("word")).append('%').toString();
        if(type.equals("해시태그")){
            return query.select(board)
                    .distinct()
                    .from(board)
                    .join(board.tags , tag)
                    .where(tag.keyword.like(word))
                    .offset(Integer.parseInt((params.get("page"))) * 10)
                    .limit(10)
                    .orderBy(board.boardId.desc())
                    .fetch();
        } else{
            return query.select(board)
                    .from(board)
                    .where(selectFilter(type , word))
                    .offset(Integer.parseInt(params.get("page")) * 10)
                    .limit(10)
                    .orderBy(board.boardId.desc())
                    .fetch();
        }
    }

    @Override
    public long count(Map<String , String> params) {
        StringBuilder stringBuilder = new StringBuilder();
        String type = params.get("type");
        String word = stringBuilder.append('%').append(params.get("word")).append('%').toString();

        if(type.equals("해시태그")){
            return query.select(board.countDistinct())
                    .from(board)
                    .join(board.tags , tag)
                    .where(tag.keyword.like(word))
                    .fetchOne();
        } else{
            return query.select(board.count())
                    .from(board)
                    .where(selectFilter(type , word))
                    .fetchOne();
        }
    }

    private BooleanExpression selectFilter(String type , String word){
        if(!type.equals("")){
            if(type.equals("제목")){
                return board.title.like(word);
            } else if(type.equals("작성자")){
                return board.writer.like(word);
            } else if(type.equals("내용")){
                return board.content.like(word);
            }
        }
        return null;
    }

}
