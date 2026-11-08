package com.conquestrefabricated.content.blocks.tileentity.forge;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.BlockEntityType.BlockEntitySupplier;

public class TileEntityTypesImpl {
   public TileEntityTypesImpl() {
   }

   public static <T extends BlockEntity> BlockEntityType<T> create(BlockEntitySupplier<T> factory, String name, String... blockNames) {
      Block[] blocks = new Block[blockNames.length];
      for (int i = 0; i < blockNames.length; i++) {
         ResourceLocation id = ResourceLocation.tryParse(blockNames[i]);
         Block block = id == null ? null : BuiltInRegistries.BLOCK.get(id);
         if (block == null) {
            throw new IllegalArgumentException("Block " + blockNames[i] + " is not registered correctly");
         }
         blocks[i] = block;
      }
      return BlockEntityType.Builder.of(factory, blocks).build(null);
   }
}
