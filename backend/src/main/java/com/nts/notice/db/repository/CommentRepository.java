package com.nts.notice.db.repository;

import com.nts.notice.db.entity.Comment;

public interface CommentRepository {
    Comment findbyId(int commentId);
    void save(Comment comment);
    void delete(Comment comment);
}
