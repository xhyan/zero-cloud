package com.xhyan.zero.cloud.account.exception;

import lombok.Data;

/**
 * 系统操作异常
 *
 * @author xhyan
 */
@Data
public class OperationException extends RuntimeException {

    /**
     * 错误信息
     */
    String msg;
    /**
     * 错误码
     */
    String code;

    public OperationException(Throwable throwable) {
        super(throwable);
    }

    public OperationException(String code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }
}
