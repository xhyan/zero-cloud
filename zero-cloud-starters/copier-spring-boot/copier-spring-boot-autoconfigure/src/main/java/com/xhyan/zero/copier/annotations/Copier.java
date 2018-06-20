package com.xhyan.zero.copier.annotations;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import ma.glasnost.orika.CustomMapper;

/**
 * 属性复制器注解
 * @author yanliwei
 */
@Documented
@Target({TYPE, FIELD})
@Retention(RUNTIME)
public @interface Copier {

    /**
     * 映射类
     * @return
     */
    Class mapClass();

    /**
     * 自定义映射器，默认为空白映射器
     * @return
     */
    Class<? extends CustomMapper> mapper() default EmptyMapper.class;
}
