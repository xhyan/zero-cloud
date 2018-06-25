package com.xhyan.zero.cloud.member;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * spring cloud eureka server 启动程序
 *
 * @author yanliwei
 */
//@EnableFeignClients
//@EnableHystrix
//@EnableHystrixDashboard
@EnableDiscoveryClient
@SpringBootApplication
@MapperScan(basePackages = "com.xhyan.zero.cloud.member.mapper")
public class MemberApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder(MemberApplication.class).web(true).run(args);
    }
}
