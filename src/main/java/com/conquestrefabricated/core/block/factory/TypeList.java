package com.conquestrefabricated.core.block.factory;

import com.conquestrefabricated.core.util.OptimizedList;
import com.conquestrefabricated.core.util.cache.Cache;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import net.minecraft.world.level.block.AirBlock;
import net.minecraft.world.level.block.Block;

public class TypeList implements Iterable<Class<? extends Block>>, Comparator<Block> {
   public static final TypeList EMPTY = new TypeList(Collections.emptyList());
   private static final Cache<TypeList.TypeKey, TypeList> cache = new TypeList.TypeListCache();
   private final List<Class<? extends Block>> types;

   private TypeList(List<Class<? extends Block>> types) {
      this.types = types;
   }

   public boolean isEmpty() {
      return this.types.isEmpty();
   }

   public Class<? extends Block> first() {
      return this.types.size() > 0 ? this.types.get(0) : AirBlock.class;
   }

   public static TypeList of(Collection<Class<? extends Block>> types) {
      return new TypeList(new ArrayList<>(types));
   }

   @SafeVarargs
   public static TypeList of(Class<? extends Block>... types) {
      if (types.length == 0) {
         throw new RuntimeException("No Types provided!");
      } else {
         return cache.get(new TypeList.TypeKey(types));
      }
   }

   public TypeList add(Class<? extends Block> type) {
      if (type == null) {
         throw new IllegalArgumentException("Cannot add null type to TypeList");
      }

      List<Class<? extends Block>> newTypes = new ArrayList<>(this.types);
      newTypes.add(type);
      return new TypeList(newTypes);
   }

   public TypeList addAll(Collection<Class<? extends Block>> types) {
      if (types != null && !types.isEmpty()) {
         List<Class<? extends Block>> newTypes = new ArrayList<>(this.types);
         newTypes.addAll(types);
         return new TypeList(newTypes);
      } else {
         return this;
      }
   }

   @SafeVarargs
   public final TypeList addAll(Class<? extends Block>... types) {
      return types != null && types.length != 0 ? this.addAll(Arrays.asList(types)) : this;
   }

   public TypeList remove(Class<? extends Block> type) {
      if (type != null && this.types.contains(type)) {
         List<Class<? extends Block>> newTypes = new ArrayList<>(this.types);
         newTypes.remove(type);
         return newTypes.isEmpty() ? EMPTY : new TypeList(newTypes);
      } else {
         return this;
      }
   }

   public TypeList removeAll(Collection<Class<? extends Block>> typesToRemove) {
      if (typesToRemove != null && !typesToRemove.isEmpty()) {
         List<Class<? extends Block>> newTypes = new ArrayList<>(this.types);
         newTypes.removeAll(typesToRemove);
         return newTypes.isEmpty() ? EMPTY : new TypeList(newTypes);
      } else {
         return this;
      }
   }

   @SafeVarargs
   public final TypeList removeAll(Class<? extends Block>... types) {
      return types != null && types.length != 0 ? this.removeAll(Arrays.asList(types)) : this;
   }

   public TypeList replace(Class<? extends Block> oldType, Class<? extends Block> newType) {
      if (oldType != null && newType != null && this.types.contains(oldType)) {
         List<Class<? extends Block>> newTypes = new ArrayList<>(this.types);
         int index = newTypes.indexOf(oldType);
         newTypes.set(index, newType);
         return new TypeList(newTypes);
      } else {
         return this;
      }
   }

   @Override
   public Iterator<Class<? extends Block>> iterator() {
      return this.types.iterator();
   }

   public int compare(Block b1, Block b2) {
      return this.getIndex(b1) - this.getIndex(b2);
   }

   private int getIndex(Object o) {
      int max = -1;

      for (int i = 0; i < this.types.size(); i++) {
         Class<?> type = this.types.get(i);
         if (type.isInstance(o)) {
            max = Math.max(max, i);
         }
      }

      return max == -1 ? this.types.size() : max;
   }

   private static class TypeKey {
      private final Class<? extends Block>[] types;

      private TypeKey(Class<? extends Block>[] types) {
         this.types = types;
      }

      @Override
      public boolean equals(Object o) {
         if (this == o) {
            return true;
         } else if (o != null && this.getClass() == o.getClass()) {
            TypeList.TypeKey typeKey = (TypeList.TypeKey)o;
            return Arrays.equals(this.types, typeKey.types);
         } else {
            return false;
         }
      }

      @Override
      public int hashCode() {
         return Arrays.hashCode(this.types);
      }
   }

   private static class TypeListCache extends Cache<TypeList.TypeKey, TypeList> {
      private TypeListCache() {
      }

      public TypeList compute(TypeList.TypeKey typeKey) {
         OptimizedList<Class<? extends Block>> list = OptimizedList.of(typeKey.types);
         list.trim();
         return new TypeList(list);
      }
   }
}
