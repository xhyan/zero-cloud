package com.xhyan.zero.member.dto;

import lombok.Data;

/**
 * 会员信息DTO
 *
 * @author xhyan
 */
@Data
public class MemberDTO {

    /**
     * ID
     */
    private Long id;

    /**
     * 账户id
     */
    private Long accountId;

    /**
     * 会员等级
     */
    private Integer level;

    /**
     * 会员积分
     */
    private Long points;

    /**
     * 会员可用积分
     */
    private Long availablePoints;

    /**
     * 会员卡号
     */
    private String number;

    /**
     * 会员状态：1正常,4注销
     */
    private Integer status;

}
