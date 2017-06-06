package com.xhyan.zero.cloud.account.dto.resp;

import lombok.Builder;
import lombok.Getter;

/**
 * 统一响应对象
 *
 * @author yanliwei
 */
@Builder
@Getter
public class ZeroResponse<T> {
    private Boolean isSuccess;
    private String errorMsg;
    private String errorCode;
    private T data;

    public static <T> ZeroResponse<T> success(T data) {
        return (ZeroResponse<T>) ZeroResponse.builder()
                .isSuccess(Boolean.TRUE)
                .data(data)
                .build();
    }

    public static ZeroResponse fail(String errorCode, String errorMsg) {
        return ZeroResponse.builder()
                .isSuccess(Boolean.FALSE)
                .errorCode(errorCode)
                .errorMsg(errorMsg)
                .build();
    }
}
