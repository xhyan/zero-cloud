package com.xhyan.zero.cloud.account.converter;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

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
    /*是否单向转换*/
    private boolean unidirection;
    /*是否惰性*/
    private boolean lazy;
}
