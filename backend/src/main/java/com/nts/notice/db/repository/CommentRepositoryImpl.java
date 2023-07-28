package com.nts.notice.db.repository;

import com.nts.notice.db.entity.Comment;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

import static com.nts.notice.db.entity.QBoard.board;
import static com.nts.notice.db.entity.QComment.comment;

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
                .orderBy(comment.commentId.desc())
                .fetch();
    }
}
