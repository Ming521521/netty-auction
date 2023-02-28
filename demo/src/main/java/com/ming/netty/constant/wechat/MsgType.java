package com.ming.netty.constant.wechat;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum MsgType {
    Text("text"),
    Image("image"),
    Music("music"),
    Video("video"),
    Voice("voice"),
    Location("location"),
    Link("link"),
    Event("event"),
    ;
    private String msgType = "";

    @Override
    public String toString() {
        return this.msgType;
    }
}
