package com.nts.notice.db.repository;

import com.nts.notice.db.entity.Notice;
import com.nts.notice.db.entity.Tag;

public interface TagRepository {
    Tag findById(int tagId);
    void save(Tag tag);
    void delete(Tag tag);
}
