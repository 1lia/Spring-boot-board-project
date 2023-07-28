package com.nts.notice.api.Service;

import com.nts.notice.api.request.CommentReq;
import com.nts.notice.api.response.CommentRes;

import java.util.List;

public interface CommentService {
    void insertComment(CommentReq commentReq);
    void deleteComment(int CommentId);
    List<CommentRes> selectComment(long boardId , int page);

    int checkPassword(long commentId , String password);
}
