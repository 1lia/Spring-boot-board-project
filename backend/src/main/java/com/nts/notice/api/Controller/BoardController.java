package com.nts.notice.api.Controller;


import com.nts.notice.api.Service.BoardService;
import com.nts.notice.api.request.BoardReq;
import com.nts.notice.api.response.BoardDetailRes;
import com.nts.notice.api.response.BoardRes;
import com.nts.notice.api.response.CommentRes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/boards")
public class BoardController {
    private BoardService boardService;

    @Autowired
    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }
//  게시판 작성
    @PostMapping
    public ResponseEntity<Long> insertBoard(@RequestBody BoardReq boardReq){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(boardService.insertBoard(boardReq));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
//  게시판 수정
    @PutMapping("/{boardId}")
    public ResponseEntity<Void> updateBoard(@PathVariable long boardId , @RequestBody BoardReq boardReq){
        try {
            boardService.updateBoard(boardId , boardReq);
            return ResponseEntity.status(HttpStatus.OK).body(null);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
//  게시판 삭제
    @DeleteMapping("/{boardId}")
    public ResponseEntity<Void> deleteBoard(@PathVariable long boardId){
        try {
            boardService.deleteBoard(boardId);
            return ResponseEntity.status(HttpStatus.OK).body(null);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
//  게시판 목록 조회
    @GetMapping
    public ResponseEntity<List<BoardRes>> selectAllBoard(@RequestParam Map<String, String> params){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(boardService.selectAllBoard(params));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

//  게시판 상세 조회
    @GetMapping("/detail")
    public ResponseEntity<BoardDetailRes> selectAllBoard(@RequestParam long boardId){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(boardService.selectDetailBoard(boardId));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

//   비밀번호 확인
    @GetMapping("/check")
    public ResponseEntity<Integer> checkPassword(@RequestParam long boardId , @RequestParam String password){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(boardService.checkPassword(boardId , password));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
