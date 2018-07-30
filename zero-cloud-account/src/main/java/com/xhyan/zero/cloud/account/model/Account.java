package com.xhyan.zero.cloud.account.model;

import com.xhyan.zero.cloud.account.dto.AccountDTO;
import com.xhyan.zero.copier.annotations.Copier;
import javax.persistence.*;
import lombok.Data;

/**
 * 账户信息
 * @author xhyan
 */
@Data
@Copier(mapClass = AccountDTO.class)
@Table(name = "account")
public class Account extends BaseModel {
    /**
     * ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 钱包ID
     */
    @Column(name = "wallet_id")
    private Long walletId;

    /**
     * 账号绑定手机号
     */
    private String mobile;

    /**
     * 登录名
     */
    @Column(name = "login_name")
    private String loginName;

    /**
     * 登录密码(不可逆加密)
     */
    @Column(name = "login_pwd")
    private String loginPwd;

    /**
     *  账号绑定邮箱
     */
    private String email;

    /**
     * 活力值
     */
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

    /**
     * 扩展字段
     */
    private String extend;

}