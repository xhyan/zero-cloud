package com.xhyan.zero.cloud.member;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

/**
 * spring cloud eureka server 启动程序
 */
@EnableDiscoveryClient
@EnableFeignClients
@SpringBootApplication
public class MemberApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder(MemberApplication.class).web(true).run(args);
    }
}
