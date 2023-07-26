package com.nts.notice.db.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@Getter @Setter
@DynamicInsert
@DynamicUpdate
@Table(name = "favorite")
public class Favorite {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "favorite_id")
    private Long favoriteId;

    @ManyToOne
    @JoinColumn(name = "user_id" , insertable = false , updatable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "notice_id" , insertable = false , updatable = false)
    private Notice notice;

    @Column(name = "is_favorite")
    private Byte is_favorite;
}
