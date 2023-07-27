package com.nts.notice.api.Service;

import com.nts.notice.api.request.BoardReq;
import com.nts.notice.api.response.BoardDetailRes;
import com.nts.notice.api.response.BoardRes;

import java.util.List;
import java.util.Map;

public interface BoardService {
    long insertBoard(BoardReq boardReq);
    void updateBoard(long boardId , BoardReq boardReq);
    void deleteBoard(long boardId);
    List<BoardRes> selectAllBoard(Map<String, Object> params);
    BoardDetailRes selectDetailBoard(long boardId);
}
