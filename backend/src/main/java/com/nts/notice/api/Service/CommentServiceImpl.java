package com.nts.notice.api.Service;

import com.nts.notice.api.request.CommentReq;
import com.nts.notice.api.response.CommentRes;
import com.nts.notice.db.entity.Board;
import com.nts.notice.db.entity.Comment;
import com.nts.notice.db.repository.BoardRepository;
import com.nts.notice.db.repository.CommentRepository;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class CommentServiceImpl implements CommentService{
    private CommentRepository commentRepository;
    private BoardRepository boardRepository;

    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository, BoardRepository boardRepository) {
        this.commentRepository = commentRepository;
        this.boardRepository = boardRepository;
    }

    @Override
    public void insertComment(CommentReq commentReq) {
        Board board = boardRepository.findById(commentReq.getBoardId());
        board.setCommentCount(board.getCommentCount() + 1);
        String password = BCrypt.hashpw(commentReq.getPassword(),BCrypt.gensalt());

        Comment comment = Comment.builder()
                .writer(commentReq.getWriter())
                .password(password)
                .board(board)
                .text(commentReq.getText())
                .deleted(0)
                .createTime(LocalDateTime.now())
                .build();
        commentRepository.save(comment);
    }

    @Override
    public void deleteComment(int CommentId) {
        Comment comment = commentRepository.findbyId(CommentId);
        comment.setDeleted(1);
        comment.setText("삭제된 댓글입니다.");
    }

    @Override
    public List<CommentRes> selectComment(long boardId, int page) {
        List<Comment> comments = commentRepository.findByBoardId(boardId , page);
        List<CommentRes> commentRes = new ArrayList<>();
        for (Comment comment : comments ) {
            commentRes.add(new CommentRes(comment.getCommentId() , comment.getWriter() , comment.getText() , comment.getCreateTime().format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM))));
        }
        return commentRes;
    }

    @Override
    public int checkPassword(long commentId, String password) {
        Comment comment = commentRepository.findbyId(commentId);
        if(BCrypt.checkpw(password , comment.getPassword())){
            return 1;
        } else{
            return 0;
        }
    }
}
