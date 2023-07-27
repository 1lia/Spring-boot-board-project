package com.nts.notice.db.repository;

import com.nts.notice.db.entity.Tag;

public interface TagRepository {
    Tag findById(long tagId);
    void save(Tag tag);
    void delete(Tag tag);

    void deleteTagByBoardId(long BoardId);
}
