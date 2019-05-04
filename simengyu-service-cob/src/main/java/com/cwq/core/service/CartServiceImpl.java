package com.cwq.core.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cwq.core.dao.SkuDao;
import com.cwq.core.pojo.Cart;
import com.cwq.core.pojo.Item;
import com.cwq.core.pojo.SuperPojo;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import redis.clients.jedis.Jedis;

@Service(value="cartService")
public class CartServiceImpl implements CartService {
    
    @Autowired
    private SkuDao skuDao;
    
    @Autowired
    private Jedis jedis;

    /**
     * 根据从购物车取出的cart信息的库存id，查出完整的库存信息封装到cart中返回
     * @param 传过来的cart itme没有库存信息（有skuId，amount）
     * @return 返回的Cart item有sku库存信息
     */
    public Cart findCartsBySkuId(Cart cart) {
        //从购物车对象中取出购买集合
        ArrayList<Item> listItem = new ArrayList<Item>();
        
        List<Item> Items = cart.getItems();
        for (Item item : Items) {
            //查出复合库存信息,将其存入item中
            SuperPojo scp= skuDao.findSkuAndColorAndProductBySkuId(item.getSkuId());
            //添加sku库存信息到item中
            item.setSku(scp);
            
            //将这个item添加到集合
            listItem.add(item);
        }
        //覆盖cart中的信息
        cart.setItems(listItem);
        return cart;
    }

    /**
     * 用户已登录的情况下将cart信息添加到redis中
     * @param username
     * @param cart
     */
    public void addCartToRedis(String username, Cart cart) {
        //将cart对象转json对象存入redis中
            try {
                ObjectMapper om = new ObjectMapper();
                String jsonstr = om.writeValueAsString(cart);
                jedis.set("cart:"+username, jsonstr);
            } catch (JsonProcessingException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
     
        
    }

    @Override
    public Cart getCartFromRedis(String username) {
       //将cart信息从redis中取出来转对象
        String strcart = jedis.get("cart:"+username);
        if(strcart==null){
            return null;
        }
        Cart cart = null;
        try {
            ObjectMapper om = new ObjectMapper();
            cart = om.readValue(strcart, Cart.class);
        } catch(IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return cart;
    }

}
