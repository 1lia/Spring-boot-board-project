package com.nts.notice.api.Service;

import com.nts.notice.api.request.BoardReq;
import com.nts.notice.api.request.LikeReq;
import com.nts.notice.api.response.CountRes;
import com.nts.notice.api.response.BoardDetailRes;
import com.nts.notice.api.response.BoardRes;

import java.util.List;
import java.util.Map;

public interface BoardService {
    long insertBoard(BoardReq boardReq);
    void updateBoard(long boardId , BoardReq boardReq);
    void deleteBoard(long boardId);
    List<BoardRes> selectAllBoard(Map<String, String> params);
    BoardDetailRes selectDetailBoard(long boardId);
    Integer checkPassword(long BoardId , String password);
    void updateBoardLike(LikeReq likeReq);
    CountRes checkCount(Map<String, String> params);
}
