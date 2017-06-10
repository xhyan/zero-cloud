//package com.xhyan.zero.cloud.account.config.druid;
//
//import org.springframework.boot.context.properties.ConfigurationProperties;
//
///**
// * Druid数据连接池的配置项
// * <p>
// * TODO 后期改为阿里官方的spring boot start，现在没有稳定版
// */
//@ConfigurationProperties(prefix = "spring.datasource.druid")
//public class DruidDataSourceProperties {
//    private String url;
//    private String username;
//    private String password;
//    private String driverClass;
//
//    private int maxActive;
//    private int minIdle;
//    private long maxWait;
//    private int initialSize;
//    private boolean testOnBorrow;
//
//    public String getUrl() {
//        return url;
//    }
//
//    public void setUrl(String url) {
//        this.url = url;
//    }
//
//    public String getUsername() {
//        return username;
//    }
//
//    public void setUsername(String username) {
//        this.username = username;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//    public String getDriverClass() {
//        return driverClass;
//    }
//
//    public void setDriverClass(String driverClass) {
//        this.driverClass = driverClass;
//    }
//
//    public int getMaxActive() {
//        return maxActive;
//    }
//
//    public void setMaxActive(int maxActive) {
//        this.maxActive = maxActive;
//    }
//
//    public int getMinIdle() {
//        return minIdle;
//    }
//
//    public void setMinIdle(int minIdle) {
//        this.minIdle = minIdle;
//    }
//
//    public long getMaxWait() {
//        return maxWait;
//    }
//
//    public void setMaxWait(long maxWait) {
//        this.maxWait = maxWait;
//    }
//
//    public int getInitialSize() {
//        return initialSize;
//    }
//
//    public void setInitialSize(int initialSize) {
//        this.initialSize = initialSize;
//    }
//
//    public boolean isTestOnBorrow() {
//        return testOnBorrow;
//    }
//
//    public void setTestOnBorrow(boolean testOnBorrow) {
//        this.testOnBorrow = testOnBorrow;
//    }
//}
