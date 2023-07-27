package com.nts.notice.db.repository;

import com.nts.notice.db.entity.Tag;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

import static com.nts.notice.db.entity.QTag.tag;

@Repository
public class TagRepositoryImpl implements TagRepository{
    private final EntityManager em;
    private final JPAQueryFactory query;

    @Autowired
    public TagRepositoryImpl(EntityManager em) {
        this.em = em;
        this.query = new JPAQueryFactory(em);
    }
    @Override
    public Tag findById(long tagId) {
        return em.find(Tag.class , tagId);
    }

    @Override
    public void save(Tag tag) {
        em.persist(tag);
    }

    @Override
    public void delete(Tag tag) {
        em.remove(tag);
    }

    @Override
    public void deleteTagByBoardId(long BoardId) {
        query.delete(tag).where(tag.board.boardId.eq(BoardId)).execute();
    }
}
