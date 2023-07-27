package com.nts.notice.db.repository;

import com.nts.notice.db.entity.Favorite;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class FavoriteRepositoryImpl implements FavoriteRepository{
    private final EntityManager em;
    private final JPAQueryFactory query;

    @Autowired
    public FavoriteRepositoryImpl(EntityManager em) {
        this.em = em;
        this.query = new JPAQueryFactory(em);
    }

    @Override
    public Favorite findById(long favoriteId) {
        return em.find(Favorite.class , favoriteId);
    }

    @Override
    public void save(Favorite favorite) {
        em.persist(favorite);
    }

    @Override
    public void delete(Favorite favorite) {
        em.remove(favorite);
    }
}
