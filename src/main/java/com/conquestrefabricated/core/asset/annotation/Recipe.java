package com.conquestrefabricated.core.asset.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Recipe {
   String name();

   String template();

   String output() default "";

   Ingredient[] ingredients() default {};
}
