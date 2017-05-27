package com.xhyan.zero.cloud.account.converter.annotations;

import com.baidu.unbiz.easymapper.transformer.Transformer;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义的转换规则
 */
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ConvertRule {

    /**
     * 转换时是否忽略该字段
     */
    boolean ignore() default false;

    /**
     * 转换对应的属性
     */

    String targetField() default "";

    /**
     * 自定义转换器
     *
     * @return
     */
    Class<? extends Transformer> transformer() default Transformer.class;
}
