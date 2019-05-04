package com.cwq.core.message;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.jms.Message;
import javax.jms.MessageListener;

import org.apache.activemq.command.ActiveMQTextMessage;
import org.springframework.beans.factory.annotation.Autowired;

import com.cwq.core.pojo.Color;
import com.cwq.core.pojo.SuperPojo;
import com.cwq.core.service.ProductService;
import com.cwq.core.service.StaticPageService;



/**
 * 消息回调处理类
 * @author Administrator
 *
 */
public class CMSMessageListener implements MessageListener {
    
    @Autowired
    private ProductService productService;
    
    @Autowired
    private StaticPageService staticPageService; 

    @Override
    public void onMessage(Message message) {
        try {
       ActiveMQTextMessage amessage=(ActiveMQTextMessage)message;
       String ids = amessage.getText();
       System.out.println("cms消费方监听到消息 ： "+ ids);
       String[] split = ids.split(",");
       for (String id : split) {
           Long productId = Long.parseLong(id);
           //查询出单个商品详情页面信息
           SuperPojo singleProductDetail = productService.findSingleProductById(productId);
           //取出库存信息及颜色信息
           List<SuperPojo> skus = (List<SuperPojo>)singleProductDetail.get("skus");
           //去除颜色重复，将原有的map变成能够支持freemarker
          Set<Color> colors = new HashSet<Color>();
           for (SuperPojo sku : skus) {
               Long colorId = (Long)sku.get("color_id");
               String colorName=(String)sku.get("colorName");
               Color color = new Color();
               color.setName(colorName);
               color.setId(colorId);
               colors.add(color);
        }
           //将非重复的颜色封装传到页面
           singleProductDetail.setProperty("colors", colors);
           //设置处理数据
           HashMap<String, Object> hashMap = new HashMap<String,Object>();
           hashMap.put("singleProductDetail",singleProductDetail);
           //开始静态化
           staticPageService.staticProductPage(hashMap, id);
           
        
    }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
    }
        
    }

  
}
