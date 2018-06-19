package com.xhyan.zero.cloud.member;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * spring cloud eureka server 启动程序
 *
 * @author yanliwei
 */
//@EnableDiscoveryClient
//@EnableFeignClients
//@EnableHystrix
//@EnableHystrixDashboard
@SpringBootApplication
public class MemberApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder(MemberApplication.class).web(true).run(args);
    }
}
