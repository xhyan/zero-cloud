package com.xhyan.zero.cloud.account.model;

import java.math.BigDecimal;
import javax.persistence.*;
import lombok.Data;

/**
 * 奖励发放
 *
 * @author xhyan
 */
@Data
@Table(name = "rewards_dispatch")
public class RewardsDispatch extends BaseModel {
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
     * 奖励类型
     */
    private Integer type;

    /**
     * 数量
     */
    private BigDecimal amount;

    /**
     * 扩展字段
     */
    private String extend;
}