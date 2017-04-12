package com.xhyan.zero.eureka.client.a;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * A服务启动程序
 */
@EnableDiscoveryClient
@SpringBootApplication
public class ServiceAApp {
    public static void main(String[] args) {
        new SpringApplicationBuilder(ServiceAApp.class).web(true).run(args);
    }
}
