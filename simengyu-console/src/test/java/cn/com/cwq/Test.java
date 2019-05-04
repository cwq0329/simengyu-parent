package cn.com.cwq;

import java.util.HashMap;

public class Test {
  public static void main(String[] args) {
            scan:{
              for (int i = 0; i <100; i++) {
                if(i==99) break  scan;
              }
            }
  
           HashMap<String, String> hashMap = new HashMap<String,String>(){
               public static final int a=1;
               {
                   put("1","1");
                   put("2","2");
                   put("3","3");
                   put("4","4");
                   put("5","5");
               }
           };
           
          System.out.println(hashMap.get("1"));
      
  }
}
