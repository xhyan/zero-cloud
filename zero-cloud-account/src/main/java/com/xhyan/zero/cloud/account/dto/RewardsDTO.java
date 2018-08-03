package com.xhyan.zero.cloud.account.dto;

import java.math.BigDecimal;
import lombok.Data;

/**
 * 奖励发放DTO
 *
 * @author
 */
@Data
public class RewardsDTO {

    private Long id;

    /**
     * 账户id
     */
    private Long accountId;

    /**
     * 奖励类型
     */
    private Integer type;

    /**
     * 数量
     */
    private BigDecimal amount;
}
