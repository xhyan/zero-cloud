package com.xhyan.zero.copier.annotations;

import ma.glasnost.orika.metadata.MappingDirection;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 属性复制器字段配置注解
 * @author yanliwei
 */
@Documented
@Target({FIELD})
@Retention(RUNTIME)
public @interface CopierField {

    /**
     * 映射字段
     * @return
     */
    String mapFiled();

    /**
     * 是否忽略该属性
     * @return
     */
    boolean isIgnore() default false;

    /**
     * 属性转换器名称
     * @return
     */
    String converter() default "";

    /**
     * 是否复制空值
     * @return
     */
    boolean mapNulls() default false;
    /**
     * 反向是否复制空值
     * @return
     */
    boolean mapNullsInReverse() default false;

    /**
     * 映射策略，默认双向，可指定A->B, B->A 的单向映射
     * @see ma.glasnost.orika.metadata.MappingDirection
     * @return
     */
    MappingDirection direction() default MappingDirection.BIDIRECTIONAL;
}
