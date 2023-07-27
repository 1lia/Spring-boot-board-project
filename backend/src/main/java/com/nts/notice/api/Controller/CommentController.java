package com.nts.notice.api.Controller;

import com.nts.notice.api.Service.CommentService;
import com.nts.notice.api.request.BoardReq;
import com.nts.notice.api.request.CommentReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/comments")
public class CommentController {
    private CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

//   댓글작성
    @PostMapping
    public ResponseEntity<Long> insertBoard(@RequestBody CommentReq commentReq){
        try {
            commentService.insertComment(commentReq);
            return ResponseEntity.status(HttpStatus.OK).body(null);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}