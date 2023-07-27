package com.nts.notice.api.request;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CommentReq {
    long userId;
    long boardId;
    String comment;
}
