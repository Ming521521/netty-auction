package com.ming.netty.constant.message;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MessageSign {
    Consign(0,"未签收，未读"),

    sign(1,"已签收，已读")
            ;
    private Integer id;

    private String desc;
}
