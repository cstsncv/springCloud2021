package com.csts.springcloud.alibaba.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;

@Mapper
public interface AccountDao {
    // 扣减库存
    void decrease(@Param("userId") Long userId, @Param("money") BigDecimal money);
}
