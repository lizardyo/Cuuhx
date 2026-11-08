package com.conquestrefabricated.content.blocks.util;

import com.conquestrefabricated.api.tags.ModTags;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.Property;

public class Interactions {
   public Interactions() {
   }

   public static InteractionResult onUseToggleItem(Player player, Level level, BlockPos blockPos, BlockState state, Property<?> property) {
      if (!player.getAbilities().mayBuild) {
         return InteractionResult.FAIL;
      } else if (player.getAbilities().instabuild) {
         level.setBlock(blockPos, (BlockState)state.cycle(property), 3);
         return InteractionResult.SUCCESS;
      } else if (player.getMainHandItem().is(ModTags.CYCLING_TOOLS)) {
         level.setBlock(blockPos, (BlockState)state.cycle(property), 3);
         return InteractionResult.SUCCESS;
      } else {
         return InteractionResult.FAIL;
      }
   }
}
