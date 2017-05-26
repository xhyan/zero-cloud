package com.xhyan.zero.cloud.account.converter.annotations;

import com.baidu.unbiz.easymapper.codegen.AtoBMapping;

import java.lang.annotation.*;

/**
 * 对象的转换器的注解
 */
@Documented
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Convert {
    /**
     * 需要转换的目标对象
     * @return
     */
    Class<?> target();

    /**
     * 空属性是否映射
     * */
    boolean mapOnNull() default false;

    /**
     * 自定义映射规则，AtoBMapping接口的实现类
     * @return
     */
    Class<? extends AtoBMapping> mapping() default AtoBMapping.class;

}
