package com.nts.notice.db.repository;

import com.nts.notice.db.entity.Notice;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class NoticeRepositoryImpl implements NoticeRepository{
    private final EntityManager em;
    private final JPAQueryFactory query;

    @Autowired
    public NoticeRepositoryImpl(EntityManager em) {
        this.em = em;
        this.query = new JPAQueryFactory(em);
    }

    @Override
    public Notice findById(int noticeId) {
        return em.find(Notice.class , noticeId);
    }

    @Override
    public void save(Notice notice) {
        em.persist(notice);
    }

    @Override
    public void delete(Notice notice) {
        em.remove(notice);
    }
}
