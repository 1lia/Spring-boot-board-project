package com.nts.notice.api.Service;

import com.nts.notice.api.request.BoardReq;
import com.nts.notice.api.response.BoardDetailRes;
import com.nts.notice.api.response.BoardRes;
import com.nts.notice.db.entity.Board;
import com.nts.notice.db.entity.Tag;
import com.nts.notice.db.repository.BoardRepository;
import com.nts.notice.db.repository.TagRepository;
import com.nts.notice.db.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
@Transactional
public class BoardServiceImpl implements BoardService{
    private BoardRepository boardRepository;
    private UserRepository userRepository;
    private TagRepository tagRepository;

    @Autowired
    public BoardServiceImpl(BoardRepository boardRepository, UserRepository userRepository, TagRepository tagRepository) {
        this.boardRepository = boardRepository;
        this.userRepository = userRepository;
        this.tagRepository = tagRepository;
    }

    @Override
    public long insertBoard(BoardReq boardReq) {
        Board board = Board.builder()
                .title(boardReq.getTitle())
                .user(userRepository.findbyId(boardReq.getUserId()))
                .content(boardReq.getContent())
                .hit(0)
                .likeCount(0)
                .commentCount(0)
                .build();
        boardRepository.save(board);
        updateTag(board , boardReq.getTags());
        return board.getBoardId();
    }

    @Override
    public void updateBoard(long boardId, BoardReq boardReq) {
        Board board = boardRepository.findById(boardId);
        board.setTitle(boardReq.getTitle());
        board.setContent(boardReq.getContent());
        tagRepository.deleteTagByBoardId(board.getBoardId());
        updateTag(board , boardReq.getTags());
    }

    @Override
    public void deleteBoard(long boardId) {
        Board board = boardRepository.findById(boardId);
        tagRepository.deleteTagByBoardId(boardId);
        boardRepository.delete(board);
    }

    @Override
    public List<BoardRes> selectAllBoard(Map<String, Object> params) {
        return boardRepository.findAll(params);
    }

    @Override
    public BoardDetailRes selectDetailBoard(long boardId) {
        updateHit(boardId);
        BoardDetailRes boardDetailRes = boardRepository.findDetailById(boardId);
        boardDetailRes.setTags(tagRepository.findKeywordByBoardId(boardId));
        return boardDetailRes;
    }

    public void updateHit(long boardId){
        Board board = boardRepository.findById(boardId);
        board.setHit(board.getHit() + 1);
    }

    public void updateTag(Board board , List<String> tagString){
        List<Tag> tags = new ArrayList<>();
        for (String item : tagString) {
            tags.add(new Tag(board , item));
        }
        board.setTags(tags);
        boardRepository.save(board);
    }
}
