package com.conquestrefabricated.content.blocks.block;

import com.conquestrefabricated.content.blocks.block.classical.corinthian.CubeCapitalCorinthian;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CrossCollisionBlock;
import net.minecraft.world.level.block.FenceGateBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class WallOld extends CrossCollisionBlock {
   public static final BooleanProperty UP = BlockStateProperties.UP;
   private final VoxelShape[] wallShapes;
   private final VoxelShape[] wallCollisionShapes;

   public WallOld(Properties properties) {
      super(0.0F, 3.0F, 0.0F, 14.0F, 24.0F, properties);
      this.registerDefaultState(
         (BlockState)((BlockState)((BlockState)((BlockState)((BlockState)((BlockState)((BlockState)this.stateDefinition.any()).setValue(UP, true))
                        .setValue(NORTH, false))
                     .setValue(EAST, false))
                  .setValue(SOUTH, false))
               .setValue(WEST, false))
            .setValue(WATERLOGGED, false)
      );
      this.wallShapes = this.makeShapes(4.0F, 3.0F, 16.0F, 0.0F, 14.0F);
      this.wallCollisionShapes = this.makeShapes(4.0F, 3.0F, 24.0F, 0.0F, 24.0F);
   }

   public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
      return state.getValue(UP) ? this.wallShapes[this.getAABBIndex(state)] : super.getShape(state, worldIn, pos, context);
   }

   public VoxelShape getCollisionShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
      return state.getValue(UP) ? this.wallCollisionShapes[this.getAABBIndex(state)] : super.getCollisionShape(state, worldIn, pos, context);
   }

   private boolean canAttachTo(BlockState blockState, boolean isSideSolid, Direction direction) {
      Block block = blockState.getBlock();
      boolean flag = blockState.is(BlockTags.WALLS)
         || block instanceof FenceGateBlock && FenceGateBlock.connectsToDirection(blockState, direction)
         || block instanceof CubeCapitalCorinthian
         || block instanceof VerticalCorner && (Integer)blockState.getValue(VerticalCorner.LAYERS) >= 3
         || block instanceof VerticalCornerLessLayers && (Integer)blockState.getValue(VerticalCornerLessLayers.LAYERS) >= 2
         || block instanceof VerticalSlab && (Integer)blockState.getValue(VerticalSlab.LAYERS) >= 3 && this.canAttachToVertSlab(blockState, direction)
         || block instanceof VerticalSlabLessLayers
            && (Integer)blockState.getValue(VerticalSlabLessLayers.LAYERS) >= 2
            && this.canAttachToVertSlab(blockState, direction)
         || block instanceof VerticalQuarter && (Integer)blockState.getValue(VerticalQuarter.LAYERS) >= 3 && this.canAttachToVertQuarter(blockState, direction)
         || block instanceof VerticalQuarterLessLayers
            && (Integer)blockState.getValue(VerticalQuarterLessLayers.LAYERS) >= 2
            && this.canAttachToVertQuarter(blockState, direction);
      return !isExceptionForConnection(blockState) && isSideSolid || flag;
   }

   private boolean canAttachToVertQuarter(BlockState state, Direction direction) {
      return state.getValue(VerticalQuarter.DIRECTION) != direction && ((Direction)state.getValue(VerticalQuarter.DIRECTION)).getCounterClockWise() != direction;
   }

   public final boolean canAttachToVertSlab(BlockState state, Direction direction) {
      return state.getValue(VerticalSlab.DIRECTION) != direction;
   }

   public BlockState getStateForPlacement(BlockPlaceContext context) {
      LevelReader iworldreader = context.getLevel();
      BlockPos blockpos = context.getClickedPos();
      FluidState ifluidstate = context.getLevel().getFluidState(context.getClickedPos());
      BlockPos blockpos1 = blockpos.north();
      BlockPos blockpos2 = blockpos.east();
      BlockPos blockpos3 = blockpos.south();
      BlockPos blockpos4 = blockpos.west();
      BlockState blockstate = iworldreader.getBlockState(blockpos1);
      BlockState blockstate1 = iworldreader.getBlockState(blockpos2);
      BlockState blockstate2 = iworldreader.getBlockState(blockpos3);
      BlockState blockstate3 = iworldreader.getBlockState(blockpos4);
      boolean flag = this.canAttachTo(blockstate, blockstate.isFaceSturdy(iworldreader, blockpos1, Direction.SOUTH), Direction.SOUTH);
      boolean flag1 = this.canAttachTo(blockstate1, blockstate1.isFaceSturdy(iworldreader, blockpos2, Direction.WEST), Direction.WEST);
      boolean flag2 = this.canAttachTo(blockstate2, blockstate2.isFaceSturdy(iworldreader, blockpos3, Direction.NORTH), Direction.NORTH);
      boolean flag3 = this.canAttachTo(blockstate3, blockstate3.isFaceSturdy(iworldreader, blockpos4, Direction.EAST), Direction.EAST);
      boolean flag4 = (!flag || flag1 || !flag2 || flag3) && (flag || !flag1 || flag2 || !flag3);
      return (BlockState)((BlockState)((BlockState)((BlockState)((BlockState)((BlockState)this.defaultBlockState()
                        .setValue(UP, flag4 || !iworldreader.isEmptyBlock(blockpos.above())))
                     .setValue(NORTH, flag))
                  .setValue(EAST, flag1))
               .setValue(SOUTH, flag2))
            .setValue(WEST, flag3))
         .setValue(WATERLOGGED, ifluidstate.getType() == Fluids.WATER);
   }

   public BlockState updateShape(BlockState stateIn, Direction facing, BlockState facingState, LevelAccessor worldIn, BlockPos currentPos, BlockPos facingPos) {
      if ((Boolean)stateIn.getValue(WATERLOGGED)) {
         worldIn.scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickDelay(worldIn));
      }

      if (facing == Direction.DOWN) {
         return super.updateShape(stateIn, facing, facingState, worldIn, currentPos, facingPos);
      }

      Direction direction = facing.getOpposite();
      boolean flag = facing == Direction.NORTH
         ? this.canAttachTo(facingState, facingState.isFaceSturdy(worldIn, facingPos, direction), direction)
         : (Boolean)stateIn.getValue(NORTH);
      boolean flag1 = facing == Direction.EAST
         ? this.canAttachTo(facingState, facingState.isFaceSturdy(worldIn, facingPos, direction), direction)
         : (Boolean)stateIn.getValue(EAST);
      boolean flag2 = facing == Direction.SOUTH
         ? this.canAttachTo(facingState, facingState.isFaceSturdy(worldIn, facingPos, direction), direction)
         : (Boolean)stateIn.getValue(SOUTH);
      boolean flag3 = facing == Direction.WEST
         ? this.canAttachTo(facingState, facingState.isFaceSturdy(worldIn, facingPos, direction), direction)
         : (Boolean)stateIn.getValue(WEST);
      boolean flag4 = (!flag || flag1 || !flag2 || flag3) && (flag || !flag1 || flag2 || !flag3);
      return (BlockState)((BlockState)((BlockState)((BlockState)((BlockState)stateIn.setValue(UP, flag4 || !worldIn.isEmptyBlock(currentPos.above())))
                  .setValue(NORTH, flag))
               .setValue(EAST, flag1))
            .setValue(SOUTH, flag2))
         .setValue(WEST, flag3);
   }

   protected void createBlockStateDefinition(Builder<Block, BlockState> builder) {
      builder.add(new Property[]{UP, NORTH, EAST, WEST, SOUTH, WATERLOGGED});
   }
}
