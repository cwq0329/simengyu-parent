package com.cwq.core.service;

import com.cwq.core.pojo.Order;

/**
 * 订单服务接口
 * @author Administrator
 *
 */
public interface OrderService {
    /**
     * 保存订单及订单详情
     * @param order
     * @param username
     */
    public void addOrderAndDetail(Order order,String username);
}
