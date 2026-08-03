package com.order.demo.service;


import com.order.demo.mapper.OrderMapper;
import com.order.demo.model.OrderInfo;
import com.order.demo.model.ProductInfo;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@Service
public class OrderService {
    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private DiscoveryClient discoveryClient;
    //计数器
    private AtomicInteger count = new AtomicInteger(1);

    private List<ServiceInstance> instances;

    @PostConstruct
    public void init(){
        //从Eureka中获取服务列表
        instances = discoveryClient.getInstances("product-service");
    }

//    public OrderInfo selectOrderById(Integer orderId){
//        OrderInfo orderInfo = orderMapper.selectOrderById(orderId);
    ////        String url = "http://127.0.0.1:9090/product/"+orderInfo.getProductId();
//        //从Eureka中获取服务列表
//        List<ServiceInstance> instances = discoveryClient.getInstances("product-service");
//        String uri = instances.get(0).getUri().toString();
//
//        String url = uri+"/product/"+orderInfo.getProductId();
//        log.info("远程调用url:{}", url);
//        ProductInfo productInfo = restTemplate.getForObject(url, ProductInfo.class);
//        orderInfo.setProductInfo(productInfo);
//        return orderInfo;
//
//    }

//    public OrderInfo selectOrderById(Integer orderId){
//        OrderInfo orderInfo = orderMapper.selectOrderById(orderId);
//        //计算轮流的实例idnex
//        int index= count.getAndIncrement() % instances.size();
//        //获取实例
//        String uri = instances.get(index).getUri().toString();
//        //拼接url
//        String url = uri+"/product/"+orderInfo.getProductId();
//        log.info("远程调用url:{}", url);
//        ProductInfo productInfo = restTemplate.getForObject(url, ProductInfo.class);
//        orderInfo.setProductInfo(productInfo);
//        return orderInfo;
//
//    }

    public OrderInfo selectOrderById(Integer orderId){
        OrderInfo orderInfo = orderMapper.selectOrderById(orderId);
        String url = "http://product-service/product/"+orderInfo.getProductId();
        log.info("远程调用url:{}", url);
        ProductInfo productInfo = restTemplate.getForObject(url, ProductInfo.class);
        orderInfo.setProductInfo(productInfo);
        return orderInfo;

    }
}