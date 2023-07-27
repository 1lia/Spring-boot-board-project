package com.nts.notice.db.repository;

import com.nts.notice.db.entity.Board;

public interface BoardRepository {
    Board findById(long boardId);
    void save(Board board);
    void delete(Board board);
}
