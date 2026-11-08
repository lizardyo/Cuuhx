package com.conquestrefabricated.core.asset.annotation;

import com.conquestrefabricated.core.util.RenderLayer;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Assets {
   State state();

   Model item();

   Model[] block() default {};

   Recipe[] recipe() default {};

   Render render() default @Render(RenderLayer.UNDEFINED);
}
