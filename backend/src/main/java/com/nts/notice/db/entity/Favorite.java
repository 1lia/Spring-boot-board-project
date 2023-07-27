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

    @Builder
    public Favorite(User user, Notice notice, Byte is_favorite) {
        this.user = user;
        this.notice = notice;
        this.is_favorite = is_favorite;
    }
}
