package com.ming.netty.preminary.exception;

import com.ming.netty.preminary.enums.IResponseEnum;
import org.apache.catalina.StoreManager;
import sun.jvm.hotspot.runtime.aarch64.AARCH64CurrentFrameGuess;

public class BaseException  extends  RuntimeException{
    protected IResponseEnum responseEnum;
    protected  Object[] args;
    public BaseException(int code,String msg){
        super(msg);
        this.responseEnum=new IResponseEnum() {
            @Override
            public int getCode() {
                return code;
            }

            @Override
            public String getMsg() {
                return msg;
            }
        };
    }
    public BaseException(IResponseEnum responseEnum,Object[] args,String message ){
        super(message);
        this.responseEnum=responseEnum;
        this.args= args;
    }
    public BaseException(IResponseEnum responseEnum,Object[] args,String message,Throwable cause){
        super(message,cause);
        this.responseEnum=responseEnum;
        this.args=args;
    }
    public  static  String convertMessage(String message,Object... objects){
        if (message.indexOf("{}")>0){
            String s=message.replaceAll("\\{\\}","%s");
            String format=null;
            try {
                format=String.format(s,objects);
            }catch (Exception e){
                return message;
            }
            return format;
        }else {
            return message;
        }
    }
}
