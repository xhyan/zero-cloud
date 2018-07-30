package com.xhyan.zero.cloud.account.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

/**
 * 账户绑定授权
 *
 * @author xhyan
 */
@Data
@Table(name = "account_bind")
public class AccountBind extends BaseModel {

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
     * 绑定/授权应用
     */
    private String application;

    /**
     * 绑定/授权应用凭证
     */
    private String credentials;

    /**
     * 扩展字段
     */
    private String extend;
}