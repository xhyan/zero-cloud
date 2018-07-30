package com.xhyan.zero.cloud.account.service;

import com.xhyan.zero.cloud.account.enums.VerificationCodeTypeEnum;
import com.xhyan.zero.cloud.account.mapper.VerificationCodeMapper;
import com.xhyan.zero.cloud.account.model.VerificationCode;
import java.util.Date;
import java.util.Optional;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

/**
 * 短信相关服务，用于发送短信验证码、通知等
 *
 * @author xhyan
 */
@Service
public class SmsService {

    @Autowired
    private VerificationCodeMapper verificationCodeMapper;

    /**
     * 发送注册验证码
     */
    public void sendVerificationCode(String mobile, VerificationCodeTypeEnum type) {
        VerificationCode code = new VerificationCode();
        code.setCode("123456");
        code.setMobile(mobile);
        code.setType(type.getType());
        //有效期10分钟
        code.setExpiryDate(DateUtils.addMinutes(new Date(), 10));
        verificationCodeMapper.insertSelective(code);
    }

    public boolean verify(String mobile, VerificationCodeTypeEnum type, String code){
        VerificationCode verificationCode = new VerificationCode();
        verificationCode.setCode(code);
        verificationCode.setMobile(mobile);
        verificationCode.setType(type.getType());
        VerificationCode result = verificationCodeMapper.selectOne(verificationCode);
        return Optional.ofNullable(result).map(item -> Boolean.TRUE).orElse(Boolean.FALSE);
    }
}
