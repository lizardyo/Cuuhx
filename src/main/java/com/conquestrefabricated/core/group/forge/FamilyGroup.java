package com.conquestrefabricated.core.group.forge;

import com.conquestrefabricated.core.item.family.Family;
import com.conquestrefabricated.core.item.family.FamilyRegistry;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Stream;
import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.CreativeModeTab.DisplayItemsGenerator;
import net.minecraft.world.item.CreativeModeTab.Row;
import net.minecraft.world.item.CreativeModeTab.Type;

public class FamilyGroup extends TaggedGroup<FamilyGroup> {
   public static final List<FamilyGroup> FAMILY_GROUPS = new LinkedList<>();
   private static final Family.Filler ALL_ITEMS = Family::addAllItems;
   private static final Family.Filler ROOT_ITEMS = Family::addRootItem;
   private static Family.Filler filler = Family::addAllItems;
   private final Supplier<ItemStack> icon;
   public final String label;

   public FamilyGroup(int order, String label, Supplier<ItemStack> icon, Row row, int column, Type type, Component text, DisplayItemsGenerator entryCollector) {
      super(order, label, row, column, type, text, icon, entryCollector);
      this.icon = icon;
      this.label = label;
      FAMILY_GROUPS.add(this);
   }

   public FamilyGroup self() {
      return this;
   }

   @Override
   public void populate(NonNullList<ItemStack> items) {
      FamilyRegistry.BLOCKS.values().forEach(family -> filler.fill(family, this, items));
      FamilyRegistry.ITEMS.values().forEach(family -> filler.fill(family, this, items));
   }

   public static void setAddAllItems() {
      if (filler != ALL_ITEMS) {
         filler = ALL_ITEMS;
         FAMILY_GROUPS.forEach(ConquestItemGroup::invalidate);
      }
   }

   public static void setAddRootItems() {
      if (filler != ROOT_ITEMS) {
         filler = ROOT_ITEMS;
         FAMILY_GROUPS.forEach(ConquestItemGroup::invalidate);
      }
   }

   public static Stream<FamilyGroup> stream() {
      return FAMILY_GROUPS.stream().sorted(Comparator.comparing(ConquestItemGroup::getOrderIndex));
   }
}
