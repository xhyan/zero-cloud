package com.xhyan.zero.cloud.account.converter.annotations;

import com.baidu.unbiz.easymapper.transformer.Transformer;

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
    String targetField() default "";
    /**
     * 自定义转换器
     * @return
     */
    Class<? extends Transformer> transformer() default Transformer.class;
}
