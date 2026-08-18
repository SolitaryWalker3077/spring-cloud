package com.seata.order.service;

import com.seata.order.entity.OrderInfo;

public interface OrderService {
    /**
     * 创建订单
     */
    Long create(OrderInfo orderInfo);
}
