package com.conquestrefabricated.core.util;

public interface OptionalValue {
   default boolean isPresent() {
      return !this.isAbsent();
   }

   boolean isAbsent();
}
