package com.nts.notice.api.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class BoardDetailRes {
    String writer;
    String title;
    String content;
    String createTime;
    int commentCount;
    int hit;
    int likeCount;
    List<String> tags;
}
