package com.conquestrefabricated.core.block;

import com.conquestrefabricated.core.block.factory.InitializationException;
import com.conquestrefabricated.core.item.ItemUtils;
import java.util.Optional;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.Property;

public class StateUtils {
   public StateUtils() {
   }

   public static Optional<BlockState> getOrDefault(ItemStack stack) {
      return toItemBlock(stack.getItem()).map(item -> fromStack(stack, item).orElse(item.getBlock().defaultBlockState()));
   }

   public static Optional<BlockState> fromStack(ItemStack stack) {
      return toItemBlock(stack.getItem()).flatMap(item -> fromStack(stack, item));
   }

   public static Optional<BlockState> fromStack(ItemStack stack, BlockItem item) {
      CompoundTag stackTag = stack.getTag();
      if (stackTag == null) {
         return Optional.of(item.getBlock().defaultBlockState());
      }

      CompoundTag stateTag = stackTag.getCompound("BlockStateTag");
      if (stateTag.isEmpty()) {
         return Optional.of(item.getBlock().defaultBlockState());
      }

      BlockState state = item.getBlock().defaultBlockState();
      StateDefinition<Block, BlockState> container = item.getBlock().getStateDefinition();

      for (String key : stateTag.getAllKeys()) {
         Property<?> property = container.getProperty(key);
         if (property != null) {
            String s1 = stateTag.get(key).getAsString();
            state = with(state, property, s1);
         }
      }

      return state == item.getBlock().defaultBlockState() ? Optional.empty() : Optional.of(state);
   }

   public static Optional<BlockItem> toItemBlock(Item item) {
      return ItemUtils.toItem(item, BlockItem.class);
   }

   public static BlockState parse(String input) {
      int domainEnd = input.indexOf(58);
      String domain = domainEnd == -1 ? "minecraft" : input.substring(0, domainEnd);
      int nameEnd = input.indexOf(91);
      String name = nameEnd == -1 ? input.substring(domainEnd + 1) : input.substring(domainEnd + 1, nameEnd);
      Block block = (Block)BuiltInRegistries.BLOCK.get(ResourceLocation.fromNamespaceAndPath(domain, name));
      if (block == null) {
         throw new InitializationException("invalid block " + input);
      }

      BlockState state = block.defaultBlockState();

      for (int i = nameEnd + 1; i < input.length(); i++) {
         int keyStart = i;
         int keyEnd = indexOf(input, keyStart, '=');
         if (keyEnd == -1) {
            break;
         }

         int valStart = keyEnd + 1;
         int valEnd = indexOf(input, valStart, ',', ']');
         if (valEnd == -1) {
            break;
         }

         String key = input.substring(keyStart, keyEnd);
         String value = input.substring(valStart, valEnd);
         state = with(state, key, value);
         i = valEnd + 1;
      }

      return state;
   }

   private static int indexOf(String string, int from, char... chars) {
      for (char c : chars) {
         int i = string.indexOf(c, from);
         if (i != -1) {
            return i;
         }
      }

      return -1;
   }

   private static BlockState with(BlockState state, String key, String value) {
      Property<? extends Comparable> property = state.getBlock().getStateDefinition().getProperty(key);
      return property == null ? state : with(state, property, value);
   }

   public static <T extends Comparable<T>> BlockState with(BlockState state, Property<T> property, String value) {
      return property.getValue(value).map(t -> (BlockState)state.setValue(property, t)).orElse(state);
   }
}
