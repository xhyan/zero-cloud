package com.xhyan.zero.cloud.account.converter;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * 转换器的配置项
 *
 * @author xhyan
 */
@Data
@ConfigurationProperties(prefix = "zero.converter")
public class ConverterProperties {
    /*是否开启转换器*/
    private boolean enable;
    /*转换器需要扫面的包路径*/
    private List<String> scanPackages;
    /*是否映射空值*/
    private boolean mapOnNull = true;
    /*是否惰性*/
    private boolean lazy;
}
