package com.nts.notice.db.repository;

import com.nts.notice.db.entity.Notice;

public interface NoticeRepository {
    Notice findById(int noticeId);
    void save(Notice notice);
    void delete(Notice notice);
}
