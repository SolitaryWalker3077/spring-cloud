package com.jinan.order.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.jinan.order.model.OrderInfo;
import com.jinan.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/order")
@RestController
public class OrderController {
    @Autowired
    private OrderService orderService;

    @SentinelResource("/sentinel/id")
    @RequestMapping("/{orderId}")
    public OrderInfo getOrderById(@PathVariable("orderId") Integer orderId){
        return orderService.selectOrderById(orderId);
    }

    @RequestMapping("/write")
    public String write() {
        System.out.println("写操作");
        orderService.queryOrder();
        return "写操作";
    }
    @RequestMapping("/read")
    public String read() {
        System.out.println("读操作");
        orderService.queryOrder();
        return "读操作";
    }
}
