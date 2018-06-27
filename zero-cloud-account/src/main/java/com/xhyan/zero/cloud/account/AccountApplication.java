package com.xhyan.zero.cloud.account;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * AccountApplication  账户应用
 *
 * @author xhyan
 */
//@EnableHystrix
//@EnableHystrixDashboard
@EnableSwagger2
@EnableFeignClients
@SpringCloudApplication
@MapperScan(basePackages = "com.xhyan.zero.cloud.account.mapper")
public class AccountApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(AccountApplication.class).run(args);
    }
}
