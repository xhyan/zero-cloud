package com.xhyan.zero.cloud.account.enums;

import lombok.Getter;

/**
 * 校验码枚举类
 *
 * @author xhyan
 */
@Getter
public enum VerificationCodeTypeEnum {
    /**
     * 注册验证码
     */
    SIGN_UP(1),
    /**
     * 登录验证码
     */
    SIGN_IN(2);

    private Integer type;

    VerificationCodeTypeEnum(Integer type) {
        this.type = type;
    }

    public VerificationCodeTypeEnum getByType(Integer type) {
        for (VerificationCodeTypeEnum typeEnum : values()) {
            if (typeEnum.getType().equals(type)) {
                return typeEnum;
            }
        }
        return null;
    }
}
