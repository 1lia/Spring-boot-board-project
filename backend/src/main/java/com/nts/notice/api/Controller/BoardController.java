package com.nts.notice.api.Controller;


import com.nts.notice.api.Service.BoardService;
import com.nts.notice.api.request.BoardReq;
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

    @PostMapping
    public ResponseEntity<Long> insertBoard(@RequestBody BoardReq boardReq){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(boardService.insertBoard(boardReq));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }



}
