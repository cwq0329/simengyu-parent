package pojo;

import java.awt.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;

public class Student1 {
      private String name;
      private  int age;
      
      
      
	public Student1(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	
      static class pojo{
	     Integer total;
	     ArrayList<Student> data;
        public Integer getTotal() {
            return total;
        }
        public void setTotal(Integer total) {
            this.total = total;
        }
        public ArrayList<Student> getData() {
            return data;
        }
        public void setData(ArrayList<Student> data) {
            this.data = data;
        }
	    
	}
	
	

        static class Student{
             String name;
             int age;
            public String getName() {
                return name;
            }
            public void setName(String name) {
                this.name = name;
            }
            public int getAge() {
                return age;
            }
            public void setAge(int age) {
                this.age = age;
            }
             
            
        }
	
	
	public static void main1(String[] args) {
		
                
	}

	
	
	public static void main(String[] args) {
              String str="{'data'/***/:[{'age':0,'name':'i'},{'age':1,'name':'i'},{'age':2,'name':'i'},{'age':3,'name':'i'}],'total':20}";
               pojo parseObject2 = JSON.parseObject(str,pojo.class);
               System.out.println(parseObject2.getTotal());
               System.out.println(parseObject2.getData().get(1).getName());
               
               //Feature
               JSONObject parseObject = JSON.parseObject(str, Feature.AutoCloseSource);
               System.out.println(parseObject);
               
               System.out.println("==========");
               JSONObject parseObject1= JSON.parseObject(str, Feature.AllowComment);
               
               
               System.out.println("==========");
               JSONObject parseObject3= JSON.parseObject(str, Feature.AllowSingleQuotes); 
               //AllowSingleQuotes
               System.out.println(parseObject3);               
               
        }
	
	
	
}
