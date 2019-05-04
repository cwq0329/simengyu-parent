package cn.cwq.com;

import org.apache.ibatis.builder.ParameterExpression;

public class test1 {
  public static void main(String[] args) {
    String str="id:VARCHAR,mode=in";
    str="id,jdbcType=VARCHAR,mode=in";
    ParameterExpression map = new ParameterExpression(str);
    System.out.println(map);
}
}
