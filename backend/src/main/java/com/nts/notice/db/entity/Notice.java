package com.nts.notice.db.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
@DynamicInsert
@DynamicUpdate
@Table(name = "notice")
public class Notice {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notice_id")
    private Long noticeId;

    @ManyToOne
    @JoinColumn(name = "user_id" , insertable = false , updatable = false)
    private User user;

    @Column(name = "title")
    private String title;

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
}
