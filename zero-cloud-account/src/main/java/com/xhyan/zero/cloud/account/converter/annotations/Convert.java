package com.xhyan.zero.cloud.account.converter.annotations;

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
    Class<?> target() default Void.class;

    /**
     * 是否是单向映射
     * */
    boolean unidirection() default false;

    /**
     * 空属性是否映射
     * */
    boolean mapOnNull() default false;

}
