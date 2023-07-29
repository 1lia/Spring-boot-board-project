package com.nts.notice.api.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
public class CountRes {
    long boardCount;
    long commentCount;
}
