package com.csts.springcloud.alibaba.controller;

import com.csts.springcloud.alibaba.domain.CommonResult;
import com.csts.springcloud.alibaba.domain.Order;
import com.csts.springcloud.alibaba.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Slf4j
@RestController
public class OrderController {

    @Resource
    private OrderService orderService;

    @GetMapping("/order/create")
    public CommonResult createOrder(Order order){
        log.info("coming /order/create");
        orderService.create(order);
        return new CommonResult(200, "订单创建成功");
    }
}
