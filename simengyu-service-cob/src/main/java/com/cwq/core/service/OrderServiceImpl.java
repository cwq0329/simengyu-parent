package com.cwq.core.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cwq.core.dao.DetailDao;
import com.cwq.core.dao.OrderDao;
import com.cwq.core.pojo.Cart;
import com.cwq.core.pojo.Detail;
import com.cwq.core.pojo.Item;
import com.cwq.core.pojo.Order;

import redis.clients.jedis.Jedis;

@Service(value="orderService")
@Transactional
public class OrderServiceImpl implements OrderService {
    @Autowired
    private Jedis jedis;
    
    @Autowired
    private CartService cartService; 
    
    @Autowired
    private OrderDao orderDao;
    
    @Autowired
    private DetailDao detailDao;
    
    /**
     * 保存订单及订单详情
     * @param order
     * @param username
     */
    public void addOrderAndDetail(Order order, String username) {
       
        //保存订单
        //生成orderId
        Long incr = jedis.incr("oid");
        order.setId(incr);
        
        //从redis中取出cart信息
        Cart cart = cartService.getCartFromRedis(username);
        
        //将购物车信息补充完整
        cart= cartService.findCartsBySkuId(cart);
        
        //从购物车中取出相关信息存入订单对象
        order.setDeliverFee(cart.getFee());
        order.setTotalPrice(cart.getTotalPrice());
        order.setOrderPrice(cart.getProductPrice());
        
        //设置订单的支付状态
        if(order.getPaymentWay() == 1){
            order.setIsPaiy(0);
        }else{
            order.setIsPaiy(1);
        }
        
        //设置订单状态  0:提交订单 1:仓库配货 2:商品出库 3:等待收货 4:完成 5待退货 6已退货
        order.setOrderState(0);
        
        //设置时间
        order.setCreateDate(new Date());
        
        //设置用户id
        //前台注册的时候可以将用户名和用户id保存到redis中，key：用户名，value：用户id，
        long buyerId = Long.parseLong(jedis.get(username));
        order.setBuyerId(buyerId);
        
        orderDao.insert(order);
        
        
        //遍历购物项来保存订单详情
        List<Item> items = cart.getItems();
        for (Item item : items) {
            Detail detail = new Detail();
            detail.setOrderId(incr);
            detail.setProductId(Long.parseLong(item.getSku().get("product_id").toString()));
            detail.setProductName(item.getSku().get("productName").toString());
            detail.setColor(item.getSku().get("colorName").toString());
            detail.setSize(item.getSku().get("size").toString());
            detail.setPrice(Float.parseFloat(item.getSku().get("price").toString()));
            detail.setAmount(item.getAmount());
            detailDao.insert(detail);
        }
        
        //清空购物车
        jedis.del("cart:"+username);
        
        
    }

}
