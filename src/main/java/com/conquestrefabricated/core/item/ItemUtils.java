package com.conquestrefabricated.core.item;

import com.conquestrefabricated.core.item.family.Family;
import com.conquestrefabricated.core.item.family.FamilyRegistry;
import com.conquestrefabricated.core.item.family.TypeFilter;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import net.minecraft.core.NonNullList;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.BlockItemStateProperties;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.Property;

public class ItemUtils {
   public static final String BLOCK_STATE_TAG = "BlockStateTag";

   public ItemUtils() {
   }

   public static ItemStack fromState(BlockState state) {
      ItemStack stack = new ItemStack(state.getBlock());
      stack.set(DataComponents.BLOCK_STATE, blockStateProperties(state, property -> true));
      return stack;
   }

   public static ItemStack fromStateNoFacing(BlockState state) {
      ItemStack stack = new ItemStack(state.getBlock());
      stack.set(DataComponents.BLOCK_STATE, blockStateProperties(state, property -> !Objects.equals(property.getName(), "facing")));
      return stack;
   }

   public static ItemStack fromState(BlockState state, Property<?> property) {
      String value = state.getValue(property).toString();
      ItemStack stack = new ItemStack(state.getBlock());
      stack.set(DataComponents.BLOCK_STATE, blockStateProperties(state, candidate -> candidate.equals(property)));
      stack.set(DataComponents.CUSTOM_NAME, Component.nullToEmpty(stack.getHoverName().getString() + property.getName() + "=" + value));
      return stack;
   }

   public static ItemStack fromState(BlockState state, Collection<Property<?>> properties) {
      StringBuilder name = new StringBuilder("[");
      ItemStack stack = new ItemStack(state.getBlock());
      Map<String, String> stateProperties = new HashMap<>();

      for (Property<?> property : properties) {
         if (state.hasProperty(property)) {
            String value = state.getValue(property).toString();
            stateProperties.put(property.getName(), value);
            if (name.length() > 1) {
               name.append(',');
            }

            name.append(property.getName()).append('=').append(value);
         }
      }

      stack.set(DataComponents.BLOCK_STATE, new BlockItemStateProperties(stateProperties));
      if (name.length() > 1) {
         name.append("]");
         stack.set(DataComponents.CUSTOM_NAME, Component.nullToEmpty(stack.getHoverName().getString() + name));
      }

      return stack;
   }

   public static <T extends Item> Optional<T> toItem(Item item, Class<T> t) {
      return t.isInstance(item) ? Optional.of(t.cast(item)) : Optional.empty();
   }

   public static NonNullList<ItemStack> getFamilyItems(ItemStack stack) {
      return getFamilyItems(stack, TypeFilter.ANY);
   }

   public static NonNullList<ItemStack> getFamilyItems(ItemStack stack, TypeFilter filter) {
      NonNullList<ItemStack> items = NonNullList.create();
      getFamily(stack).addAllItems(CreativeModeTabs.searchTab(), items, filter);
      if (items.isEmpty()) {
         items.add(stack);
      }

      return items;
   }

   public static Family<Block> getFamily(ItemStack stack) {
      Item item = stack.getItem();
      Block block = Blocks.AIR;
      if (item instanceof BlockItem) {
         block = ((BlockItem)item).getBlock();
      }

      return FamilyRegistry.BLOCKS.getFamily(block);
   }

   private static BlockItemStateProperties blockStateProperties(
      BlockState state, java.util.function.Predicate<Property<?>> includeProperty
   ) {
      Map<String, String> properties = new HashMap<>();
      state.getValues().forEach((property, value) -> {
         if (includeProperty.test(property)) {
            properties.put(property.getName(), value.toString());
         }
      });
      return new BlockItemStateProperties(properties);
   }
}
