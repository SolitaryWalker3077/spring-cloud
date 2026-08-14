package com.bite.gateway;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class CustomGatewayFilterFactory extends AbstractGatewayFilterFactory<CustomConfig> implements Ordered {
    public CustomGatewayFilterFactory() {
        super(CustomConfig.class);
    }

    @Override
    public GatewayFilter apply(CustomConfig config) {
        return new GatewayFilter() {
            /**
             * ServerWebExchange: HTTP 请求-响应交互契约, 提供了对HTTP请求和响应的访问
             * GatewayFilterChain: 过滤器链
             * Mono: Reactor的核心类, 数据流发布者,Mono最多只能触发一个事件.可以把Mono用在异步完成任务时,发出通知
             * chain.filter(exchange)  执行请求
             * Mono.fromRunnable()  创建一个包含Runnable元素的数据流
             */
            @Override
            public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
                //Pre类型   执行请求   Post类型
                log.info("Pre Filter, config:{} ",config);  //Pre类型过滤器代码逻辑
                return chain.filter(exchange).then(Mono.fromRunnable(()->{
                    log.info("Post Filter....");  //Post类型过滤器代码逻辑
                }));
            }
        };
    }

    @Override
    public int getOrder() {
        return Ordered.LOWEST_PRECEDENCE;
    }
}
