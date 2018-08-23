package com.xhyan.zero.cloud.common.dto;

import java.util.List;
import lombok.Builder;
import lombok.Data;

/**
 * 通用分页对象
 *
 * @param <T>
 * @author xhyan
 */
@Data
@Builder
public class ZeroPageInfo<T> {

    /**
     * 数据总数
     */
    private long total;
    /**
     * 当前页数
     */
    private int page;
    /**
     * 数据列表
     */
    private List<T> data;
}
