package com.cwq.core.test;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletContext;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import freemarker.template.Configuration;
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations={"classpath:applicationContext.xml"})
public class ListOrMapTest implements ServletContextAware {
//    @Autowired
//    private FreeMarkerConfigurer freeMarkerConfigurer;
    private ServletContext servletContext;
    @Test
    public void test1(){
        HashMap<String, List<String>> hashMap = new HashMap<String,List<String>>();
      //定义集合
        List<String> persons = new ArrayList<String>();
        persons.add("范冰冰");
        persons.add("杨幂");
        persons.add("景甜");
        hashMap.put("persons", persons);
//        persons.stream().collect(Collectors.tom)
        System.out.println(persons);
        System.out.println("=========================");
        System.out.println(hashMap);
        

    }
    
    @Test
    public void test2(){
        HashMap<String, Object> hashMap = new HashMap<String,Object>();
        HashMap<String,String> persons2 = new HashMap<String,String>();
        persons2.put("fbb", "范冰冰");
        persons2.put("jt", "景甜");
        
        hashMap.put("persons2", persons2);

        System.out.println(persons2);
        System.out.println("=========================");
        System.out.println(hashMap);
        

    }
    
    
    @Test
    public void test3(){
        HashMap<String, Object> hashMap = new HashMap<String,Object>();
     // List中包含map
        List ps = new ArrayList();
        HashMap p1 = new HashMap();
        p1.put("id1", "范冰冰");
        p1.put("id2", "景甜");

        HashMap p2 = new HashMap();
        p2.put("id1", "大毛");
        p2.put("id2", "二毛");
        ps.add(p1);
        ps.add(p2);
        hashMap.put("ps", ps);

        System.out.println(ps);
        System.out.println("=========================");
        System.out.println(hashMap);
        

    }
    @Test
    public void test4(){
        
//        Configuration configuration = freeMarkerConfigurer.getConfiguration();
        // 生成的文件位置
        String realPath = servletContext.getRealPath("/html/product/" + 234 + ".html");
        System.out.println("生成的文件位置:" + realPath);
        // 获得最终文件的父目录
        File file = new File(realPath);
        File parentFile = file.getParentFile();
        //如果父目录不存在则进行创建
        if(!parentFile.exists()){
            parentFile.mkdirs();
        }
//        System.out.println(ps);
//        System.out.println("=========================");
//        System.out.println(hashMap);
        

    }

    @Override
    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
        
    }

}
