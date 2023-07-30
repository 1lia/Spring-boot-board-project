package com.nts.notice.db.repository;

import com.nts.notice.db.entity.Comment;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Map;

import static com.nts.notice.db.entity.QBoard.board;
import static com.nts.notice.db.entity.QComment.comment;
import static com.nts.notice.db.entity.QTag.tag;

@Repository
public class CommentRepositoryImpl implements CommentRepository{
    EntityManager em;
    JPAQueryFactory query;

    public CommentRepositoryImpl(EntityManager em) {
        this.em = em;
        this.query = new JPAQueryFactory(em);
    }

    @Override
    public Comment findbyId(long commentId) {
        return em.find(Comment.class , commentId);
    }

    @Override
    public void save(Comment comment) {
        em.persist(comment);
    }

    @Override
    public void delete(Comment comment) {
        em.remove(comment);
    }

    @Override
    public List<Comment> findByBoardId(long boardId, int page) {
        return query.select(comment)
                .from(comment)
                .join(comment.board, board)
                .where(board.boardId.eq(boardId))
                .offset(page * 5)
                .limit(5)
                .orderBy(comment.commentId.desc())
                .fetch();
    }

    @Override
    public long count(Map<String , String> params) {
        StringBuilder stringBuilder = new StringBuilder();
        String type = params.get("type");
        String word = stringBuilder.append('%').append(params.get("word")).append('%').toString();

        if(type.equals("해시태그")){
            return query.select(comment.countDistinct())
                    .from(comment)
                    .join(comment.board , board)
                    .join(board.tags , tag)
                    .where(tag.keyword.like(word))
                    .fetchOne();
        } else{
            return query.select(comment.count())
                    .from(comment)
                    .join(comment.board , board)
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
