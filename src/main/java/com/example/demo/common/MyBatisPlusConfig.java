package com.example.demo.common;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @ author: limeng
 * @ date: Created in 2019/8/28 14:35
 * @ description：mybatisplus配置
 */
@EnableTransactionManagement(proxyTargetClass = true)
@Configuration
public class MyBatisPlusConfig {

    // mybatis-plus分页插件
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }
}
