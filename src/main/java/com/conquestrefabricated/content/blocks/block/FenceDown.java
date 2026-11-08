package com.conquestrefabricated.content.blocks.block;

import com.conquestrefabricated.content.blocks.block.decor.FenceLayered;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.IronBarsBlock;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.SnowLayerBlock;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.Half;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.block.state.properties.SlabType;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;

public class FenceDown extends IronBarsBlock {
   public static final BooleanProperty DOWN = BlockStateProperties.DOWN;

   public FenceDown(Properties properties) {
      super(properties);
   }

   public BlockState updateShape(BlockState stateIn, Direction facing, BlockState facingState, LevelAccessor world, BlockPos currentPos, BlockPos facingPos) {
      if ((Boolean)stateIn.getValue(WATERLOGGED)) {
         world.scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickDelay(world));
      }

      return facing.getAxis().isHorizontal()
         ? (BlockState)((BlockState)stateIn.setValue(
               (Property)PROPERTY_BY_DIRECTION.get(facing), this.canAttach(facingState, facingState.isFaceSturdy(world, facingPos, facing.getOpposite()), facing.getOpposite())
            ))
            .setValue(DOWN, this.canAttachBelow(world, currentPos.below(), stateIn))
         : (BlockState)super.updateShape(stateIn, facing, facingState, world, currentPos, facingPos)
            .setValue(DOWN, this.canAttachBelow(world, currentPos.below(), stateIn));
   }

   public BlockState getStateForPlacement(BlockPlaceContext context) {
      BlockGetter iblockreader = context.getLevel();
      BlockPos blockpos = context.getClickedPos();
      FluidState ifluidstate = context.getLevel().getFluidState(context.getClickedPos());
      BlockPos blockposNorth = blockpos.north();
      BlockPos blockposSouth = blockpos.south();
      BlockPos blockposWest = blockpos.west();
      BlockPos blockposEast = blockpos.east();
      BlockState blockstateNorth = iblockreader.getBlockState(blockposNorth);
      BlockState blockstateSouth = iblockreader.getBlockState(blockposSouth);
      BlockState blockstateWest = iblockreader.getBlockState(blockposWest);
      BlockState blockstateEast = iblockreader.getBlockState(blockposEast);
      BlockPos blockposDown = blockpos.below();
      BlockState blockstateDown = iblockreader.getBlockState(blockposDown);
      boolean attachedNorth = this.canAttach(blockstateNorth, blockstateNorth.isFaceSturdy(iblockreader, blockposNorth, Direction.SOUTH), Direction.SOUTH);
      boolean attachedEast = this.canAttach(blockstateEast, blockstateEast.isFaceSturdy(iblockreader, blockposEast, Direction.WEST), Direction.WEST);
      boolean attachedSouth = this.canAttach(blockstateSouth, blockstateSouth.isFaceSturdy(iblockreader, blockposSouth, Direction.NORTH), Direction.NORTH);
      boolean attachedWest = this.canAttach(blockstateWest, blockstateWest.isFaceSturdy(iblockreader, blockposWest, Direction.EAST), Direction.EAST);
      return (BlockState)((BlockState)((BlockState)((BlockState)((BlockState)((BlockState)this.defaultBlockState().setValue(NORTH, attachedNorth))
                     .setValue(EAST, attachedEast))
                  .setValue(SOUTH, attachedSouth))
               .setValue(WEST, attachedWest))
            .setValue(WATERLOGGED, ifluidstate.getType() == Fluids.WATER))
         .setValue(DOWN, this.canAttachBelow(blockstateDown));
   }

   public final boolean canAttach(BlockState state, boolean bool, Direction direction) {
      Block block = state.getBlock();
      return !isExceptionForConnection(state) && bool
         || block instanceof IronBarsBlock
         || state.getBlock() instanceof FenceLayered
         || state.getBlock() instanceof FenceLayered.Half
         || block instanceof VerticalCorner && (Integer)state.getValue(VerticalCorner.LAYERS) >= 3
         || block instanceof VerticalCornerLessLayers && (Integer)state.getValue(VerticalCornerLessLayers.LAYERS) >= 2;
   }

   public final boolean canAttachBelow(BlockState state) {
      Block block = state.getBlock();
      return block instanceof StairBlock && state.getValue(StairBlock.HALF) == Half.BOTTOM
         || block instanceof SlabLessLayers && state.getValue(SlabLessLayers.TYPE_UPDOWN) == Half.BOTTOM
         || block instanceof Slab && state.getValue(Slab.TYPE_UPDOWN) == Half.BOTTOM
         || block instanceof SlabBlock && state.getValue(SlabBlock.TYPE) == SlabType.BOTTOM
         || block instanceof Layer
         || block instanceof SnowLayerBlock;
   }

   public final boolean canAttachBelow(LevelAccessor world, BlockPos pos, BlockState stateIn) {
      BlockState state = world.getBlockState(pos);
      Block block = state.getBlock();
      return block instanceof StairBlock && state.getValue(StairBlock.HALF) == Half.BOTTOM
         || block instanceof SlabLessLayers && state.getValue(SlabLessLayers.TYPE_UPDOWN) == Half.BOTTOM
         || block instanceof Slab && state.getValue(Slab.TYPE_UPDOWN) == Half.BOTTOM
         || block instanceof SlabBlock && state.getValue(SlabBlock.TYPE) == SlabType.BOTTOM
         || block instanceof Layer
         || block instanceof SnowLayerBlock;
   }

   protected void createBlockStateDefinition(Builder<Block, BlockState> builder) {
      builder.add(new Property[]{NORTH, EAST, WEST, SOUTH, DOWN, WATERLOGGED});
   }
}
