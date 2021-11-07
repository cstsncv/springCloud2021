package com.csts.springcloud.controller;

import com.csts.springcloud.entities.CommonResult;
import com.csts.springcloud.entities.Payment;
import com.csts.springcloud.lb.LoadBalancer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.net.URI;
import java.util.List;

@RestController
@Slf4j
public class OrderController {
//    public static final String PAYMENT_URL = "http://localhost:8001";
    public static final String PAYMENT_URL = "http://CLOUD-PAYMENT-SERVICE";

    @Resource
    private RestTemplate restTemplate;

    @Resource
    private LoadBalancer loadBalancer;

    @Resource
    private DiscoveryClient discoveryClient;

    @GetMapping("/consumer/payment/create")
    public CommonResult<Payment> create(Payment payment) {
        CommonResult commonResult = restTemplate.postForObject(PAYMENT_URL + "/payment/create", payment, CommonResult.class);
        log.info(commonResult.getMessage());
        return commonResult;
    }


    @GetMapping("/consumer/payment/get/{id}")
    public CommonResult<Payment> getPayment(@PathVariable("id") Long id) {
        CommonResult forObject = restTemplate.getForObject(PAYMENT_URL + "/payment/get/" + id, CommonResult.class);
        log.info("get payment success: ", id);
        return forObject;
    }

    @GetMapping("/consumer/payment/getforentity/{id}")
    public CommonResult<Payment> getPayment1(@PathVariable("id") Long id) {
        ResponseEntity<CommonResult> forEntity = restTemplate.getForEntity(PAYMENT_URL + "/payment/get/" + id, CommonResult.class);
        if (forEntity.getStatusCode().is2xxSuccessful()) {
            log.info("get payment success: ", id);
            return forEntity.getBody();
        }else {
            log.error("failed get id:"+ id);
            return new CommonResult<>(444, "failed get id: "+ id);
        }
    }
    @GetMapping("/consumer/payment/lb")
    public String getLB(){

        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");

        if (instances == null || instances.size() <= 0){
            return null;
        }
        ServiceInstance serviceInstance = loadBalancer.instances(instances);
        URI uri = serviceInstance.getUri();
        return restTemplate.getForObject(uri + "/payment/lb", String.class);
    }
}
