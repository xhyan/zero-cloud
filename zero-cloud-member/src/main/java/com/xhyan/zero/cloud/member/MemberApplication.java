package com.xhyan.zero.cloud.member;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * spring cloud eureka server 启动程序
 */
@EnableDiscoveryClient
@SpringBootApplication
public class MemberApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder(MemberApplication.class).web(true).run(args);
    }
}
