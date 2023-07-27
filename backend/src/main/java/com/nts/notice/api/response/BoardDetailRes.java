package com.nts.notice.api.response;

import com.nts.notice.db.entity.Tag;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
public class BoardDetailRes {
    long userId;
    String name;
    String title;
    LocalDateTime createTime;
    int commentCount;
    int hit;
    int likeCount;
    List<String> tags;

    public BoardDetailRes(long userId, String name, String title, LocalDateTime createTime, int commentCount, int hit, int likeCount) {
        this.userId = userId;
        this.name = name;
        this.title = title;
        this.createTime = createTime;
        this.commentCount = commentCount;
        this.hit = hit;
        this.likeCount = likeCount;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }
}
