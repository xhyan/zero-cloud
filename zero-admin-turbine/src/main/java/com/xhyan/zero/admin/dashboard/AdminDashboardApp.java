package com.xhyan.zero.admin.dashboard;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Configuration;


@Configuration
@EnableAdminServer
@EnableDiscoveryClient
@SpringBootApplication
public class AdminDashboardApp {

    public static void main(String[] args) {
        new SpringApplicationBuilder(AdminDashboardApp.class).run(args);
    }

}
