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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "board" , cascade = CascadeType.PERSIST)
    private List<Tag> tags = new ArrayList<>();

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

    @Builder
    public Board(User user, List<Tag> tags, String title, String content, LocalDateTime createTime, Integer commentCount, Integer likeCount, Integer hit) {
        this.user = user;
        this.tags = tags;
        this.title = title;
        this.content = content;
        this.createTime = createTime;
        this.commentCount = commentCount;
        this.likeCount = likeCount;
        this.hit = hit;
    }
}
