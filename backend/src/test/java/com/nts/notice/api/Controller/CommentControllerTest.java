package com.nts.notice.api.Controller;

import com.nts.notice.api.Service.CommentService;
import com.nts.notice.api.request.CommentReq;
import com.nts.notice.db.repository.CommentRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Slf4j
@DisplayName("댓글 테스트")
class CommentControllerTest {
    @Autowired
    private CommentService commentService;
    @Autowired
    private CommentRepository commentRepository;

    @Test
    @DisplayName("댓글 쓰기")
    @Transactional
    void insertComment() {
        CommentReq commentReq = new CommentReq();
        commentReq.setBoardId(13);
        commentReq.setUserId(1);
        commentReq.setComment("댓글 내용입니다");
        commentService.insertComment(commentReq);
    }

    @Test
    @DisplayName("댓글 삭제")
    @Transactional
    void deleteComment() {
        commentService.deleteComment(2);
    }
}