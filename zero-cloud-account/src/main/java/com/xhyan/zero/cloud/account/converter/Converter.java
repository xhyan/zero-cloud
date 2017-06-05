package com.xhyan.zero.cloud.account.converter;

import com.xhyan.zero.cloud.account.converter.mapper.ZeroConvertMapper;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 单例的转换器，获取转换器的统一入口
 *
 * @author xhyan
 */
public class Converter {

    /**
     * 对象转换，转换的对象必须是已注册的对象
     *
     * @param source 源对象
     * @param target 目标对象的class
     *
     * */
    public static <T, P> T convert(P source, Class<T> target) {
        return ZeroConvertMapper.MAPPER.map(source, target);
    }

    /**
     * 列表之间的转换，列表中的对象必须是注册的对象
     *
     * @param sourceList 源对象的列表
     * @param target 目标对象的class
     *
     * */
    public static <T, P> List<T> convertList(List<P> sourceList, Class<T> target){
        return sourceList.stream().map(item -> ZeroConvertMapper.MAPPER.map(item, target)).collect(Collectors.toList());
    }
}
