package com.conquestrefabricated.core.block.builder;

public class BlockName {
   private final String namespace;
   private final String plural;
   private final String singular;

   private BlockName(String namespace, String plural, String singular) {
      this.namespace = namespace;
      this.plural = plural;
      this.singular = singular;
   }

   public String getNamespace() {
      return this.namespace;
   }

   @Override
   public String toString() {
      return this.namespace + ":" + this.singular;
   }

   public String namespaceFormat(String format, boolean plural) {
      return format.indexOf(58) != -1 ? this.format(format, plural) : this.namespace + ":" + this.format(format, plural);
   }

   public String format(String format, boolean plural) {
      return String.format(format, plural ? this.plural : this.singular);
   }

   public static BlockName of(String namespace, String plural, String singular) {
      return new BlockName(namespace, plural, singular);
   }
}
