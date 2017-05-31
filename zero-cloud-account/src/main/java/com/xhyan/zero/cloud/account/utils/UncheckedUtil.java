package com.xhyan.zero.cloud.account.utils;

import com.xhyan.zero.cloud.account.functional.UncheckedFunction;

import java.util.Objects;
import java.util.function.Function;

/**
 * 处理Lambda表达式工具类，Checked异常，并转为Unchecked异常
 */
public class UncheckedUtil {

    public static <T, R> Function<T, R> of(UncheckedFunction<T, R> mapper) {
        Objects.requireNonNull(mapper);
        return t -> {
            try {
                return mapper.apply(t);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        };
    }

}
