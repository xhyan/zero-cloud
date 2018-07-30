package com.xhyan.zero.cloud.wallet;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * spring cloud eureka server 启动程序
 *
 * @author yanliwei
 */
//@EnableFeignClients
//@EnableHystrix
//@EnableHystrixDashboard
@EnableScheduling
@EnableDiscoveryClient
@SpringBootApplication
//@MapperScan(basePackages = "com.xhyan.zero.cloud.wallet.mapper")
public class WalletApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(WalletApplication.class).web(true).run(args);
    }

}
