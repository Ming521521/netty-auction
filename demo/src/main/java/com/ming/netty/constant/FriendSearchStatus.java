package com.ming.netty.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum FriendSearchStatus implements  BaseEnum{
    USER_NOT_EXIST(0,"用户不存在"),
    NOT_YOURSELF(1,"查找用户不能是自己"),
    ALREADY_FRIENDS(2,"已经是好友了")
            ;
    private int code;

    private String msg;
}
