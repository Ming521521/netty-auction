package com.ming.netty.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    FriendSearchError(80001,"搜索用户错误"),
    FriendAddRequestError(80002,"添加用户失败")
    ;
    private int code;
    private String msg;
}
