package com.csts.springcloud.alibaba.service.impl;

import com.csts.springcloud.alibaba.dao.OrderDao;
import com.csts.springcloud.alibaba.domain.Order;
import com.csts.springcloud.alibaba.service.AccountService;
import com.csts.springcloud.alibaba.service.OrderService;
import com.csts.springcloud.alibaba.service.StorageService;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Resource
    private OrderDao orderDao;

    @Resource
    private StorageService storageService;

    @Resource
    private AccountService accountService;

    @Override
    @GlobalTransactional(name = "csts-create-order", rollbackFor = Exception.class)
    public void create(Order order) {

        // 1. 建订单
        log.info("--------开始新建订单----");
        orderDao.create(order);

        // 2. 减库存
        log.info("--------订单微服务开始调用库存, 做Storage扣减count");
        storageService.decrease(order.getProductId(), order.getCount());
        log.info("--------订单微服务开始调用库存, 做扣减count结束");

        // 3. 减账户
        log.info("--------订单微服务开始调用账户, 做Account扣减money");
        accountService.decrease(order.getProductId(), order.getMoney());
        log.info("--------订单微服务开始调用账户, 做扣减money结束");

        // 4. 修改订单状态 从0 --> 1, 1 代表已经完成
        log.info("--------修改订单状态开始");
        orderDao.update(order.getUserId(), 0);
        log.info("--------修改订单状态结束");

        log.info("下订单结束");
    }
}
