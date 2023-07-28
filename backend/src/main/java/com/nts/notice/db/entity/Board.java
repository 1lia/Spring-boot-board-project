package com.nts.notice.db.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor
@DynamicInsert
@DynamicUpdate
@Table(name = "board")
public class Board {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id")
    private Long boardId;

    @OneToMany(mappedBy = "board" , cascade = CascadeType.PERSIST)
    private List<Tag> tags = new ArrayList<>();

    @Column(name = "title")
    private String title;

    @Column(name = "writer")
    private String writer;

    @Column(name = "password")
    private String password;

    @Column(name = "content", length = 4000)
    private String content;

    @Column(name = "create_time")
    private LocalDateTime createTime;

    @Column(name = "comment_cnt")
    private Integer commentCount;

    @Column(name = "like_cnt")
    private Integer likeCount;

    @Column(name = "hit")
    private Integer hit;

    @Builder
    public Board(List<Tag> tags, String title, String writer, String password, String content, LocalDateTime createTime, Integer commentCount, Integer likeCount, Integer hit) {
        this.tags = tags;
        this.title = title;
        this.writer = writer;
        this.password = password;
        this.content = content;
        this.createTime = createTime;
        this.commentCount = commentCount;
        this.likeCount = likeCount;
        this.hit = hit;
    }
}
