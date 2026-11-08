package com.conquestrefabricated.core.asset.lang;

import com.conquestrefabricated.core.util.Translatable;
import com.conquestrefabricated.core.util.cache.Cache;
import net.minecraft.core.RegistryAccess.RegistryEntry;
import net.minecraft.resources.ResourceLocation;

public class Translations extends Cache<String, String> {
   private static final Translations instance = new Translations();

   private Translations() {
   }

   public void add(Translatable translatable) {
      this.put(translatable.getTranslationKey(), translatable.getDisplayName());
   }

   public void add(String type, RegistryEntry<?> entry, String translation) {
      if (entry.key().registry() != null) {
         String key = getKey(type, entry.key().registry());
         this.put(key, translation);
      }
   }

   public void add(String type, ResourceLocation name, String translation) {
      String key = getKey(type, name);
      this.put(key, translation);
   }

   public void add(String key, String translation) {
      this.put(key, translation);
   }

   public String compute(String s) {
      return "";
   }

   public static Translations getInstance() {
      return instance;
   }

   public static String getKey(String type, ResourceLocation name) {
      return getKey(type, name.getNamespace(), name.getPath());
   }

   public static String getKey(String type, String namespace, String path) {
      return type + "." + namespace + "." + path;
   }

   public static String translate(String in) {
      char[] out = new char[in.length()];
      boolean first = true;

      for (int i = 0; i < in.length(); i++) {
         char c = in.charAt(i);
         if (first) {
            first = false;
            c = Character.toUpperCase(c);
         } else if (c == '_') {
            c = ' ';
            first = true;
         }

         out[i] = c;
      }

      return new String(out);
   }
}
