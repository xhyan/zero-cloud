package com.xhyan.zero.cloud.account.dto;

import lombok.Data;

/**
 * 任务信息DTO
 *
 * @author xhyan
 */
@Data
public class TaskDTO {

    private Long id;

    /**
     * 任务名称
     */
    private String name;

    /**
     * 任务类型:1 内部  2 外部
     */
    private Integer type;

    /**
     * 任务活力值
     */
    private Integer energy;

    /**
     * 任务状态：1 有效  -1 无效
     */
    private Integer status;
}
