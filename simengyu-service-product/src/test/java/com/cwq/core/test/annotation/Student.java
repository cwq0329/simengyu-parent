package com.cwq.core.test.annotation;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Iterator;



@Dbtable(name="student")
public class Student {
    
    @SqlString(name="name1",value=30,constraits=@Constraits(primaryKey=true))
    public String name;

   public static void main(String[] args) throws ClassNotFoundException {
       Class<?> clazz= Class.forName("com.cwq.core.test.annotation.Student");
       boolean flag = clazz.isAnnotationPresent(Dbtable.class);
       if(flag){
           Dbtable annotation = clazz.getAnnotation(Dbtable.class);
           
           String name=annotation.name();
          
           Field[] fields = clazz.getDeclaredFields();
          
           for (int i = 0; i < fields.length; i++) {
               SqlString annotat= fields[i].getAnnotation(SqlString.class);
               if(annotat!=null){
                   Constraits constraits = annotat.constraits();
                   System.out.println(constraits.primaryKey());
               }
           }
           
//           String sql="create table "+name+"( name1  varchar("+value+") primarkey";
           
           //..........
           
           
       }
   }
}




    