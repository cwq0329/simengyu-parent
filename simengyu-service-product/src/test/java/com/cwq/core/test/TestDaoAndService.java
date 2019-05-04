package com.cwq.core.test;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSONObject;
import com.cwq.core.dao.TbTestDAO;
import com.cwq.core.pojo.Brand;
import com.cwq.core.pojo.Page1;
import com.cwq.core.pojo.Product;
import com.cwq.core.pojo.TbTest;
import com.cwq.core.service.BrandService;
import com.cwq.core.service.ProductService;
import com.cwq.core.service.TbTestService;
import com.cwq.core.tools.PageHelper.Page;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext.xml"})
public class TestDaoAndService {
    @Autowired
    private TbTestDAO tbTestDAO;
    @Autowired
    private TbTestService tbTestService;
    @Autowired
    private BrandService brandService;
    
    @Autowired
    private ProductService productService;
    @Test
    public void addTest(){
        TbTest tbTest = new TbTest();   
        tbTest.setName("dhth");
        tbTest.setBirthday(new Date());
        tbTestDAO.add(tbTest);
    }
    
    @Test
    public void addTest2(){
        TbTest tbTest = new TbTest();   
        tbTest.setName("dhdwwwwwwwwwwwwwwt");
        tbTest.setBirthday(new Date());
        tbTestService.add(tbTest);
    }
    
    @Test
    public void addTest3(){
        Brand brand = new Brand();
        brand.setId(10l);
        Brand findBrandToById = brandService.findById(brand.getId());
        System.out.println(findBrandToById);
        
    }
    
    
    @Test
    public void addTest4(){
       Page1<Brand> page1 = new Page1<Brand>(null,null);
       Page1 findPage = brandService.findPage(page1);
       String jsonString = JSONObject.toJSONString(findPage,true);
        System.out.println(jsonString);

        
    }
    
    @Test
    public void addTest5(){
      Brand brand = new Brand();
      brand.setName("紫罗兰");;
      brandService.addBrand(brand);
    }
      @Test
      public void addTest6(){
       
        brandService.deleteByIds("29,30");;

       
        
    }
      
      @Test
      public void addTest7(){
       
        Product product = new Product();

        Page<Product> findProductPageByExample = productService.findProductPageByExample(product, null, null);
        System.out.println(findProductPageByExample);
        
    }
      
      @Test
      public void addTest8(){
       Integer name=null;
       String c = "redirect:/console/brand/list.do?name=" + name;
        System.out.println(c instanceof String);
        
    }
      
      @Test
      public void addTest9(){
       String ids = "1,2,3,4,5,6";
       String[] arrids = ids.split(",");
       List<String> collect = Stream.of(arrids).collect(Collectors.toList());
//      List<String> collect = Arrays.stream(arrids).collect(Collectors.toList());
       //转换后无法做增删改
       List<String> asList = Arrays.asList(arrids);
       System.out.println(asList);
        
    }

}
