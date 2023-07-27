package com.nts.notice.db.repository;

import com.nts.notice.api.response.CommentRes;
import com.nts.notice.db.entity.Comment;

import java.util.List;

public interface CommentRepository {
    Comment findbyId(long commentId);
    void save(Comment comment);
    void delete(Comment comment);

    List<CommentRes> findByBoardId(long boardId , int page);
}
