package com.nts.notice.api.Controller;

import com.nts.notice.api.Service.BoardService;
import com.nts.notice.api.request.BoardReq;
import com.nts.notice.db.repository.BoardRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
@DisplayName("게시판 테스트")
class BoardControllerTest {

    @Autowired
    private BoardService boardService;
    @Autowired
    private BoardRepository boardRepository;


    @Test
    @Transactional
    @DisplayName("게시판 작성")
    void insertBoard() {
        BoardReq boardReq = new BoardReq();
        boardReq.setTitle("테스트 제목");
        boardReq.setContent("테스트 내용");
        ArrayList<String> tags = new ArrayList<>();
        tags.add("태그1");
        tags.add("태그2");
        boardReq.setTags(tags);
        boardService.insertBoard(boardReq);
    }
    
    @Test
    @Transactional
    @DisplayName("게시판 수정")
    void updateBoard() {
        BoardReq boardReq = new BoardReq();
        boardReq.setTitle("수정 제목");
        boardReq.setContent("수정 내용");
        ArrayList<String> tags = new ArrayList<>();
        tags.add("수정태그1");
        tags.add("수정태그2");
        tags.add("수정태그3");
        boardReq.setTags(tags);
        boardService.updateBoard(14 , boardReq);
    }

    @Test
    @Transactional
    @DisplayName("게시판 삭제")
    void deleteBoard() {
        boardService.deleteBoard(14 );
    }

    @Test
    @Transactional
    @DisplayName("게시판 목록 조회")
    void selectAllBoard() {
        Map<String, Object> map = new HashMap<>();
        map.put("type" , "해시태그");
        map.put("keyword" , "태그1");
        map.put("page" , 1);
        boardService.selectAllBoard(map);
    }

    @Test
    @Transactional
    @DisplayName("게시판 상세 조회")
    void selectDetailBoard() {
        boardService.selectDetailBoard(18);
    }
}