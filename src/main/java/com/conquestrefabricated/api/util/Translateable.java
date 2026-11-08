package com.conquestrefabricated.api.util;

import net.minecraft.client.resources.language.I18n;

public interface Translateable {
   String getName();

   String getTranslationKey();

   String getTranslationKey(String var1);

   default String getDisplayName() {
      String lookup = this.getTranslationKey();
      String translation = I18n.get(lookup, new Object[0]);
      return translation.equals(lookup) ? this.getName() : translation;
   }

   default String getDisplayName(String parent) {
      String lookup = this.getTranslationKey(parent);
      String translation = I18n.get(lookup, new Object[0]);
      return translation.equals(lookup) ? this.getName() : translation;
   }
}
