package com.xhyan.zero.eureka.client.a.config.druid;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.http.HttpServlet;
import javax.sql.DataSource;
import javax.sql.XADataSource;

/**
 * 阿里Druid数据连接池配置
 */
@Configuration
@ConditionalOnClass({DruidDataSource.class})
@EnableConfigurationProperties(DataSourceProperties.class)
public class DruidConfig {

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.druid")
    @ConditionalOnMissingBean({DataSource.class, XADataSource.class})
    public DataSource dataSource(DataSourceProperties properties) {
        return properties.initializeDataSourceBuilder().type(DruidDataSource.class).build();
    }

    @Bean
    @ConditionalOnClass(HttpServlet.class)
    public ServletRegistrationBean druidStatViewServlet() {
        return new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
    }
    @Bean
    public FilterRegistrationBean druidWebStatFilter() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new WebStatFilter());
        //添加过滤规则.
        filterRegistrationBean.addUrlPatterns("/*");
        //添加不需要忽略的格式信息.
        filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid2/*");
        return filterRegistrationBean;
    }
}
