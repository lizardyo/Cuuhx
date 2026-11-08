package com.conquestrefabricated.content.blocks.block.decor;

import com.conquestrefabricated.content.blocks.BlockSettingsAccessor;
import com.conquestrefabricated.content.blocks.CustomOffsetType;
import com.conquestrefabricated.content.blocks.block.Layer;
import com.conquestrefabricated.content.blocks.block.Slab;
import com.conquestrefabricated.core.asset.annotation.Assets;
import com.conquestrefabricated.core.asset.annotation.Model;
import com.conquestrefabricated.core.asset.annotation.Render;
import com.conquestrefabricated.core.asset.annotation.State;
import com.conquestrefabricated.core.util.RenderLayer;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CrossCollisionBlock;
import net.minecraft.world.level.block.FenceGateBlock;
import net.minecraft.world.level.block.IronBarsBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour.OffsetType;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;

@Assets(
   state = @State(name = "%s", template = "parent_pane_old"),
   render = @Render(RenderLayer.CUTOUT),
   item = @Model(name = "item/%s", parent = "block/%s_pane_ns", template = "item/parent_pane"),
   block = {
         @Model(name = "block/%s_pane_n", template = "block/parent_flatpane_n"),
         @Model(name = "block/%s_pane_ne", template = "block/parent_flatpane_ne"),
         @Model(name = "block/%s_pane_ns", template = "block/parent_flatpane_ns"),
         @Model(name = "block/%s_pane_nse", template = "block/parent_flatpane_nse"),
         @Model(name = "block/%s_pane_nsew", template = "block/parent_flatpane_nsew"),
         @Model(name = "block/%s_pane_post", template = "block/parent_flatpane_post")
   }
)
public class FenceLayered extends CrossCollisionBlock {
   public static final IntegerProperty LAYERS = BlockStateProperties.LAYERS;

   public FenceLayered(Properties properties) {
      super(
         1.0F,
         1.0F,
         16.0F,
         16.0F,
         22.0F,
         ((BlockSettingsAccessor)properties).setCustomOffsetter(CustomOffsetType.LAYERS_STATIC).offsetType(OffsetType.NONE).dynamicShape()
      );
      ((BlockState)((BlockState)((BlockState)((BlockState)((BlockState)((BlockState)this.stateDefinition.any()).setValue(NORTH, false))
                     .setValue(EAST, false))
                  .setValue(SOUTH, false))
               .setValue(WEST, false))
            .setValue(LAYERS, 8))
         .setValue(WATERLOGGED, false);
   }

   public boolean canConnectTo(BlockState state, boolean sideSolidFullSquare, Direction dir) {
      boolean bl2 = state.getBlock() instanceof FenceGateBlock && FenceGateBlock.connectsToDirection(state, dir);
      return !isExceptionForConnection(state) && sideSolidFullSquare
         || state.getBlock() instanceof IronBarsBlock
         || state.getBlock() instanceof FenceLayered
         || state.getBlock() instanceof FenceLayered.Half
         || state.is(BlockTags.WALLS)
         || bl2;
   }

   public BlockState getStateForPlacement(BlockPlaceContext ctx) {
      BlockGetter blockView = ctx.getLevel();
      BlockPos blockPos = ctx.getClickedPos();
      BlockPos down = blockPos.below();
      BlockState blockStateDown = blockView.getBlockState(down);
      FluidState fluidState = ctx.getLevel().getFluidState(ctx.getClickedPos());
      BlockPos blockPos2 = blockPos.north();
      BlockPos blockPos3 = blockPos.south();
      BlockPos blockPos4 = blockPos.west();
      BlockPos blockPos5 = blockPos.east();
      BlockState blockState = blockView.getBlockState(blockPos2);
      BlockState blockState2 = blockView.getBlockState(blockPos3);
      BlockState blockState3 = blockView.getBlockState(blockPos4);
      BlockState blockState4 = blockView.getBlockState(blockPos5);
      return !blockStateDown.hasProperty(Layer.LAYERS) && !blockStateDown.hasProperty(Slab.LAYERS)
         ? (BlockState)((BlockState)((BlockState)((BlockState)((BlockState)((BlockState)this.defaultBlockState().setValue(LAYERS, 8))
                        .setValue(NORTH, this.canConnectTo(blockState, blockState.isFaceSturdy(blockView, blockPos2, Direction.SOUTH), Direction.SOUTH)))
                     .setValue(SOUTH, this.canConnectTo(blockState2, blockState2.isFaceSturdy(blockView, blockPos3, Direction.NORTH), Direction.NORTH)))
                  .setValue(WEST, this.canConnectTo(blockState3, blockState3.isFaceSturdy(blockView, blockPos4, Direction.EAST), Direction.EAST)))
               .setValue(EAST, this.canConnectTo(blockState4, blockState4.isFaceSturdy(blockView, blockPos5, Direction.WEST), Direction.WEST)))
            .setValue(WATERLOGGED, fluidState.getType() == Fluids.WATER)
         : (BlockState)((BlockState)((BlockState)((BlockState)((BlockState)((BlockState)this.defaultBlockState()
                           .setValue(LAYERS, (Integer)blockStateDown.getValue(LAYERS)))
                        .setValue(NORTH, this.canConnectTo(blockState, blockState.isFaceSturdy(blockView, blockPos2, Direction.SOUTH), Direction.SOUTH)))
                     .setValue(SOUTH, this.canConnectTo(blockState2, blockState2.isFaceSturdy(blockView, blockPos3, Direction.NORTH), Direction.NORTH)))
                  .setValue(WEST, this.canConnectTo(blockState3, blockState3.isFaceSturdy(blockView, blockPos4, Direction.EAST), Direction.EAST)))
               .setValue(EAST, this.canConnectTo(blockState4, blockState4.isFaceSturdy(blockView, blockPos5, Direction.WEST), Direction.WEST)))
            .setValue(WATERLOGGED, fluidState.getType() == Fluids.WATER);
   }

   public BlockState updateShape(BlockState state, Direction direction, BlockState neighborState, LevelAccessor world, BlockPos currentPos, BlockPos neighborPos) {
      BlockPos down = currentPos.below();
      BlockState blockStateDown = world.getBlockState(down);
      if ((Boolean)state.getValue(WATERLOGGED)) {
         world.scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickDelay(world));
      }

      if (!blockStateDown.hasProperty(Layer.LAYERS) && !blockStateDown.hasProperty(Slab.LAYERS)) {
         return direction.getAxis().isHorizontal()
            ? (BlockState)((BlockState)state.setValue(
                  (Property)PROPERTY_BY_DIRECTION.get(direction),
                  this.canConnectTo(neighborState, neighborState.isFaceSturdy(world, neighborPos, direction.getOpposite()), direction)
               ))
               .setValue(LAYERS, 8)
            : (BlockState)state.setValue(LAYERS, 8);
      } else {
         return direction.getAxis().isHorizontal()
            ? (BlockState)((BlockState)state.setValue(
                  (Property)PROPERTY_BY_DIRECTION.get(direction),
                  this.canConnectTo(neighborState, neighborState.isFaceSturdy(world, neighborPos, direction.getOpposite()), direction)
               ))
               .setValue(LAYERS, (Integer)blockStateDown.getValue(LAYERS))
            : (BlockState)state.setValue(LAYERS, (Integer)blockStateDown.getValue(LAYERS));
      }
   }

   protected void createBlockStateDefinition(Builder<Block, BlockState> builder) {
      builder.add(new Property[]{NORTH, EAST, WEST, SOUTH, LAYERS, WATERLOGGED});
   }

   public static class Half extends CrossCollisionBlock {
      @Render(RenderLayer.CUTOUT)
      public Half(Properties properties) {
         super(
            1.0F,
            1.0F,
            8.0F,
            8.0F,
            12.0F,
            ((BlockSettingsAccessor)properties).setCustomOffsetter(CustomOffsetType.LAYERS_STATIC).offsetType(OffsetType.NONE).dynamicShape()
         );
         ((BlockState)((BlockState)((BlockState)((BlockState)((BlockState)((BlockState)this.stateDefinition.any()).setValue(NORTH, false))
                        .setValue(EAST, false))
                     .setValue(SOUTH, false))
                  .setValue(WEST, false))
               .setValue(FenceLayered.LAYERS, 8))
            .setValue(WATERLOGGED, false);
      }

      public boolean canConnectTo(BlockState state, boolean sideSolidFullSquare, Direction dir) {
         boolean bl2 = state.getBlock() instanceof FenceGateBlock && FenceGateBlock.connectsToDirection(state, dir);
         return !isExceptionForConnection(state) && sideSolidFullSquare
            || state.getBlock() instanceof IronBarsBlock
            || state.getBlock() instanceof FenceLayered
            || state.getBlock() instanceof FenceLayered.Half
            || state.is(BlockTags.WALLS)
            || bl2;
      }

      public BlockState getStateForPlacement(BlockPlaceContext ctx) {
         BlockGetter blockView = ctx.getLevel();
         BlockPos blockPos = ctx.getClickedPos();
         BlockPos down = blockPos.below();
         BlockState blockStateDown = blockView.getBlockState(down);
         FluidState fluidState = ctx.getLevel().getFluidState(ctx.getClickedPos());
         BlockPos blockPos2 = blockPos.north();
         BlockPos blockPos3 = blockPos.south();
         BlockPos blockPos4 = blockPos.west();
         BlockPos blockPos5 = blockPos.east();
         BlockState blockState = blockView.getBlockState(blockPos2);
         BlockState blockState2 = blockView.getBlockState(blockPos3);
         BlockState blockState3 = blockView.getBlockState(blockPos4);
         BlockState blockState4 = blockView.getBlockState(blockPos5);
         return !blockStateDown.hasProperty(Layer.LAYERS) && !blockStateDown.hasProperty(Slab.LAYERS)
            ? (BlockState)((BlockState)((BlockState)((BlockState)((BlockState)((BlockState)this.defaultBlockState().setValue(FenceLayered.LAYERS, 8))
                           .setValue(NORTH, this.canConnectTo(blockState, blockState.isFaceSturdy(blockView, blockPos2, Direction.SOUTH), Direction.SOUTH)))
                        .setValue(SOUTH, this.canConnectTo(blockState2, blockState2.isFaceSturdy(blockView, blockPos3, Direction.NORTH), Direction.NORTH)))
                     .setValue(WEST, this.canConnectTo(blockState3, blockState3.isFaceSturdy(blockView, blockPos4, Direction.EAST), Direction.EAST)))
                  .setValue(EAST, this.canConnectTo(blockState4, blockState4.isFaceSturdy(blockView, blockPos5, Direction.WEST), Direction.WEST)))
               .setValue(WATERLOGGED, fluidState.getType() == Fluids.WATER)
            : (BlockState)((BlockState)((BlockState)((BlockState)((BlockState)((BlockState)this.defaultBlockState()
                              .setValue(FenceLayered.LAYERS, (Integer)blockStateDown.getValue(FenceLayered.LAYERS)))
                           .setValue(NORTH, this.canConnectTo(blockState, blockState.isFaceSturdy(blockView, blockPos2, Direction.SOUTH), Direction.SOUTH)))
                        .setValue(SOUTH, this.canConnectTo(blockState2, blockState2.isFaceSturdy(blockView, blockPos3, Direction.NORTH), Direction.NORTH)))
                     .setValue(WEST, this.canConnectTo(blockState3, blockState3.isFaceSturdy(blockView, blockPos4, Direction.EAST), Direction.EAST)))
                  .setValue(EAST, this.canConnectTo(blockState4, blockState4.isFaceSturdy(blockView, blockPos5, Direction.WEST), Direction.WEST)))
               .setValue(WATERLOGGED, fluidState.getType() == Fluids.WATER);
      }

      public BlockState updateShape(BlockState state, Direction direction, BlockState neighborState, LevelAccessor world, BlockPos currentPos, BlockPos neighborPos) {
         BlockPos down = currentPos.below();
         BlockState blockStateDown = world.getBlockState(down);
         if ((Boolean)state.getValue(WATERLOGGED)) {
            world.scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickDelay(world));
         }

         if (!blockStateDown.hasProperty(Layer.LAYERS) && !blockStateDown.hasProperty(Slab.LAYERS)) {
            return direction.getAxis().isHorizontal()
               ? (BlockState)((BlockState)state.setValue(
                     (Property)PROPERTY_BY_DIRECTION.get(direction),
                     this.canConnectTo(neighborState, neighborState.isFaceSturdy(world, neighborPos, direction.getOpposite()), direction)
                  ))
                  .setValue(FenceLayered.LAYERS, 8)
               : (BlockState)state.setValue(FenceLayered.LAYERS, 8);
         } else {
            return direction.getAxis().isHorizontal()
               ? (BlockState)((BlockState)state.setValue(
                     (Property)PROPERTY_BY_DIRECTION.get(direction),
                     this.canConnectTo(neighborState, neighborState.isFaceSturdy(world, neighborPos, direction.getOpposite()), direction)
                  ))
                  .setValue(FenceLayered.LAYERS, (Integer)blockStateDown.getValue(FenceLayered.LAYERS))
               : (BlockState)state.setValue(FenceLayered.LAYERS, (Integer)blockStateDown.getValue(FenceLayered.LAYERS));
         }
      }

      protected void createBlockStateDefinition(Builder<Block, BlockState> builder) {
         builder.add(new Property[]{NORTH, EAST, WEST, SOUTH, FenceLayered.LAYERS, WATERLOGGED});
      }
   }
}
