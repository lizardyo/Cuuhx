package com.conquestrefabricated.core.group.forge;

import com.conquestrefabricated.core.asset.lang.Translations;
import com.conquestrefabricated.core.item.group.ConquestGroup;
import com.conquestrefabricated.core.item.group.sort.ItemList;
import com.conquestrefabricated.core.item.group.sort.Sorter;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;
import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.CreativeModeTab.DisplayItemsGenerator;
import net.minecraft.world.item.CreativeModeTab.Row;
import net.minecraft.world.item.CreativeModeTab.Type;

public abstract class ConquestItemGroup extends CreativeModeTab implements ConquestGroup {
   private static final String pathFormat = "/assets/%s/groups/%s.txt";
   private final int index;
   private final Component translationKey;
   public final Sorter<ItemStack> sorter;
   public List<ItemStack> cached = Collections.emptyList();

   public ConquestItemGroup(
      int index, String label, Row row, int i, Type type, Component text, Supplier<ItemStack> supplier, DisplayItemsGenerator entryCollector
   ) {
      super(
         CreativeModeTab.builder(row, i)
            .icon(supplier)
            .backgroundTexture(ResourceLocation.withDefaultNamespace("textures/gui/container/creative_inventory/tab_items.png"))
            .title(text)
            .displayItems(entryCollector)
      );
      String namespace = "conquest";
      this.index = index;
      this.translationKey = Component.translatable(Translations.getKey("itemGroup", namespace, label));
      this.sorter = this.getItemSorter(namespace, label);
      Translations.getInstance().add(this.translationKey.getString(), Translations.translate(label));
   }

   public Component getDisplayName() {
      return this.translationKey;
   }

   public void invalidate() {
      this.cached = Collections.emptyList();
   }

   public int getOrderIndex() {
      return this.index;
   }

   private Sorter<ItemStack> getItemSorter(String namespace, String label) {
      String path = String.format("/assets/%s/groups/%s.txt", namespace, label);

      try (InputStream in = FamilyGroup.class.getResourceAsStream(path)) {
         if (in == null) {
            return Sorter.none();
         }

         try (BufferedReader reader = new BufferedReader(new InputStreamReader(in))) {
            return ItemList.read(reader);
         }
      } catch (IOException e) {
         e.printStackTrace();
         return Sorter.none();
      }
   }

   public abstract void populate(NonNullList<ItemStack> var1);
}
