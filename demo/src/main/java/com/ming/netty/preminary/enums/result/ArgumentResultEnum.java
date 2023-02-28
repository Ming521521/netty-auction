package com.ming.netty.preminary.enums.result;

import com.ming.netty.preminary.assertion.CommonExceptionAssert;
import com.mysql.cj.protocol.PacketReceivedTimeHolder;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ArgumentResultEnum implements CommonExceptionAssert {
    /**
     * 绑定参数校验异常
     */
    VALID_ERROR(6000, "参数校验异常"),

    /**
     * 某个参数不能为空
     */
    PARAM_NOTNULL(6001,"{0}不能为空"),

    /**
     * 绑定参数校验异常2
     */
    VALID_PARAM_ERROR(6002, "参数[%s]校验异常"),
    ;

    private int code;
    private String msg;

}
