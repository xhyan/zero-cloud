package com.xhyan.zero.cloud.account.model;

import javax.persistence.*;
import lombok.Data;

/**
 * 账户任务信息
 * @author xhyan
 */
@Data
@Table(name = "account_task")
public class AccountTask extends BaseModel {
    /**
     * ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 账户id
     */
    @Column(name = "account_id")
    private Long accountId;

    /**
     * 任务id
     */
    @Column(name = "task_id")
    private Long taskId;

    /**
     * 任务活力值
     */
    private Integer energy;

    /**
     * 扩展字段
     */
    private String extend;

}