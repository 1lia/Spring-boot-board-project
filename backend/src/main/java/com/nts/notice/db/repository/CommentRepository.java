package com.nts.notice.db.repository;

import com.nts.notice.api.response.CommentRes;
import com.nts.notice.db.entity.Comment;

import java.util.List;
import java.util.Map;

public interface CommentRepository {
    Comment findbyId(long commentId);
    void save(Comment comment);
    void delete(Comment comment);
    List<Comment> findByBoardId(long boardId , int page);
    long count(Map<String , String> params);
}
