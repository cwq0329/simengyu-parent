package com.cwq.core.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cwq.core.pojo.Order;
import com.cwq.core.service.OrderService;
import com.cwq.core.service.SessionService;
import com.cwq.core.tools.SessionTools;

@Controller
public class OrderController {
    
    @Autowired
    private SessionService sessionService;
    
    @Autowired
    private OrderService orderService;
    
    
    //提交订单
    @RequestMapping(value="/buyer/submitOrder")
    public String submitOrder(Model model,Order order,HttpServletRequest request,HttpServletResponse response){
        //判断用户是否登录
        String username = sessionService.getUsernameFromRedis(SessionTools.getSessionId(request, response));
        orderService.addOrderAndDetail(order,username);
        return "success";
    }
    
}
