package com.nts.notice.api.request;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CommentReq {
    long boardId;
    String writer;
    String password;
    String text;
}
