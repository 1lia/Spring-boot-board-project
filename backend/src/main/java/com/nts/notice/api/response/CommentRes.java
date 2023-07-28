package com.nts.notice.api.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class CommentRes {
    String writer;
    String text;
    LocalDateTime createTime;
}
