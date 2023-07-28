package com.nts.notice.api.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class BoardRes {
    long boardId;
    String title;
    String name;
    LocalDateTime createTime;
    int commentCount;
    int hit;
    int likeCount;
}
