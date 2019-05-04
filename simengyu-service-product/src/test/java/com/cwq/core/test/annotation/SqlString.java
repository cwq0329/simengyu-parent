package com.cwq.core.test.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface SqlString {
  //字段名字  
  public String name() default "123";
  //字段长度
  public int value() default 0;
  //约束条件
  public Constraits  constraits();
}
