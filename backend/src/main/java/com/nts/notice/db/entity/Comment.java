package com.nts.notice.db.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
@NoArgsConstructor
@DynamicInsert
@DynamicUpdate
@Table(name = "comment")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long commentId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    @Column(name = "writer")
    private String writer;

    @Column(name = "password")
    private String password;

    @Column(name = "text", length = 255)
    private String text;

    @Column(name = "deleted")
    private Integer deleted;

    @Column(name = "create_time")
    private LocalDateTime createTime;

    @Column(name = "parent")
    private Integer parent;

    @Column(name = "depth")
    private Integer depth;

    @Builder
    public Comment(Board board, String writer, String password, String text, Integer deleted, Integer parent, Integer depth) {
        this.board = board;
        this.writer = writer;
        this.password = password;
        this.text = text;
        this.deleted = deleted;
        this.parent = parent;
        this.depth = depth;
    }
}