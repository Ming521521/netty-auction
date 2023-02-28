package com.ming.netty.preminary.exception;

import com.ming.netty.preminary.enums.IResponseEnum;

//参数错误异常
public class ArgumentException extends BaseException{
    public static final long serialVersionUId= 1L;
    public ArgumentException(IResponseEnum responseEnum,Object[]args,String message){
        super(responseEnum,args,message);
    }
    public ArgumentException(IResponseEnum responseEnum,Object[]args,String message,Throwable cause){
        super(responseEnum, args, message, cause);
    }
}
