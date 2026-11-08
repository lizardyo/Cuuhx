package com.conquestrefabricated.client.gui.palette;

import com.conquestrefabricated.core.item.family.Family;
import com.conquestrefabricated.core.item.family.FamilyRegistry;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import net.minecraft.core.NonNullList;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

public class Palette {
   public Palette() {
   }

   public static Container createPalette(ItemStack first, List<ItemStack> family) {
      List<ItemStack> result = new ArrayList<>(family.size());
      result.add(copyOne(first));

      for (ItemStack stack : family) {
         if (!ItemStack.isSameItem(stack, first) || !ItemStack.matches(first, stack)) {
            result.add(copyOne(stack));
         }
      }

      return new SimpleContainer(result.toArray(new ItemStack[0]));
   }

   public static Optional<Container> getPalette(ItemStack stack) {
      return getPalette(stack, stack.getItem());
   }

   private static Optional<Container> getPalette(ItemStack stack, Item item) {
      return item instanceof BlockItem ? getPalette(stack, ((BlockItem)item).getBlock()) : Optional.empty();
   }

   private static Optional<Container> getPalette(ItemStack stack, Block block) {
      if (block == Blocks.AIR) {
         return Optional.empty();
      }

      Family<Block> family = FamilyRegistry.BLOCKS.getFamily(block);
      if (family.isAbsent()) {
         return Optional.empty();
      }

      NonNullList<ItemStack> items = NonNullList.create();
      family.addAllItems(family.getGroup(), items);
      return Optional.of(createPalette(stack, items));
   }

   private static List<ItemStack> createDebugPalette(int min, int max) {
      Random random = new Random(System.currentTimeMillis());
      int size = min + random.nextInt(max - min);
      NonNullList<ItemStack> items = NonNullList.create();
      List<Block> blocks = new ArrayList<>(BuiltInRegistries.BLOCK.stream().toList());

      while (items.size() < size) {
         int index = random.nextInt(blocks.size());
         ItemStack itemStack = new ItemStack((ItemLike)blocks.get(index));
         if (!itemStack.isEmpty()) {
            items.add(itemStack);
         }
      }

      return items;
   }

   private static ItemStack copyOne(ItemStack stack) {
      stack = stack.copy();
      stack.setCount(1);
      return stack;
   }
}
