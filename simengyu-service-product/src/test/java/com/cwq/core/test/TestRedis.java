package com.cwq.core.test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.dubbo.common.json.JSON;
import com.alibaba.dubbo.common.json.ParseException;
import com.alibaba.fastjson.JSONObject;
import com.cwq.core.dao.BrandDao;
import com.cwq.core.pojo.Brand;
import com.cwq.core.service.BrandService;

import redis.clients.jedis.Jedis;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext.xml"})
public class TestRedis {
    @Autowired
    private Jedis jedis;
    
    @Autowired
    private BrandService brandService;
    
    @Autowired
    private BrandDao brandDao;
    
    @Test
    public void testRedis(){
        Jedis jedis = new Jedis("192.168.40.101",6379);
        Long incr = jedis.incr("pno");
        System.out.println("incrincrincrincrincrincr"+incr);
        
        
    }
    
    @Test
    public void testRedis2(){
      
        Long incr = jedis.incr("pno");
        System.out.println("incrincrincrincrincrincr"+incr);
        
        
    }
    
    @Test
    public void testRedis4() throws ParseException{
        
        
      
//        List<Brand> findBrands = brandService.findBrands();
//        for (Brand brand : findBrands) {
//            String jsonString = JSONObject.toJSONString(brand, true);
//            jedis.hset("brand", String.valueOf(brand.getId()), jsonString);
//        }
        
        
//         Brand brand = new Brand();
//         brand.setName("大大");
////         brand.setId(null);
//         brand.setDescription("第四个个");
//        brandDao.addBrand(brand);
//        
//////        Long incr = jedis.incr(String.valueOf(brand.getId()));
////        jedis.hset("brand",String.valueOf(jedis.hlen("brand")), JSONObject.toJSONString(brand, true));
//        System.out.println(brand.getId());
//        
        
        
//        List<Brand> list = new ArrayList<Brand>();
//        Map<String, String> hgetAll = jedis.hgetAll("brand");
//        Collection<String> values = hgetAll.values();
//        for (String string : values) {
//            System.out.println(string);
//            list.add(JSON.parse(string, Brand.class));
//        }
        
        
//        System.out.println(list);
        
        
        
//        List<Brand> list = new ArrayList<Brand>();
//        Map<String, String> hgetAll = jedis.hgetAll("brand");
//        Set<Entry<String, String>> entrySet = hgetAll.entrySet();
//        for (Entry<String, String> entry : entrySet) {
//            String value = entry.getValue();
////            System.out.println(value);
//           Brand parse = JSON.parse(value, Brand.class);
//           list.add(parse);
//          
//        }
//        
//        System.out.println(list);

         
        System.out.println(jedis.hgetAll("brand"));
        
        
    }
}
