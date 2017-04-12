package com.xhyan.zero.admin.dashboard;

import de.codecentric.boot.admin.config.EnableAdminServer;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Created by yanliwei on 2016/12/1.
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableAdminServer
public class AdminDashboardServer {
}
