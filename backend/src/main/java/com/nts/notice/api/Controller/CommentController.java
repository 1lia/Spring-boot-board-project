package com.nts.notice.api.Controller;

import com.nts.notice.api.Service.CommentService;
import com.nts.notice.api.request.CommentReq;
import com.nts.notice.api.response.CommentRes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<Void> insertComment(@RequestBody CommentReq commentReq){
        try {
            commentService.insertComment(commentReq);
            return ResponseEntity.status(HttpStatus.OK).body(null);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
//  댓글삭제
    @DeleteMapping("/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable int commentId){
        try {
            commentService.deleteComment(commentId);
            return ResponseEntity.status(HttpStatus.OK).body(null);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

//   댓글 조회
    @GetMapping("")
    public ResponseEntity<List<CommentRes>> selectComment(@RequestParam long boardId , @RequestParam int page){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(commentService.selectComment(boardId , page));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
