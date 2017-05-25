//package com.xhyan.zero.cloud.account.config.druid;
//
//import com.alibaba.druid.pool.DruidDataSource;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.AutoConfigureBefore;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
//import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
//import org.springframework.boot.context.properties.EnableConfigurationProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import javax.sql.DataSource;
//import java.sql.SQLException;
//
///**
// * 阿里Druid数据连接池自动化配置配置
// */
//@Configuration
//@ConditionalOnClass({DruidDataSource.class})
//@AutoConfigureBefore(DataSourceAutoConfiguration.class)
//@EnableConfigurationProperties(DruidDataSourceProperties.class)
//public class DruidConfig {
//
//    @Autowired
//    private DruidDataSourceProperties druidDataSourceProperties;
//
//    @Bean
//    public DataSource dataSource(){
//        DruidDataSource dataSource = new DruidDataSource();
//        dataSource.setUrl(druidDataSourceProperties.getUrl());
//        dataSource.setUsername(druidDataSourceProperties.getUsername());
//        dataSource.setPassword(druidDataSourceProperties.getPassword());
//        if (druidDataSourceProperties.getInitialSize() > 0) {
//            dataSource.setInitialSize(druidDataSourceProperties.getInitialSize());
//        }
//        if (druidDataSourceProperties.getMinIdle() > 0) {
//            dataSource.setMinIdle(druidDataSourceProperties.getMinIdle());
//        }
//        if (druidDataSourceProperties.getMaxActive() > 0) {
//            dataSource.setMaxActive(druidDataSourceProperties.getMaxActive());
//        }
//        if (druidDataSourceProperties.getMaxWait() > 0){
//            dataSource.setMaxWait(druidDataSourceProperties.getMaxWait());
//        }
//        dataSource.setTestOnBorrow(druidDataSourceProperties.isTestOnBorrow());
//        try {
//            dataSource.init();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        return dataSource;
//    }
//}
