package com.xhyan.zero.cloud.account;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * AccountApplication  账户应用
 *
 * @author xhyan
 */
@EnableScheduling
@EnableSwagger2
@SpringCloudApplication
@MapperScan(basePackages = "com.xhyan.zero.cloud.account.mapper")
@EnableFeignClients(basePackages = "com.xhyan.zero.cloud.account.components.client")
public class AccountApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(AccountApplication.class).run(args);
    }
}
