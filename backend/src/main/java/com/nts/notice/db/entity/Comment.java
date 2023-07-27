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

    @ManyToOne
    @JoinColumn(name = "user_id" , insertable = false , updatable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "notice_id" , insertable = false , updatable = false)
    private Notice notice;

    @Column(name = "comment", length = 255)
    private String comment;

    @Column(name = "deleted")
    private Byte deleted;

    @Column(name = "parent")
    private Integer parent;

    @Column(name = "depth")
    private Integer depth;

    @Builder
    public Comment(User user, Notice notice, String comment, Byte deleted, Integer parent, Integer depth) {
        this.user = user;
        this.notice = notice;
        this.comment = comment;
        this.deleted = deleted;
        this.parent = parent;
        this.depth = depth;
    }
}