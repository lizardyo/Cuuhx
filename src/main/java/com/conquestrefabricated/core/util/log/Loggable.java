package com.conquestrefabricated.core.util.log;

import org.apache.logging.log4j.Marker;

public interface Loggable {
   Marker getMarker();

   default void info(String format, Object... args) {
      Log.info(this.getMarker(), format, args);
   }

   default void debug(String format, Object... args) {
      Log.debug(this.getMarker(), format, args);
   }

   default void trace(String format, Object... args) {
      Log.trace(this.getMarker(), format, args);
   }

   default void error(String format, Object... args) {
      Log.error(this.getMarker(), format, args);
   }

   default void warn(String format, Object... args) {
      Log.warn(this.getMarker(), format, args);
   }
}
