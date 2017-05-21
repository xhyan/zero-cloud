package com.xhyan.zero.cloud.account;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * AccountService
 */
//@EnableDiscoveryClient
@EnableSwagger2
@SpringBootApplication
@MapperScan(basePackages = "com.xhyan.zero.cloud.account.mapper")
public class AccountApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder(AccountApplication.class).web(true).run(args);
    }
}
