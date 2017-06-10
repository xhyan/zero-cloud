package com.xhyan.zero.cloud.account.converter.annotations;

import java.lang.annotation.*;

/**
 * 转换器注解集合，用于一个对象映射多个目标对象的情况
 */
@Documented
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Rules {

    Rule[] rules();
}
