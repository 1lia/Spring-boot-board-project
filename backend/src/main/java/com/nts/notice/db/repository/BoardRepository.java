package com.nts.notice.db.repository;

import com.nts.notice.api.response.BoardRes;
import com.nts.notice.db.entity.Board;

import java.util.List;
import java.util.Map;

public interface BoardRepository {
    Board findById(long boardId);
    void save(Board board);
    void delete(Board board);

    List<BoardRes> findAll(Map<String , Object> params);
}
