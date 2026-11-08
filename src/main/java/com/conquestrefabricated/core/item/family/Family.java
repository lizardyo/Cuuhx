package com.conquestrefabricated.core.item.family;

import com.conquestrefabricated.core.util.OptimizedList;
import com.conquestrefabricated.core.util.OptionalValue;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import net.minecraft.core.NonNullList;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.ItemStack;

public abstract class Family<T> implements OptionalValue, Comparator<T> {
   private final List<T> members;
   private final CreativeModeTab group;
   private final Comparator<T> order;
   private T root = (T)null;

   public Family(CreativeModeTab group, List<T> members) {
      this(group, (t1, t2) -> 0, members);
   }

   public Family(CreativeModeTab group, Comparator<T> order, List<T> members) {
      this.group = group;
      this.order = order;
      this.members = members;
   }

   protected abstract T emptyValue();

   protected abstract void addItem(CreativeModeTab var1, NonNullList<ItemStack> var2, T var3);

   public void trim() {
      if (this.members instanceof ArrayList) {
         ((ArrayList)this.members).trimToSize();
      } else {
         if (this.members instanceof OptimizedList) {
            ((OptimizedList)this.members).trim();
         }
      }
   }

   public CreativeModeTab getGroup() {
      return this.group;
   }

   public T getRoot() {
      return this.members.isEmpty() ? this.emptyValue() : this.members.get(0);
   }

   public Optional<T> getMember(int index) {
      return index < this.members.size() ? Optional.ofNullable(this.members.get(index)) : Optional.empty();
   }

   public Optional<T> getMember(Class<? extends T> type) {
      return this.members.stream().filter(t -> t.getClass() == type).findFirst();
   }

   public List<T> getMembers() {
      return Collections.unmodifiableList(this.members);
   }

   public int size() {
      return this.members.size();
   }

   public int indexOf(T member) {
      return this.members.indexOf(member);
   }

   public Family<T> setRoot(T root) {
      this.root = root;
      if (!this.members.contains(root)) {
         this.members.add(root);
      }

      this.members.sort(this);
      return this;
   }

   public Family<T> add(T member) {
      if (!this.members.contains(member)) {
         this.members.add(member);
         this.members.sort(this);
      }

      return this;
   }

   public Family<T> add(T... members) {
      for (T t : members) {
         this.add(t);
      }

      return this;
   }

   public void addAllItems(CreativeModeTab group, NonNullList<ItemStack> list) {
      this.addAllItems(group, list, TypeFilter.ANY);
   }

   public void addAllItems(CreativeModeTab group, NonNullList<ItemStack> list, TypeFilter filter) {
      if (group == BuiltInRegistries.CREATIVE_MODE_TAB.get(CreativeModeTabs.SEARCH) || group == this.group) {
         for (T t : this.members) {
            if (filter.test(t)) {
               this.addItem(group, list, t);
            }
         }
      }
   }

   public void addRootItem(CreativeModeTab group, NonNullList<ItemStack> list) {
      if (group == BuiltInRegistries.CREATIVE_MODE_TAB.get(CreativeModeTabs.SEARCH) || group == this.group) {
         this.addItem(group, list, this.getRoot());
      }
   }

   @Override
   public final int compare(T t1, T t2) {
      if (this.root != null) {
         if (t1 == this.root && t2 != this.root) {
            return -1;
         }

         if (t2 == this.root && t1 != this.root) {
            return 1;
         }
      }

      return this.order != null ? this.order.compare(t1, t2) : 0;
   }

   public interface Filler {
      void fill(Family var1, CreativeModeTab var2, NonNullList<ItemStack> var3);
   }
}
