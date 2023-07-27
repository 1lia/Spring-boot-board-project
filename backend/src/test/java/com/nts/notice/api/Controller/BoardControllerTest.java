package com.nts.notice.api.Controller;

import com.nts.notice.api.Service.BoardService;
import com.nts.notice.api.request.BoardReq;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
@DisplayName("게시판 테스트")
class BoardControllerTest {

    @Autowired
    private BoardService boardService;


    @Test
    @Transactional
    @DisplayName("게시판 작성")
    void selectAllContacts() {
        BoardReq boardReq = new BoardReq();
        boardReq.setTitle("테스트 제목");
        boardReq.setContent("테스트 내용");
        ArrayList<String> tags = new ArrayList<>();
        tags.add("태그1");
        tags.add("태그2");
        boardReq.setTags(tags);
        boardService.insertBoard(boardReq);
    }
}