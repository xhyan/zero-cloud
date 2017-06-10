package com.xhyan.zero.cloud.account;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * AccountApplication  账户应用
 *
 * @author yanliwei
 */
@EnableSwagger2
@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
@MapperScan(basePackages = "com.xhyan.zero.cloud.account.mapper")
public class AccountApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder(AccountApplication.class).run(args);
    }
}
