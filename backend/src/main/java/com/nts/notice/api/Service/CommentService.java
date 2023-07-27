package com.nts.notice.api.Service;

import com.nts.notice.api.request.CommentReq;

public interface CommentService {
    void insertComment(CommentReq commentReq);
    void deleteComment(int CommentId);
}
