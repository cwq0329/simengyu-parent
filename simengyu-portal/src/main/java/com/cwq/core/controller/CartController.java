package com.cwq.core.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cwq.core.pojo.Cart;
import com.cwq.core.pojo.Item;
import com.cwq.core.service.CartService;
import com.cwq.core.service.SessionService;
import com.cwq.core.tools.SessionTools;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class CartController {
    
    @Autowired
    private CartService cartService;
    
    @Autowired
    private SessionService sessionService;
    
    //未登录显示购物车
    @RequestMapping(value="/cart")
    public String showCart(Model model,HttpServletRequest request,HttpServletResponse response){
        //判断用户是否登录
        String username = sessionService.getUsernameFromRedis(SessionTools.getSessionId(request, response));
        
        Cart cart2=null;
        //从cookie中取出购物车信息
        Cart cart1 = getCartFromCookies(request);
        
        //用户登录的情况下从redis中取出cart信息
        if(username != null){
            cart2 = cartService.getCartFromRedis(username);
        }
        
        //合并两处购物车
        Cart  cart=mergeCart(cart1,cart2);
        
        //用户登录状态下将cookie和redis中的购物车信息合并后，将cookie中的信息,并将最终的cart信息存入redis中
        if(username!=null){
            this.delCartFromCookies(request,response);
            cartService.addCartToRedis(username, cart);
        }
        
        //根据购物车信息中的库存id查出完整的购物车库存信息
        cart = cartService.findCartsBySkuId(cart);
        model.addAttribute("buyerCart", cart);
        return "cart";
    }
    

    //未登录添加购物车
    @RequestMapping(value="/addCart")
    public String addCart(Model model,HttpServletRequest request,HttpServletResponse response,
            Long skuId,Integer amount){
        Cart cart =null;
        //判断用户是否登录
        String username = sessionService.getUsernameFromRedis(SessionTools.getSessionId(request, response));
        
        if(username!=null){
            //登录状态下从redis中取出cart信息
            cart = cartService.getCartFromRedis(username);
        }else{
            //未登录从cookie中取出购物车
            cart = this.getCartFromCookies(request);
        }
      
        //未登录cookie和已登录redis中都没有cart信息，则新建一个
        if(cart == null){
            cart = new Cart();
        }
        
        //将新商品添加到购物车中
        Item item = new Item();
        item.setSkuId(skuId);
        item.setAmount(amount);
        
        cart.addItem(item);
        
        if(username!=null){
            //已登录将cart存入redis中
            cartService.addCartToRedis(username, cart);
        }else{
            //未登录，将新增加商品的购物车信息保存到cookie中
            this.addCartToCookies(cart,response);
        }
      
        
        return "redirect:/cart";
    }
    
    //显示结算页面
    @RequestMapping(value="/buyer/trueBuy")
    public String  showTrueBuy(Model model,HttpServletRequest request,HttpServletResponse response){
        
        String username= sessionService.getUsernameFromRedis(SessionTools.getSessionId(request, response));
        
        //无货标识
        boolean flag=true;
        
        //判断redis中购物车不能为空
        Cart cart = cartService.getCartFromRedis(username);
        if(cart != null && cart.getItems().size()>0){
            //补充购物车信息
            cart = cartService.findCartsBySkuId(cart);
            //判断库存一定够
            List<Item> items = cart.getItems();
            for (Item item : items) {
                //购买数量大于库存数量
                if(item.getAmount()>Integer.parseInt(item.getSku().get("stock").toString())){
                    item.setIsHave(false);;
                    flag=false;
                }
            }
            //至少有一款商品无货
            if(!flag){
                model.addAttribute("buyerCart", cart);
                return "cart";
            }
        }else{
            //回到空的购物车页面
            return "redirect:cart";
        }
        
        return "order";
    }
    
    
    


    public void addCartToCookies(Cart cart,HttpServletResponse response) {
        try {
            //将对象转json
            ObjectMapper om = new ObjectMapper();
            String cartJson = om.writeValueAsString(cart);
            
            Cookie cookie = new Cookie("cart",cartJson);
            cookie.setMaxAge(60*60*24);
            cookie.setPath("/");
            response.addCookie(cookie);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
    
         
    public Cart getCartFromCookies(HttpServletRequest request) {
        Cart cart =null;
        Cookie[] cookies = request.getCookies();
        if(cookies !=null && cookies.length>0){
            for (Cookie cookie : cookies) {
                if("cart".equals(cookie.getName())){
                    String jsonCart = cookie.getValue();
                    //将cartjson字符串转对象
                    ObjectMapper om = new ObjectMapper();
                    try {
                      cart = om.readValue(jsonCart, Cart.class);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
//                    break;
                    return cart;
                }
            }
           
        }
        return null;
       
    }
    
    private Cart mergeCart(Cart cart1, Cart cart2) {
        if(cart1==null){
            return cart2;
        }else if(cart2==null){
            return cart1;
        }else{
            //合并购物车
            List<Item> items = cart2.getItems();
            for (Item item : items) {
                cart1.addItem(item);
            }
        }
        return cart1;
    }
    
    private void delCartFromCookies(HttpServletRequest request, HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();
        if(cookies!=null){
            for (Cookie cookie : cookies) {
             if("cart".equals(cookie.getName())){
                 cookie.setMaxAge(0);
                 response.addCookie(cookie);
                 break;
             }
         }
        }
         
     }
}
