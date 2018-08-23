package com.xhyan.zero.cloud.common.dto;

import lombok.Data;

/**
 * 分页查询请求
 * @author xhyan
 */
@Data
public class ZeroPageReq {

    /**
     * 查询的页数
     */
    private int pageNumber;
    /**
     * 每页大小
     */
    private int pageSize;

}
