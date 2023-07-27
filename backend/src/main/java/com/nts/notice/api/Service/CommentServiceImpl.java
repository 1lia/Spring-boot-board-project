package com.nts.notice.api.Service;

import com.nts.notice.api.request.CommentReq;
import com.nts.notice.api.response.CommentRes;
import com.nts.notice.db.entity.Comment;
import com.nts.notice.db.repository.BoardRepository;
import com.nts.notice.db.repository.CommentRepository;
import com.nts.notice.db.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
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
                .text(commentReq.getComment())
                .build();
        commentRepository.save(comment);
    }

    @Override
    public void deleteComment(int CommentId) {
        Comment comment = commentRepository.findbyId(CommentId);
        comment.setText("삭제된 댓글입니다.");
    }

    @Override
    public List<CommentRes> selectComment(long boardId, int page) {
        return commentRepository.findByBoardId(boardId , page);
    }
}
