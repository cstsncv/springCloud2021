package com.csts.springcloud.alibaba.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.csts.springcloud.alibaba.myhandler.ConsumerBlockHandler;
import com.csts.springcloud.entities.CommonResult;
import com.csts.springcloud.entities.Payment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RateLimitController {

    @GetMapping("/byResource")
    @SentinelResource(value = "buResource", blockHandler = "handleException")
    public CommonResult byResource(){

        return new CommonResult(200, "按资源名称限流测试OK", new Payment(2021L, "serial001"));
    }

    public CommonResult handleException(BlockException exception){
        return new CommonResult(444, exception.getClass().getCanonicalName() + "\t 服务不可用!!!");
    }


    @GetMapping("/byUrl")
    @SentinelResource(value = "buUrl")
    public CommonResult byUrl(){

        return new CommonResult(200, "按URL限流测试OK", new Payment(2021L, "serial002"));
    }

    @GetMapping("/consumerBlockHandler")
    @SentinelResource(value = "consumerBlockHandler", blockHandlerClass = ConsumerBlockHandler.class, blockHandler = "handlerException1")
    public CommonResult byConsumerBlockHandler(){

        return new CommonResult(200, "按consumerBlockHandler限流测试OK", new Payment(2021L, "serial003"));
    }
}
