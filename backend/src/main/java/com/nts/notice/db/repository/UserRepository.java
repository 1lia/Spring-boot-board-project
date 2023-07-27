package com.nts.notice.db.repository;

import com.nts.notice.db.entity.User;
import com.querydsl.jpa.impl.JPAQueryFactory;

public interface UserRepository {
    User findbyId(int userId);
    void save(User user);
    void delete(User user);

}
