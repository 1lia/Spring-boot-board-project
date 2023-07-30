package com.nts.notice.api.Service;

import com.nts.notice.api.request.BoardReq;
import com.nts.notice.api.request.LikeReq;
import com.nts.notice.api.response.CountRes;
import com.nts.notice.api.response.BoardDetailRes;
import com.nts.notice.api.response.BoardRes;
import com.nts.notice.db.entity.Board;
import com.nts.notice.db.entity.Tag;
import com.nts.notice.db.repository.BoardRepository;
import com.nts.notice.db.repository.CommentRepository;
import com.nts.notice.db.repository.TagRepository;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class BoardServiceImpl implements BoardService{
    private BoardRepository boardRepository;
    private CommentRepository commentRepository;
    private TagRepository tagRepository;

    @Autowired
    public BoardServiceImpl(BoardRepository boardRepository, CommentRepository commentRepository, TagRepository tagRepository) {
        this.boardRepository = boardRepository;
        this.commentRepository = commentRepository;
        this.tagRepository = tagRepository;
    }

    @Override
    public long insertBoard(BoardReq boardReq) {
//       비밀번호 암호화
        String password = BCrypt.hashpw(boardReq.getPassword(),BCrypt.gensalt());

        Board board = Board.builder()
                .writer(boardReq.getWriter())
                .password(password)
                .title(boardReq.getTitle())
                .content(boardReq.getContent())
                .hit(0)
                .likeCount(0)
                .commentCount(0)
                .createTime(LocalDateTime.now())
                .build();
        boardRepository.save(board);
        updateTag(board , boardReq.getTags());
        return board.getBoardId();
    }

    @Override
    public void updateBoard(long boardId, BoardReq boardReq) {
        Board board = boardRepository.findById(boardId);
        board.setWriter(boardReq.getWriter());
        board.setTitle(boardReq.getTitle());
        board.setContent(boardReq.getContent());
        tagRepository.deleteTagByBoardId(boardId);
        updateTag(board , boardReq.getTags());
    }

    @Override
    public void deleteBoard(long boardId) {
        Board board = boardRepository.findById(boardId);
        tagRepository.deleteTagByBoardId(boardId);
        boardRepository.delete(board);
    }

    @Override
    public List<BoardRes> selectAllBoard(Map<String, String> params) {
        List<Board> boards = boardRepository.findAll(params);
        List<BoardRes> boardRes = new ArrayList<>();
        LocalDateTime now = LocalDateTime.now().minusDays(3);
        for (Board board : boards) {
            String time = null;
            LocalDateTime createtime = board.getCreateTime();
            if(createtime.isAfter(now)) {
                time = createtime.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)) + "  [now]";
            } else{
                time = createtime.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM));
            }
            boardRes.add(new BoardRes(
                    board.getBoardId(),
                    board.getTitle(),
                    board.getWriter(),
                    time,
                    board.getCommentCount(),
                    board.getHit(),
                    board.getLikeCount()
                    ));
        }
        return boardRes;
    }

    @Override
    public BoardDetailRes selectDetailBoard(long boardId) {
        Board board = boardRepository.findById(boardId);
        board.setHit(board.getHit() + 1);
        List<String> tags = tagRepository.findKeywordByBoardId(boardId);

        BoardDetailRes boardDetailRes = new BoardDetailRes(
                board.getWriter(),
                board.getTitle(),
                board.getContent(),
                board.getCreateTime().format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)),
                board.getCommentCount(),
                board.getHit(),
                board.getLikeCount(),
                tags
        );
        return boardDetailRes;
    }

    @Override
    public Integer checkPassword(long boardId , String password) {
        Board board = boardRepository.findById(boardId);
        if(BCrypt.checkpw(password , board.getPassword())){
            return 1;
        } else{
            return 0;
        }
    }

    @Override
    public void updateBoardLike(LikeReq likeReq) {
        System.out.println(likeReq.getBoardId() + likeReq.getLike());
        Board board = boardRepository.findById(likeReq.getBoardId());
        board.setLikeCount(board.getLikeCount() + likeReq.getLike());
    }

    @Override
    public CountRes checkCount(Map<String, String> params) {
        long boardCount = boardRepository.count(params);
        long commentCount = commentRepository.count(params);
        return new CountRes(boardCount ,  commentCount);
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
