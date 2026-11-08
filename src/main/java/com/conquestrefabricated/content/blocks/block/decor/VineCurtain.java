package com.conquestrefabricated.content.blocks.block.decor;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.VineBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class VineCurtain extends VineBlock {
   public VineCurtain(Properties properties) {
      super(properties);
   }

   public boolean canSurvive(BlockState state, LevelReader reader, BlockPos pos) {
      return true;
   }

   public BlockState updateShape(BlockState stateIn, Direction facing, BlockState facingState, LevelAccessor world, BlockPos currentPos, BlockPos facingPos) {
      return stateIn;
   }

   public void randomTick(BlockState state, ServerLevel world, BlockPos pos, RandomSource random) {
   }

   public boolean canBeReplaced(BlockState state, BlockPlaceContext useContext) {
      return useContext.getItemInHand().getItem() == this.asItem();
   }

   public BlockState getStateForPlacement(BlockPlaceContext context) {
      BlockState blockstate = context.getLevel().getBlockState(context.getClickedPos());
      boolean flag = blockstate.getBlock() == this;
      BlockState blockstate1 = flag ? blockstate : this.defaultBlockState();

      for (Direction direction : context.getNearestLookingDirections()) {
         if (direction != Direction.DOWN) {
            BooleanProperty booleanproperty = getPropertyForFace(direction);
            boolean flag1 = flag && (Boolean)blockstate.getValue(booleanproperty);
            if (!flag1) {
               return (BlockState)blockstate1.setValue(booleanproperty, true);
            }
         } else if (direction == Direction.DOWN) {
            Direction direction1 = context.getHorizontalDirection();
            BooleanProperty booleanproperty = getPropertyForFace(direction1);
            boolean flag1 = flag && (Boolean)blockstate.getValue(booleanproperty);
            if (!flag1) {
               return (BlockState)blockstate1.setValue(booleanproperty, true);
            }
         }
      }

      return flag ? blockstate1 : null;
   }

   public VoxelShape getCollisionShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
      return Shapes.empty();
   }
}
