package cn.cwq.com;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

public class test {
   public static void main(String[] args) throws UnsupportedEncodingException {
      String name="汪洋";
      String encode = URLEncoder.encode(name,"utf-8");
      System.out.println(encode);
      System.out.println(11111);
      String encode1=URLEncoder.encode(encode,"utf-8");
      System.out.println(encode1);
      System.out.println("===============================");
   
      String iso=URLDecoder.decode(encode1,"iso-8859-1");
      String utf=URLDecoder.decode(encode1,"utf-8");
      String gbk=URLDecoder.decode(encode1,"gbk");
      System.out.println();
      
      
     // iso:%E6%B1%AA%E6%B4%8B
     // =======
     // utf:%E6%B1%AA%E6%B4%8B
      System.out.println("iso:"+iso);
      System.out.println("=======");
      System.out.println("utf:"+utf);
      System.out.println("=======");
      System.out.println("gbk:"+gbk);
      //System.out.println(encode1);
   }
}
