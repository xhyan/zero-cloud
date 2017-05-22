package com.xhyan.zero.cloud.account.model;

import javax.persistence.*;

@Table(name = "account")
public class Account extends BaseModel {
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
     * 交易密码(不可逆加密)
     */
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

    /**
     * 获取登录名
     *
     * @return login_name - 登录名
     */
    public String getLoginName() {
        return loginName;
    }

    /**
     * 设置登录名
     *
     * @param loginName 登录名
     */
    public void setLoginName(String loginName) {
        this.loginName = loginName == null ? null : loginName.trim();
    }

    /**
     * 获取登录密码(不可逆加密)
     *
     * @return login_pwd - 登录密码(不可逆加密)
     */
    public String getLoginPwd() {
        return loginPwd;
    }

    /**
     * 设置登录密码(不可逆加密)
     *
     * @param loginPwd 登录密码(不可逆加密)
     */
    public void setLoginPwd(String loginPwd) {
        this.loginPwd = loginPwd == null ? null : loginPwd.trim();
    }

    /**
     * 获取交易密码(不可逆加密)
     *
     * @return trade_pwd - 交易密码(不可逆加密)
     */
    public String getTradePwd() {
        return tradePwd;
    }

    /**
     * 设置交易密码(不可逆加密)
     *
     * @param tradePwd 交易密码(不可逆加密)
     */
    public void setTradePwd(String tradePwd) {
        this.tradePwd = tradePwd == null ? null : tradePwd.trim();
    }

    /**
     * 获取 账号绑定邮箱
     *
     * @return email -  账号绑定邮箱
     */
    public String getEmail() {
        return email;
    }

    /**
     * 设置 账号绑定邮箱
     *
     * @param email  账号绑定邮箱
     */
    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    /**
     * 获取 账号绑定备用邮箱
     *
     * @return email_backup -  账号绑定备用邮箱
     */
    public String getEmailBackup() {
        return emailBackup;
    }

    /**
     * 设置 账号绑定备用邮箱
     *
     * @param emailBackup  账号绑定备用邮箱
     */
    public void setEmailBackup(String emailBackup) {
        this.emailBackup = emailBackup == null ? null : emailBackup.trim();
    }

    /**
     * 获取账号绑定手机号
     *
     * @return mobile - 账号绑定手机号
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * 设置账号绑定手机号
     *
     * @param mobile 账号绑定手机号
     */
    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    /**
     * 获取账户类型(1个人账户；2企业账户)
     *
     * @return type - 账户类型(1个人账户；2企业账户)
     */
    public Integer getType() {
        return type;
    }

    /**
     * 设置账户类型(1个人账户；2企业账户)
     *
     * @param type 账户类型(1个人账户；2企业账户)
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * 获取账户状态：1正常,2受限,3冻结,4注销
     *
     * @return status - 账户状态：1正常,2受限,3冻结,4注销
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置账户状态：1正常,2受限,3冻结,4注销
     *
     * @param status 账户状态：1正常,2受限,3冻结,4注销
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取扩展字段
     *
     * @return extend - 扩展字段
     */
    public String getExtend() {
        return extend;
    }

    /**
     * 设置扩展字段
     *
     * @param extend 扩展字段
     */
    public void setExtend(String extend) {
        this.extend = extend == null ? null : extend.trim();
    }
}