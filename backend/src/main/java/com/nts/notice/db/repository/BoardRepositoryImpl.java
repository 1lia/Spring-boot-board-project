package com.nts.notice.db.repository;

import com.nts.notice.db.entity.Board;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

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
}
