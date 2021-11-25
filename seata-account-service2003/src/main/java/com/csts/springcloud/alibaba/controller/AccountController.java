package com.csts.springcloud.alibaba.controller;

import com.csts.springcloud.alibaba.domain.CommonResult;
import com.csts.springcloud.alibaba.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class AccountController {

    @Autowired
    private AccountService accountService;

    @RequestMapping("/account/decrease")
    public CommonResult decrease(Long userId, BigDecimal money){
        accountService.decrease(userId, money);
        return new CommonResult(200, "扣减账户余额成功");
    }
}
