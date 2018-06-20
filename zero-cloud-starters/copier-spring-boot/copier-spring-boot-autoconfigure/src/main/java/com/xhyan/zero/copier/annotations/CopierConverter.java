package com.xhyan.zero.copier.annotations;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * 属性复制转换器
 *
 * @author yanliwei
 */
@Documented
@Target({TYPE, FIELD})
@Retention(RUNTIME)
public @interface CopierConverter {

    /**
     * 转换器注册名称
     * @return
     */
    String name();

}
