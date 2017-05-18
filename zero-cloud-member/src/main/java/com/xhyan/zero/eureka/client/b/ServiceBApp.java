package com.xhyan.zero.eureka.client.b;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * spring cloud eureka server 启动程序
 */
@EnableDiscoveryClient
@SpringBootApplication
public class ServiceBApp {
    public static void main(String[] args) {
        new SpringApplicationBuilder(ServiceBApp.class).web(true).run(args);
    }
}
