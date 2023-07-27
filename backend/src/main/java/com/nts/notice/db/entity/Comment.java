package com.nts.notice.db.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@Getter @Setter
@NoArgsConstructor
@DynamicInsert
@DynamicUpdate
@Table(name = "comment")
public class Comment {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long commentId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    @Column(name = "text", length = 255)
    private String text;

    @Column(name = "deleted")
    private Byte deleted;

    @Column(name = "parent")
    private Integer parent;

    @Column(name = "depth")
    private Integer depth;

    @Builder
    public Comment(User user, Board board, String text, Byte deleted, Integer parent, Integer depth) {
        this.user = user;
        this.board = board;
        this.text = text;
        this.deleted = deleted;
        this.parent = parent;
        this.depth = depth;
    }
}