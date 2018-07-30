package com.xhyan.zero.cloud.account.model;

import java.util.Date;
import javax.persistence.*;
import lombok.Data;

/**
 * 短信验证码
 *
 * @author xhyan
 */
@Data
@Table(name = "verification_code")
public class VerificationCode extends BaseModel {
    /**
     * ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 账号绑定手机号
     */
    private String mobile;

    /**
     * 验证码类型
     */
    private Integer type;

    /**
     * 验证码
     */
    private String code;

    /**
     * 有效期
     */
    @Column(name = "expiry_date")
    private Date expiryDate;

    /**
     * 扩展字段
     */
    private String extend;

}