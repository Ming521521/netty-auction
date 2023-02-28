package com.ming.netty.preminary.assertion;

import com.ming.netty.preminary.enums.IResponseEnum;
import com.ming.netty.preminary.exception.ArgumentException;
import com.ming.netty.preminary.exception.BaseException;

import java.text.MessageFormat;

public interface ArgumentExceptionAssert extends IResponseEnum,Assert {
    @Override
    default BaseException newException(Object... args){
        String msg= MessageFormat.format(this.getMsg(),args);
        return  new ArgumentException(this,args,msg);
    }

    @Override
    default BaseException newException(Throwable t, Object... args){
        String msg= MessageFormat.format(this.getMsg(),args);
        return  new ArgumentException(this,args,msg,t);
    }
}
