package com.csts.springcloud.service;

import org.springframework.stereotype.Component;

@Component
public class PaymentFallbackService implements PaymentService{
    @Override
    public String paymentInfo_OK(Integer id) {
        return "PaymentFallbackService---paymentInfo_OK, fallback   o(╥﹏╥)o";
    }

    @Override
    public String paymentInfo_Timeout(Integer id) {
        return "PaymentFallbackService---paymentInfo_Timeout, fallback   o(╥﹏╥)o";
    }
}
