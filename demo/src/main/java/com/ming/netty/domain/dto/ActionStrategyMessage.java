package com.ming.netty.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * 执行动作返回消息
 */
@Setter
@Getter
@AllArgsConstructor
public class ActionStrategyMessage {
    /**
     * 是否执行成功
     */
    private boolean success = false;
    /**
     * 返回码
     */
    private Integer errCode;
    /**
     * 返回消息
     */
    private String errMsg;
    /**
     * 返回数据
     */
    private Object data;
}
