package com.xhyan.zero.cloud.common.enums;

import lombok.Getter;

/**
 * 错误码定义枚举类
 *
 * @author xhyan
 */
@Getter
public enum ErrorCodeEnum {
    /**
     * 枚举定义
     */
    ERROR_50001(50001, "短信验证码错误"),
    ERROR_70001(70001, "账户信息不存在");
    /**
     * 错误码
     */
    private Integer code;
    /**
     * 错误信息
     */
    private String msg;

    ErrorCodeEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
