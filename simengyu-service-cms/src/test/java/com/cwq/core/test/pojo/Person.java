package com.cwq.core.test.pojo;

import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Map;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

//Number&Map
public class Person<T extends Number>{
    
    //非静态方法
     public  void  getP(T a){//在类上声明
        System.out.println(11111);
     }
     

    //静态方法
     public static <V> void  getP11(V a){//在类上声明
         System.out.println(11111);
      }
           
     
     
     
     
     
     
     
     public static <T> void  getP1(T a){
         System.out.println(11111);
     }
     
     static class S{
         
         public <T> void register(Class<T> type, JdbcType jdbcType, TypeHandler<? extends T> handler) {
             //register((Type) type, jdbcType, handler);
          }
         
         
         
         public static  void get(Person<? extends Number > t){
             
         }
         
         
         
         public static void main(String[] args) {
              Person<Number> person = new Person<Number>();
              Person<Integer> person1 = new Person<Integer>();
             // Person<String> person2 = new Person<String>();
              get(person1);
              //get(person2);
              
              
//              SqlSessionFactory
              
        }
     }
     
}
