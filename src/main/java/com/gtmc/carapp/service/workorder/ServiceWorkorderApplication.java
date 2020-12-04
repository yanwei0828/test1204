package com.gtmc.carapp.service.workorder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

//@EnableFeignClients
@SpringBootApplication
//@EnableDiscoveryClient
public class ServiceWorkorderApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceWorkorderApplication.class, args);
    }

}

