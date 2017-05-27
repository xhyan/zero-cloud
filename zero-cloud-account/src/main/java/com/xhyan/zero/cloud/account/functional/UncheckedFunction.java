package com.xhyan.zero.cloud.account.functional;

/**
 * 函数式接口，处理Lambda表达式中，Checked异常，并转为Unchecked异常
 *
 * @author xhyan
 */
@FunctionalInterface
public interface UncheckedFunction<T, R> {

    R apply(T t) throws Exception;
}
