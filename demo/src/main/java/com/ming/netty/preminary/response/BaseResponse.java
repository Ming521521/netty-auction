package com.ming.netty.preminary.response;

import com.ming.netty.preminary.enums.IResponseEnum;
import com.ming.netty.preminary.enums.result.CommonResultEnum;
import lombok.Data;

//基础返回结果
@Data
public class BaseResponse {
    protected  int code;
    protected  String message;
    public BaseResponse() {
        // 默认创建成功的回应
        this(CommonResultEnum.SUCCESS);
    }

    public BaseResponse(IResponseEnum responseEnum) {
        this(responseEnum.getCode(), responseEnum.getMsg());
    }

    public BaseResponse(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
