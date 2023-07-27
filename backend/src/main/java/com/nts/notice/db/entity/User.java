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
@Table(name = "user")
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "id", length = 20)
    private String id;

    @Column(name = "password", length = 255)
    private String password;

    @Column(name = "name", length = 20)
    private String name;

    @Builder
    public User(String id, String password, String name) {
        this.id = id;
        this.password = password;
        this.name = name;
    }
}