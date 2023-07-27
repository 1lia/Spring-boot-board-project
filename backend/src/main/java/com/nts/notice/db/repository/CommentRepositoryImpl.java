package com.nts.notice.db.repository;

import com.nts.notice.db.entity.Comment;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class CommentRepositoryImpl implements CommentRepository{
    EntityManager em;
    JPAQueryFactory query;

    public CommentRepositoryImpl(EntityManager em) {
        this.em = em;
        this.query = new JPAQueryFactory(em);
    }

    @Override
    public Comment findbyId(int commentId) {
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
}
