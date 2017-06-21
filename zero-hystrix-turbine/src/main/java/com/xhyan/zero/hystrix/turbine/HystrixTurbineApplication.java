package com.xhyan.zero.hystrix.turbine;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.turbine.EnableTurbine;

/**
 * Created by yanliwei on 2016/12/1.
 */
@SpringBootApplication
@EnableHystrixDashboard
@EnableTurbine
@EnableEurekaClient
public class HystrixTurbineApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder(HystrixTurbineApplication.class).run(args);
    }
}
