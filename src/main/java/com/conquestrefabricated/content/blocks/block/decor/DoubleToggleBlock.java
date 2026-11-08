package com.conquestrefabricated.content.blocks.block.decor;

import com.conquestrefabricated.core.asset.annotation.ItemDescription;
import com.conquestrefabricated.core.block.builder.Props;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;

@ItemDescription(description = "toggle_2")
public class DoubleToggleBlock extends DoubleBlock {
   public static final BooleanProperty OPEN = BooleanProperty.create("open");

   public DoubleToggleBlock(Props props) {
      super(props);
      this.registerDefaultState((BlockState)((BlockState)((BlockState)this.stateDefinition.any()).setValue(HALF, DoubleBlockHalf.LOWER)).setValue(OPEN, false));
   }

   public InteractionResult use(BlockState state, Level level, BlockPos blockPos, Player player, InteractionHand hand, BlockHitResult hitResult) {
      if ((Boolean)state.getValue(OPEN)) {
         state = (BlockState)state.setValue(OPEN, false);
         level.setBlock(blockPos, state, 10);
      } else {
         state = (BlockState)state.setValue(OPEN, true);
         level.setBlock(blockPos, state, 10);
      }

      boolean flag = (Boolean)state.getValue(OPEN);
      level.levelEvent(player, flag ? 1008 : 1014, blockPos, 0);
      level.gameEvent(player, flag ? GameEvent.BLOCK_OPEN : GameEvent.BLOCK_CLOSE, blockPos);
      return InteractionResult.sidedSuccess(level.isClientSide);
   }

   @Override
   protected void createBlockStateDefinition(Builder<Block, BlockState> builder) {
      builder.add(new Property[]{HALF, OPEN});
   }
}
