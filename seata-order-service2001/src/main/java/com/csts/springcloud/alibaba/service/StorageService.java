package com.csts.springcloud.alibaba.service;

import com.csts.springcloud.alibaba.domain.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "seata-storage-service")
public interface StorageService {

    @PostMapping("/storage/decrease")
    CommonResult decrease(@RequestParam(value = "productId") Long productId, @RequestParam(value = "count") Integer count);
}
