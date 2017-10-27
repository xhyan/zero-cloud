package com.xhyan.zero.cloud.account.model;

import com.xhyan.zero.cloud.account.converter.annotations.Convert;
import com.xhyan.zero.cloud.account.converter.annotations.Rule;
import com.xhyan.zero.cloud.account.dto.AccountDTO;
import lombok.Data;

import javax.persistence.*;

@Data
@Convert(target = AccountDTO.class)
@Table(name = "account")
public class Account extends BaseModel {

    /**
     * ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * 登录名
     */
    @Column(name = "login_name")
    private String loginName;

    /**
     * 登录密码(不可逆加密)
     */
    @Rule(ignore = true)
    @Column(name = "login_pwd")
    private String loginPwd;

    /**
     * 交易密码(不可逆加密)
     */
    @Rule(ignore = true)
    @Column(name = "trade_pwd")
    private String tradePwd;

    /**
     *  账号绑定邮箱
     */
    private String email;

    /**
     *  账号绑定备用邮箱
     */
    @Column(name = "email_backup")
    private String emailBackup;

    /**
     * 账号绑定手机号
     */
    private String mobile;

    /**
     * 账户类型(1个人账户；2企业账户)
     */
    private Integer type;

    /**
     * 账户状态：1正常,2受限,3冻结,4注销
     */
    private Integer status;

    /**
     * 扩展字段
     */
    private String extend;
}