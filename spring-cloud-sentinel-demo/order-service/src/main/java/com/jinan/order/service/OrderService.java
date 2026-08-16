package com.jinan.order.service;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.jinan.order.mapper.OrderMapper;
import com.jinan.order.model.OrderInfo;
import com.jinan.product.api.ProductApi;
import com.jinan.product.model.ProductInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OrderService {
    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private ProductApi productApi;

    public OrderInfo selectOrderById(Integer orderId){
        OrderInfo orderInfo = orderMapper.selectOrderById(orderId);
//        String url = "http://product-service/product/"+orderInfo.getProductId();
//        ProductInfo productInfo = restTemplate.getForObject(url, ProductInfo.class);
        ProductInfo productInfo = productApi.getProductInfo(orderInfo.getProductId());
        orderInfo.setProductInfo(productInfo);
        return orderInfo;
    }

    @SentinelResource("queryOrder")
    public void queryOrder() {
        System.out.println("查询订单信息");
    }
}
