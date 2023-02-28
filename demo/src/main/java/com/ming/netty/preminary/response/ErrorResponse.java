package com.ming.netty.preminary.response;

//异常返回结果
public class ErrorResponse extends BaseResponse{
    public ErrorResponse(){

    }
    public ErrorResponse(int code,String message){
        super(code,message);
    }
}
