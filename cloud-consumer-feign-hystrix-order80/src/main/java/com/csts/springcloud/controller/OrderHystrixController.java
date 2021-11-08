package com.csts.springcloud.controller;

import com.csts.springcloud.service.PaymentService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
@DefaultProperties(defaultFallback = "payment_global_fallbackMethod")
public class OrderHystrixController {

    @Resource
    private PaymentService paymentService;

    @GetMapping("/consumer/payment/hystrix/ok/{id}")
    public String paymentInfo_OK(@PathVariable("id") Integer id){
        return paymentService.paymentInfo_OK(id);
    }

    @GetMapping("/consumer/payment/hystrix/timeout/{id}")
//    @HystrixCommand(fallbackMethod = "paymentInfo_Timeout_FallbackMethod", commandProperties = {
//            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1500")
//    })
    @HystrixCommand  // 使用global fallback
    public String paymentInfo_Timeout(@PathVariable("id") Integer id){
//        int age = 10 / 0;
        return paymentService.paymentInfo_Timeout(id);
    }


    public String paymentInfo_Timeout_FallbackMethod(Integer id){
        return "Order80: Payment System busy, please retry later~";
    }

    // 全局fallback
    public String payment_global_fallbackMethod(){
        return "Global Fallback Method Handle, Retry later~!!!!!!!!!!!!";
    }
}
