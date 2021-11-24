package com.csts.springcloud.alibaba.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan({"com.csts.springcloud.alibaba.dao"})
public class MybatisConfig {
}
