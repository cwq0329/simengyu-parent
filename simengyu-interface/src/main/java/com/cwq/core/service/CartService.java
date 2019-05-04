package com.cwq.core.service;

import com.cwq.core.pojo.Cart;

/**
 * 购物车服务接口
 * @author Administrator
 *
 */
public interface CartService {

    
    /**
     * 根据从购物车取出的cart信息的库存id，查出完整的库存信息封装到cart中返回
     * @param cookieCart
     * @return
     */
    Cart findCartsBySkuId(Cart cookieCart);
    
    /**
     * 用户已登录的情况下将cart信息添加到redis中
     * @param username
     * @param cart
     */
    public void addCartToRedis(String username,Cart cart);
    
    /**
     * 用户已登录下从redis中获取信息
     * @param username
     * @return
     */
    public Cart getCartFromRedis(String username);

}
