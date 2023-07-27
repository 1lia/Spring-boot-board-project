package com.nts.notice.api.Service;

import com.nts.notice.api.request.CommentReq;
import com.nts.notice.db.entity.Comment;
import com.nts.notice.db.repository.BoardRepository;
import com.nts.notice.db.repository.CommentRepository;
import com.nts.notice.db.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService{
    private CommentRepository commentRepository;
    private UserRepository userRepository;
    private BoardRepository boardRepository;

    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository, UserRepository userRepository, BoardRepository boardRepository) {
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
        this.boardRepository = boardRepository;
    }

    @Override
    public void insertComment(CommentReq commentReq) {
        Comment comment = Comment.builder()
                .user(userRepository.findbyId(commentReq.getUserId()))
                .board(boardRepository.findById(commentReq.getBoardId()))
                .comment(commentReq.getComment())
                .build();
        commentRepository.save(comment);
    }
}
