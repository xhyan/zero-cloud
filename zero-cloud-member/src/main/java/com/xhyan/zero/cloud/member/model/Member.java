package com.xhyan.zero.cloud.member.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

/**
 * 会员信息数据库实体
 *
 * @author xhyan
 */
@Data
@Table(name = "member")
public class Member extends BaseModel {

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
    @Column(name = "available_points")
    private Long availablePoints;

    /**
     * 会员卡号
     */
    private String number;

    /**
     * 会员状态：1正常,4注销
     */
    private Integer status;

    /**
     * 扩展字段
     */
    private String extend;
}