package com.xhyan.zero.copier.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 复制器配置属性
 *
 * @author yanliwei
 */
@ConfigurationProperties(prefix = "copier")
public class CopierProperties {

    /**
     * 复制器注解类的包路径，多个以逗号分割
     */
    private String copierPackages;
    /**
     * 转换器的包路径，多个以逗号分割
     */
    private String convertPackages;

    public String getConvertPackages() {
        return convertPackages;
    }

    public void setConvertPackages(String convertPackages) {
        this.convertPackages = convertPackages;
    }

    public String getCopierPackages() {
        return copierPackages;
    }

    public void setCopierPackages(String copierPackages) {
        this.copierPackages = copierPackages;
    }
}
