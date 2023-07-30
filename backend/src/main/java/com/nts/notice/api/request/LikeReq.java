package com.nts.notice.api.request;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class LikeReq {
    long boardId;
    int like;
}
