package com.nts.notice.api.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class BoardReq {
    String writer;
    String password;
    String title;
    String content;
    List<String> tags;
}
