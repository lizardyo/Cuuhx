package com.conquestrefabricated.core.client.color;

import net.minecraft.client.color.block.BlockColor;
import net.minecraft.client.color.item.ItemColor;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.FoliageColor;
import net.minecraft.world.level.GrassColor;
import net.minecraft.world.level.block.state.BlockState;

public class BlockColors {
   public static final BlockColor GRASS = (state, reader, pos, tint) -> reader != null && pos != null
      ? BiomeColors.getAverageGrassColor(reader, pos)
      : defaultGrassColor();
   public static final BlockColor FOLIAGE = (state, reader, pos, tint) -> reader != null && pos != null
      ? BiomeColors.getAverageFoliageColor(reader, pos)
      : defaultFoliageColor();
   public static final BlockColor WATER = (state, reader, pos, tint) -> reader != null && pos != null
      ? BiomeColors.getAverageWaterColor(reader, pos)
      : defaultWaterColor();

   public BlockColors() {
   }

   public static ItemColor toItemColor(net.minecraft.client.color.block.BlockColors colors) {
      return (stack, tint) -> {
         BlockState state = ((BlockItem)stack.getItem()).getBlock().defaultBlockState();
         return colors.getColor(state, null, null, tint);
      };
   }

   private static int defaultGrassColor() {
      return GrassColor.get(0.5, 1.0);
   }

   private static int defaultFoliageColor() {
      return FoliageColor.getDefaultColor();
   }

   private static int defaultWaterColor() {
      return -1;
   }
}
