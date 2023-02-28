package com.ming.netty.preminary.enums.result;

import com.ming.netty.preminary.assertion.CommonExceptionAssert;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OperationFailureResultEnum implements CommonExceptionAssert {

    USER_SAVE_ERROR(7000,"新增用户[%s]失败"),
    USER_NOT_FOUND(7001,"用户[%s]不存在"),
    UPDATE_DATA_FAIL(7004,"修改[%s]数据失败"),

    FRIEND_ADD_REQUEST_EXIST(80002,"您已发出过添加请求，请勿重复发送"),
    ADD_DATA_FAIL(7005,"新增数据失败")
    ;
    private int code;
    private String msg;
}
