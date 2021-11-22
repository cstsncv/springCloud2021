package com.csts.spring.alibaba;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class OrderNacosFeignMain84 {
    public static void main(String[] args) {
        SpringApplication.run(OrderNacosFeignMain84.class, args);
    }
}
