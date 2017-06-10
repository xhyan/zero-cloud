package com.xhyan.zero.eureka.ribbon.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Created by xhyan on 16-11-6.
 */
@Service
public class ComputeService {
    @Autowired
    RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "addServiceFallBack")
    public String addService(){
        return restTemplate.getForEntity("http://compute-service/add?a=10&b=20", String.class).getBody();
    }

    public String addServiceFallBack(){
        return "error";
    }
}
