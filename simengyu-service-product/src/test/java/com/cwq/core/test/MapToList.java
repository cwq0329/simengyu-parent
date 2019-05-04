package com.cwq.core.test;

import java.util.HashMap;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cwq.core.dao.ProductDao;
import com.cwq.core.dao.SkuDao;
import com.cwq.core.pojo.Product;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext.xml"})
public class MapToList {
    
    @Autowired
    private ProductDao productDao;
    
    @Autowired
    private SkuDao skuDao;
    @Test
    public void test1(){
        String[] ids={"1029","1030"};
//        String id="1029";
        Product product = new Product();
        product.setIsShow(1);
        HashMap<String, Object> map = new HashMap<>();
        map.put("ids", ids);
        map.put("product", product);
        
        List<Product> ps = productDao.findinfo(map);
        for (Product product2 : ps) {
            System.out.println( product2.getId());
        }
        
    }   
}
