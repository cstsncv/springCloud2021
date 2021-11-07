package com.csts.springcloud.controller;

import com.csts.springcloud.entities.CommonResult;
import com.csts.springcloud.entities.Payment;
import com.csts.springcloud.service.PaymentFeignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.websocket.server.PathParam;

@RestController
@Slf4j
public class OrderFeignController {

    @Resource
    private PaymentFeignService paymentFeignService;

    @GetMapping("/consumer/payment/get/{id}")
    public CommonResult<Payment> getPaymentByID(@PathVariable("id") Long id) {
        return paymentFeignService.get(id);
    }

    @GetMapping("/consumer/payment/feign/timeout")
    public String paymentFeignTimeout(){
        // openfeign-ribbon, 客户端默认等待1秒
        return paymentFeignService.paymentFeignTimeout();
    }
}
