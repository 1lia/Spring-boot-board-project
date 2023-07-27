package com.nts.notice.api.Service;

import com.nts.notice.api.request.BoardReq;
import com.nts.notice.db.entity.Board;
import com.nts.notice.db.entity.Tag;
import com.nts.notice.db.repository.BoardRepository;
import com.nts.notice.db.repository.TagRepository;
import com.nts.notice.db.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class BoardServiceImpl implements BoardService{
    private BoardRepository boardRepository;
    private UserRepository userRepository;
    private TagRepository tagRepository;

    public BoardServiceImpl(BoardRepository boardRepository, UserRepository userRepository, TagRepository tagRepository) {
        this.boardRepository = boardRepository;
        this.userRepository = userRepository;
        this.tagRepository = tagRepository;
    }

    @Override
    public long insertBoard(BoardReq boardReq) {
//        List<Tag> tags = new ArrayList<>();

        Board board = Board.builder()
                .title(boardReq.getTitle())
                .user(userRepository.findbyId(boardReq.getUserId()))
                .content(boardReq.getContent())
                .hit(0)
                .likeCount(0)
                .commentCount(0)
//                .tags(tags)
                .build();
        boardRepository.save(board);
        System.out.println(board.getBoardId());

        for (String item : boardReq.getTags()) {
            Tag tag = new Tag(board , item);
            tagRepository.save(tag);
        }
        return board.getBoardId();
    }
}
