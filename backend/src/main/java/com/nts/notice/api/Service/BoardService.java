package com.nts.notice.api.Service;

import com.nts.notice.api.request.BoardReq;

public interface BoardService {
    long insertBoard(BoardReq boardReq);
}
