package com.xhyan.zero.cloud.account.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.persistence.Column;
import lombok.Data;

/**
 * Account DTO
 *
 * @author xhyan
 */
@ApiModel
@Data
public class AccountDTO{
    private Long id;
    /**
     * 账户绑定钱包余额
     */
    private Long balance;
    /**
     * 登录名
     */
    @ApiModelProperty(value = "登录名", required = true)
    private String loginName;

    /**
     * 登录密码(不可逆加密)
     */
    @ApiModelProperty(value = "登录密码", required = true)
    private String loginPwd;

    /**
     *  邮箱
     */
    @ApiModelProperty(value = "邮箱")
    private String email;

    /**
     * 活力值
     */
    @ApiModelProperty(value = "活力值")
    private Integer energy;


    /**
     * 是否开启挖矿:1 是,2 否
     */
    private Integer mining;

    /**
     * 账户状态：1 正常,4 注销
     */
    private Integer status;

    /**
     *  姓名
     */
    private String name;

    /**
     * 身份证号
     */
    @Column(name = "identity_card")
    private String identityCard;
}
