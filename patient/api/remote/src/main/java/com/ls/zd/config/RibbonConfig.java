package com.ls.zd.config;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RibbonConfig {

    /**
     * 实例化ribbon使用的RestTemplate
     * @return
     */
    @Bean
//    @LoadBalanced
    public RestTemplate rebbionRestTemplate(){
        return new RestTemplate();
    }

    /**
    * 配置随机负载策略，需要配置属性service-B.ribbon.NFLoadBalancerRuleClassName=com.netflix.loadbalancer.RandomRule
    */
    @Bean
    public IRule ribbonRule() {
        return new RandomRule();
    }
}

