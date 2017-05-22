package com.xhyan.zero.cloud.account.converter.annotations;

/**
 * 自定义的转换规则
 */
public @interface ConvertRule {

    /**
     * 转换时是否忽略该字段
     * */
    boolean ignore() default false;
    /**
     * 转换对应的属性
     * */
    String field() default "";
}
