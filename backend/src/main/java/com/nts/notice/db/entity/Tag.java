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
@Table(name = "tag")
public class Tag {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tag_id")
    private Long tagId;

    @ManyToOne
    @JoinColumn(name = "notice_id" , insertable = false , updatable = false)
    private Notice notice;

    @Column(name = "name", length = 20)
    private String name;

    @Builder
    public Tag(Notice notice, String name) {
        this.notice = notice;
        this.name = name;
    }
}