package com.xhyan.zero.cloud.account;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * AccountService
 */
//@EnableDiscoveryClient
@SpringBootApplication
public class AccountServiceApp {
    public static void main(String[] args) {
        new SpringApplicationBuilder(AccountServiceApp.class).web(true).run(args);
    }
}
