package com.nts.notice.db.repository;

import com.nts.notice.api.response.BoardRes;
import com.nts.notice.db.entity.Board;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.hibernate.criterion.Projection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Map;

import static com.nts.notice.db.entity.QBoard.board;
import static com.nts.notice.db.entity.QTag.tag;
import static com.nts.notice.db.entity.QUser.user;

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
    public List<BoardRes> findAll(Map<String, Object> params) {
        StringBuilder stringBuilder = new StringBuilder();
        String type = String.valueOf(params.get("type"));
        String keyword = stringBuilder.append('%').append(String.valueOf(params.get("keyword"))).append('%').toString();
        if(type.equals("해시태그")){
            return query.select(Projections.constructor(BoardRes.class,
                            board.boardId,
                            board.title,
                            user.name,
                            board.createTime,
                            board.commentCount,
                            board.hit,
                            board.likeCount
                    ))
                    .distinct()
                    .from(board)
                    .join(board.user , user)
                    .join(board.tags , tag)
                    .where(tag.name.like(keyword))
                    .offset(Integer.parseInt(String.valueOf(params.get("page"))))
                    .limit(20)
                    .orderBy(board.createTime.desc())
                    .fetch();
        } else{
            return query.select(Projections.constructor(BoardRes.class,
                            board.boardId,
                            board.title,
                            user.name,
                            board.createTime,
                            board.commentCount,
                            board.hit,
                            board.likeCount
                    ))
                    .from(board)
                    .join(board.user , user)
                    .where(selectFilter(type , keyword))
                    .offset(Integer.parseInt(String.valueOf(params.get("page"))))
                    .limit(20)
                    .orderBy(board.createTime.desc())
                    .fetch();
        }
    }

    private BooleanExpression selectFilter(String type , String keyword){
        if(!type.equals("")){
            if(type.equals("제목")){
                return board.title.like(keyword);
            } else if(type.equals("작성자")){
                return user.name.like(keyword);
            } else if(type.equals("내용")){
                return board.content.like(keyword);
            }
        }
        return null;
    }

}
