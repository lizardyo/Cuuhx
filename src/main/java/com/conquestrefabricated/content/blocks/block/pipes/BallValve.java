package com.conquestrefabricated.content.blocks.block.pipes;

import com.conquestrefabricated.content.blocks.BlockVoxelShapes;
import com.conquestrefabricated.content.blocks.block.directional.HorizontalDirectional;
import com.conquestrefabricated.content.blocks.util.Interactions;
import com.conquestrefabricated.core.block.builder.Props;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.VoxelShape;

public class BallValve extends HorizontalDirectional.Waterlogged.Toggle4 {
   public static final IntegerProperty VALVE = IntegerProperty.create("valve", 1, 2);

   public BallValve(Props props) {
      super(props);
   }

   @Override
   public InteractionResult use(BlockState state, Level level, BlockPos blockPos, Player player, InteractionHand hand, BlockHitResult hitResult) {
      if (!player.getAbilities().mayBuild) {
         return InteractionResult.FAIL;
      } else if (player.isShiftKeyDown()) {
         level.setBlock(blockPos, (BlockState)state.cycle(VALVE), 3);
         return InteractionResult.SUCCESS;
      } else {
         return Interactions.onUseToggleItem(player, level, blockPos, state, TOGGLE);
      }
   }

   @Override
   protected void addProperties(Builder<Block, BlockState> builder) {
      builder.add(new Property[]{VALVE, TOGGLE});
   }

   @Override
   public VoxelShape getShape(BlockState state) {
      if ((Integer)state.getValue(TOGGLE) == 1) {
         return BlockVoxelShapes.pipeShape;
      }

      switch ((Direction)state.getValue(DIRECTION)) {
         case NORTH:
         case SOUTH:
         default:
            return BlockVoxelShapes.pipeShapeHorizontal_NS;
         case EAST:
         case WEST:
            return BlockVoxelShapes.pipeShapeHorizontal_EW;
      }
   }
}
