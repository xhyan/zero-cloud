package com.xhyan.zero.cloud.account.dto.req;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * Zero Cloud 的请求对象
 *
 * @author xhyan
 */
@Data
@ApiModel
public class ZeroRequest<T> {
    private T t;
}
