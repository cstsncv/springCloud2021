package com.csts.spring.alibaba.controller;

import com.csts.spring.alibaba.service.PaymentService;
import com.csts.springcloud.entities.CommonResult;
import com.csts.springcloud.entities.Payment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class OrderFeignController {

    @Resource
    private PaymentService paymentService;

    @GetMapping("/consumer/paymentSQL/{id}")
    public CommonResult<Payment> getPaymentSQL(@PathVariable("id") Long id) {
        return paymentService.paymentSQL(id);
    }
}
