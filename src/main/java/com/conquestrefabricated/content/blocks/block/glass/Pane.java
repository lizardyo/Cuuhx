package com.conquestrefabricated.content.blocks.block.glass;

import com.conquestrefabricated.content.blocks.block.Slab;
import com.conquestrefabricated.content.blocks.block.SlabLessLayers;
import com.conquestrefabricated.content.blocks.block.VerticalCorner;
import com.conquestrefabricated.content.blocks.block.VerticalCornerLessLayers;
import com.conquestrefabricated.content.blocks.block.VerticalQuarter;
import com.conquestrefabricated.content.blocks.block.VerticalQuarterLessLayers;
import com.conquestrefabricated.content.blocks.block.VerticalSlab;
import com.conquestrefabricated.content.blocks.block.VerticalSlabLessLayers;
import com.conquestrefabricated.content.blocks.block.arch.Arch;
import com.conquestrefabricated.content.blocks.block.arch.ArchHalf;
import com.conquestrefabricated.content.blocks.block.arch.ArchSmall;
import com.conquestrefabricated.content.blocks.block.arch.ArchTwoMeter;
import com.conquestrefabricated.content.blocks.block.decor.FenceLayered;
import com.conquestrefabricated.core.asset.annotation.Assets;
import com.conquestrefabricated.core.asset.annotation.Model;
import com.conquestrefabricated.core.asset.annotation.Render;
import com.conquestrefabricated.core.asset.annotation.State;
import com.conquestrefabricated.core.util.RenderLayer;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.IronBarsBlock;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.Half;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.block.state.properties.SlabType;
import net.minecraft.world.level.block.state.properties.StairsShape;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;

@Assets(
   state = @State(name = "%s_pane", template = "parent_pane"),
   item = @Model(name = "item/%s_pane", parent = "block/%s_pane_ns", template = "item/parent_pane"),
   render = @Render(RenderLayer.CUTOUT),
   block = {
         @Model(name = "block/%s_pane_n", template = "block/parent_flatpane_n"),
         @Model(name = "block/%s_pane_ne", template = "block/parent_flatpane_ne"),
         @Model(name = "block/%s_pane_ns", template = "block/parent_flatpane_ns"),
         @Model(name = "block/%s_pane_nse", template = "block/parent_flatpane_nse"),
         @Model(name = "block/%s_pane_nsew", template = "block/parent_flatpane_nsew"),
         @Model(name = "block/%s_pane_post", template = "block/parent_flatpane_post"),
         @Model(name = "block/%s_pane_n_up", template = "block/parent_flatpane_n_up"),
         @Model(name = "block/%s_pane_ne_up", template = "block/parent_flatpane_ne_up"),
         @Model(name = "block/%s_pane_ns_up", template = "block/parent_flatpane_ns_up"),
         @Model(name = "block/%s_pane_nse_up", template = "block/parent_flatpane_nse_up"),
         @Model(name = "block/%s_pane_nsew_up", template = "block/parent_flatpane_nsew_up"),
         @Model(name = "block/%s_pane_post_up", template = "block/parent_flatpane_post_up"),
         @Model(name = "block/%s_pane_n_updown", template = "block/parent_flatpane_n_updown"),
         @Model(name = "block/%s_pane_ne_updown", template = "block/parent_flatpane_ne_updown"),
         @Model(name = "block/%s_pane_ns_updown", template = "block/parent_flatpane_ns_updown"),
         @Model(name = "block/%s_pane_nse_updown", template = "block/parent_flatpane_nse_updown"),
         @Model(name = "block/%s_pane_nsew_updown", template = "block/parent_flatpane_nsew_updown"),
         @Model(name = "block/%s_pane_post_updown", template = "block/parent_flatpane_post_updown"),
         @Model(name = "block/%s_pane_n_down", template = "block/parent_flatpane_n_down"),
         @Model(name = "block/%s_pane_ne_down", template = "block/parent_flatpane_ne_down"),
         @Model(name = "block/%s_pane_ns_down", template = "block/parent_flatpane_ns_down"),
         @Model(name = "block/%s_pane_nse_down", template = "block/parent_flatpane_nse_down"),
         @Model(name = "block/%s_pane_nsew_down", template = "block/parent_flatpane_nsew_down"),
         @Model(name = "block/%s_pane_post_down", template = "block/parent_flatpane_post_down")
   }
)
public class Pane extends IronBarsBlock {
   public static final BooleanProperty UP = BlockStateProperties.UP;
   public static final BooleanProperty DOWN = BlockStateProperties.DOWN;

   public Pane(Properties properties) {
      super(properties);
   }

   public BlockState updateShape(BlockState stateIn, Direction facing, BlockState facingState, LevelAccessor world, BlockPos currentPos, BlockPos facingPos) {
      if ((Boolean)stateIn.getValue(WATERLOGGED)) {
         world.scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickDelay(world));
      }

      return facing.getAxis().isHorizontal()
         ? (BlockState)((BlockState)((BlockState)stateIn.setValue(
                  (Property)PROPERTY_BY_DIRECTION.get(facing), this.canAttach(facingState, facingState.isFaceSturdy(world, facingPos, facing.getOpposite()), facing.getOpposite())
               ))
               .setValue(UP, this.canAttachAbove(world, currentPos.above(), stateIn)))
            .setValue(DOWN, this.canAttachBelow(world, currentPos.below(), stateIn))
         : (BlockState)((BlockState)super.updateShape(stateIn, facing, facingState, world, currentPos, facingPos)
               .setValue(UP, this.canAttachAbove(world, currentPos.above(), stateIn)))
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
      BlockPos blockposUp = blockpos.above();
      BlockState blockstateUp = iblockreader.getBlockState(blockposUp);
      BlockPos blockposDown = blockpos.below();
      BlockState blockstateDown = iblockreader.getBlockState(blockposDown);
      boolean attachedNorth = this.canAttach(blockstateNorth, blockstateNorth.isFaceSturdy(iblockreader, blockposNorth, Direction.SOUTH), Direction.SOUTH);
      boolean attachedEast = this.canAttach(blockstateEast, blockstateEast.isFaceSturdy(iblockreader, blockposEast, Direction.WEST), Direction.WEST);
      boolean attachedSouth = this.canAttach(blockstateSouth, blockstateSouth.isFaceSturdy(iblockreader, blockposSouth, Direction.NORTH), Direction.NORTH);
      boolean attachedWest = this.canAttach(blockstateWest, blockstateWest.isFaceSturdy(iblockreader, blockposWest, Direction.EAST), Direction.EAST);
      return (BlockState)((BlockState)((BlockState)((BlockState)((BlockState)((BlockState)((BlockState)this.defaultBlockState().setValue(NORTH, attachedNorth))
                        .setValue(EAST, attachedEast))
                     .setValue(SOUTH, attachedSouth))
                  .setValue(WEST, attachedWest))
               .setValue(WATERLOGGED, ifluidstate.getType() == Fluids.WATER))
            .setValue(UP, this.canAttachAbove(blockstateUp, attachedNorth, attachedEast, attachedSouth, attachedWest)))
         .setValue(DOWN, this.canAttachBelow(blockstateDown, attachedNorth, attachedEast, attachedSouth, attachedWest));
   }

   public final boolean canAttachAbove(BlockState state, boolean attachedNorth, boolean attachedEast, boolean attachedSouth, boolean attachedWest) {
      Block block = state.getBlock();
      return block instanceof StairBlock
            && state.getValue(StairBlock.HALF) == Half.TOP
            && state.getValue(StairBlock.SHAPE) == StairsShape.STRAIGHT
            && this.canAttachToStair(state, attachedNorth, attachedEast, attachedSouth, attachedWest)
         || block instanceof SlabLessLayers && state.getValue(SlabLessLayers.TYPE_UPDOWN) == Half.TOP
         || block instanceof Slab && state.getValue(Slab.TYPE_UPDOWN) == Half.TOP
         || block instanceof SlabBlock && state.getValue(SlabBlock.TYPE) == SlabType.TOP
         || block instanceof Arch
         || block instanceof ArchSmall
         || block instanceof ArchTwoMeter
         || block instanceof ArchHalf;
   }

   public final boolean canAttachBelow(BlockState state, boolean attachedNorth, boolean attachedEast, boolean attachedSouth, boolean attachedWest) {
      Block block = state.getBlock();
      return block instanceof StairBlock
            && state.getValue(StairBlock.HALF) == Half.BOTTOM
            && state.getValue(StairBlock.SHAPE) == StairsShape.STRAIGHT
            && this.canAttachToStair(state, attachedNorth, attachedEast, attachedSouth, attachedWest)
         || block instanceof SlabLessLayers && state.getValue(SlabLessLayers.TYPE_UPDOWN) == Half.BOTTOM
         || block instanceof Slab && state.getValue(Slab.TYPE_UPDOWN) == Half.BOTTOM
         || block instanceof SlabBlock && state.getValue(SlabBlock.TYPE) == SlabType.BOTTOM
         || block instanceof Arch
         || block instanceof ArchSmall
         || block instanceof ArchTwoMeter
         || block instanceof ArchHalf;
   }

   private boolean canAttachToStair(BlockState state, boolean attachedNorth, boolean attachedEast, boolean attachedSouth, boolean attachedWest) {
      switch ((Direction)state.getValue(StairBlock.FACING)) {
         case NORTH:
         case SOUTH:
         default:
            return !attachedEast && !attachedWest;
         case EAST:
         case WEST:
            return !attachedNorth && !attachedSouth;
      }
   }

   private boolean canAttachToStair(BlockState state, BlockState stateIn) {
      switch ((Direction)state.getValue(StairBlock.FACING)) {
         case NORTH:
         case SOUTH:
         default:
            return !(Boolean)stateIn.getValue(IronBarsBlock.EAST) && !(Boolean)stateIn.getValue(IronBarsBlock.WEST);
         case EAST:
         case WEST:
            return !(Boolean)stateIn.getValue(IronBarsBlock.NORTH) && !(Boolean)stateIn.getValue(IronBarsBlock.SOUTH);
      }
   }

   public final boolean canAttachAbove(LevelAccessor world, BlockPos pos, BlockState stateIn) {
      BlockState state = world.getBlockState(pos);
      Block block = state.getBlock();
      return block instanceof StairBlock
            && state.getValue(StairBlock.HALF) == Half.TOP
            && state.getValue(StairBlock.SHAPE) == StairsShape.STRAIGHT
            && this.canAttachToStair(state, stateIn)
         || block instanceof SlabLessLayers && state.getValue(SlabLessLayers.TYPE_UPDOWN) == Half.TOP
         || block instanceof Slab && state.getValue(Slab.TYPE_UPDOWN) == Half.TOP
         || block instanceof SlabBlock && state.getValue(SlabBlock.TYPE) == SlabType.TOP
         || block instanceof Arch
         || block instanceof ArchSmall
         || block instanceof ArchTwoMeter
         || block instanceof ArchHalf;
   }

   public final boolean canAttachBelow(LevelAccessor world, BlockPos pos, BlockState stateIn) {
      BlockState state = world.getBlockState(pos);
      Block block = state.getBlock();
      return block instanceof StairBlock
            && state.getValue(StairBlock.HALF) == Half.BOTTOM
            && state.getValue(StairBlock.SHAPE) == StairsShape.STRAIGHT
            && this.canAttachToStair(state, stateIn)
         || block instanceof SlabLessLayers && state.getValue(SlabLessLayers.TYPE_UPDOWN) == Half.BOTTOM
         || block instanceof Slab && state.getValue(Slab.TYPE_UPDOWN) == Half.BOTTOM
         || block instanceof SlabBlock && state.getValue(SlabBlock.TYPE) == SlabType.BOTTOM
         || block instanceof Arch
         || block instanceof ArchSmall
         || block instanceof ArchTwoMeter
         || block instanceof ArchHalf;
   }

   public final boolean canAttach(BlockState state, boolean bool, Direction direction) {
      Block block = state.getBlock();
      return !isExceptionForConnection(state) && bool
         || block instanceof IronBarsBlock
         || state.getBlock() instanceof FenceLayered
         || state.getBlock() instanceof FenceLayered.Half
         || block instanceof VerticalCorner && (Integer)state.getValue(VerticalCorner.LAYERS) >= 3
         || block instanceof VerticalCornerLessLayers && (Integer)state.getValue(VerticalCornerLessLayers.LAYERS) >= 2
         || block instanceof VerticalSlab && (Integer)state.getValue(VerticalSlab.LAYERS) >= 3 && this.canAttachToVertSlab(state, direction)
         || block instanceof VerticalSlabLessLayers
            && (Integer)state.getValue(VerticalSlabLessLayers.LAYERS) >= 2
            && this.canAttachToVertSlab(state, direction)
         || block instanceof VerticalQuarter && (Integer)state.getValue(VerticalQuarter.LAYERS) >= 3 && this.canAttachToVertQuarter(state, direction)
         || block instanceof VerticalQuarterLessLayers
            && (Integer)state.getValue(VerticalQuarterLessLayers.LAYERS) >= 2
            && this.canAttachToVertQuarter(state, direction)
         || block instanceof StairBlock
         || block instanceof Slab
         || block instanceof SlabBlock
         || block instanceof Arch
         || block instanceof ArchSmall
         || block instanceof ArchTwoMeter
         || block instanceof ArchHalf;
   }

   private boolean canAttachToVertQuarter(BlockState state, Direction direction) {
      return state.getValue(VerticalQuarter.DIRECTION) != direction && ((Direction)state.getValue(VerticalQuarter.DIRECTION)).getCounterClockWise() != direction;
   }

   public final boolean canAttachToVertSlab(BlockState state, Direction direction) {
      return state.getValue(VerticalSlab.DIRECTION) != direction;
   }

   protected void createBlockStateDefinition(Builder<Block, BlockState> builder) {
      builder.add(new Property[]{NORTH, EAST, WEST, SOUTH, UP, DOWN, WATERLOGGED});
   }
}
