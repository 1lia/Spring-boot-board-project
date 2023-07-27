package com.nts.notice.db.repository;

import com.nts.notice.db.entity.Favorite;

public interface FavoriteRepository {
    Favorite findById(int favoriteId);
    void save(Favorite favorite);
    void delete(Favorite favorite);
}
