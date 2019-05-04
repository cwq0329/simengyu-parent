package com.cwq.core.test.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Constraits {
   public boolean primaryKey() default false;
   public boolean allowNull() default false;
   public boolean unique() default false;
}
