package com.atguigu.springcloud.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ApplicationContextConfig {

    @Bean
//    @LoadBalanced // 使用自己写的Ribbon负载均衡， 所以这里先禁用自带的Ribbon默认轮询
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }
}
