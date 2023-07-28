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
    String writer;
    String title;
    LocalDateTime createTime;
    int commentCount;
    int hit;
    int likeCount;
    List<String> tags;
}
