package com.ming.netty.preminary.response;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.swing.plaf.SeparatorUI;


@Data
@EqualsAndHashCode(callSuper = true)
public class CommonResponse<T> extends BaseResponse {
    protected  T date;
    public CommonResponse(){
        super();
    }
    public CommonResponse(T date){
        super();
        this.date=date;
    }
}
