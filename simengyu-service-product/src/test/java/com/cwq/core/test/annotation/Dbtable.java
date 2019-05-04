package com.cwq.core.test.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Target(ElementType.TYPE)//只能放在类上面
@Retention(RetentionPolicy.RUNTIME)
public @interface Dbtable {

  
     public String name() default "123";


    
}
