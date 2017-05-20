package com.xhyan.zero.cloud.account.config.druid;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Druid数据连接池的配置项
 *
 * TODO 后期改为阿里官方的spring boot start，现在没有稳定版
 */
@Data
@ConfigurationProperties(prefix = "spring.datasource.druid")
public class DruidDataSourceProperties {
    private String url;
    private String username;
    private String password;
    private String driverClass;

    private int     maxActive;
    private int     minIdle;
    private int     initialSize;
    private boolean testOnBorrow;
}
