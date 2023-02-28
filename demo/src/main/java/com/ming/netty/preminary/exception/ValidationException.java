package com.ming.netty.preminary.exception;

import com.ming.netty.preminary.enums.IResponseEnum;

//参数格式校验异常
public class ValidationException extends BaseException {
    private static final long serialVersionUID = 1L;

    public ValidationException(IResponseEnum responseEnum, Object[] args, String message) {
        super(responseEnum, args, message);
    }

    public ValidationException(IResponseEnum responseEnum, Object[] args, String message, Throwable cause) {
        super(responseEnum, args, message, cause);
    }
}
