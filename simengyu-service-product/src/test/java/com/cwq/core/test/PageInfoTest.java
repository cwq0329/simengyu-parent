package com.cwq.core.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cwq.core.pojo.Brand;
import com.cwq.core.service.BrandService;
import com.cwq.core.tools.PageHelper.Page;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext.xml"})
public class PageInfoTest {
    @Autowired
    private BrandService brandService;
    @Test
    public void test1(){
        Brand brand = new Brand();
        brand.setName("莲");
        brand.setIsDisplay(1);
        Integer pageNum=1;
        Integer pageSize=3;
        Page<Brand> pages = brandService.findByExample(brand, pageNum, pageSize);
        System.out.println(pages.getResult());
        
    }
}
