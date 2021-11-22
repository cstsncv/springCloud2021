package com.csts.springcloud.alibaba.myhandler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.csts.springcloud.entities.CommonResult;
import com.csts.springcloud.entities.Payment;

public class ConsumerBlockHandler {
    public static CommonResult handlerException1(BlockException exception){
        return new CommonResult(4444, "按客户自定义 consumerBlockHandler-----1");
    }

    public static CommonResult handlerException2(BlockException exception){
        return new CommonResult(4444, "按客户自定义 consumerBlockHandler-----2");
    }
}
