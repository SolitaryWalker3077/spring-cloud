package com.bite.order.service;

import com.bite.order.mapper.OrderMapper;
import com.bite.order.model.OrderInfo;
import com.bite.product.api.ProductApi;
import com.bite.product.model.ProductInfo;
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
}
