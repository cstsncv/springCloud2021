package com.csts.springcloud.controller;

import com.csts.springcloud.entities.CommonResult;
import com.csts.springcloud.entities.Payment;
import com.csts.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.websocket.server.PathParam;


@RestController
@Slf4j
public class PaymentController {
    //    @Autowired
    @Resource
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @PostMapping(value = "/payment/create")
    public CommonResult create(@RequestBody Payment payment) {
        int result = paymentService.create(payment);
        log.info("***********insert into db: " + result);

        return new CommonResult(200, "success insert into db", result);
    }


    @GetMapping(value = "/payment/get/{id}")
    public CommonResult get(@PathVariable("id") Long id) {
        Payment payment = paymentService.getPaymentById(id);
        log.info("***********get payment sucess, serverPort: "+ serverPort +", payment: " + payment);
        if (payment != null){
            return new CommonResult(200, "***********get payment sucess, serverPort: "+ serverPort, payment);
        } else {
            return new CommonResult(444, "no match id: " + id);
        }

    }
}
