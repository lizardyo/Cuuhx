package com.conquestrefabricated.content.blocks.block.overlay_wall;

import com.conquestrefabricated.content.blocks.BlockSettingsAccessor;
import com.conquestrefabricated.content.blocks.CustomOffsetType;
import com.conquestrefabricated.content.blocks.block.Layer;
import com.conquestrefabricated.content.blocks.block.Slab;
import com.conquestrefabricated.core.asset.annotation.Render;
import com.conquestrefabricated.core.block.base.WaterloggedHorizontalDirectionalShape;
import com.conquestrefabricated.core.util.RenderLayer;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour.OffsetType;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

@Render(RenderLayer.CUTOUT)
public class OverlayVerticalQuarterThin extends WaterloggedHorizontalDirectionalShape {
   public static final IntegerProperty LAYERS = BlockStateProperties.LAYERS;
   private static final VoxelShape NORTH_SHAPE = Block.box(14.0, 0.0, 14.0, 16.0, 16.0, 16.0);
   private static final VoxelShape SOUTH_SHAPE = Block.box(0.0, 0.0, 0.0, 2.0, 16.0, 2.0);
   private static final VoxelShape EAST_SHAPE = Block.box(0.0, 0.0, 14.0, 2.0, 16.0, 16.0);
   private static final VoxelShape WEST_SHAPE = Block.box(14.0, 0.0, 0.0, 16.0, 16.0, 2.0);

   public OverlayVerticalQuarterThin(Properties properties) {
      super(((BlockSettingsAccessor)properties).setCustomOffsetter(CustomOffsetType.LAYERS_STATIC).offsetType(OffsetType.NONE).dynamicShape());
      this.registerDefaultState((BlockState)((BlockState)((BlockState)this.stateDefinition.any()).setValue(LAYERS, 8)).setValue(WATERLOGGED, false));
   }

   @Override
   public VoxelShape getShape(BlockState state) {
      switch ((Direction)state.getValue(DIRECTION)) {
         case NORTH:
         default:
            return NORTH_SHAPE;
         case SOUTH:
            return SOUTH_SHAPE;
         case WEST:
            return WEST_SHAPE;
         case EAST:
            return EAST_SHAPE;
      }
   }

   public BlockState updateShape(BlockState stateIn, Direction facing, BlockState facingState, LevelAccessor worldIn, BlockPos currentPos, BlockPos facingPos) {
      BlockPos down = currentPos.below();
      BlockState blockStateDown = worldIn.getBlockState(down);
      return !blockStateDown.hasProperty(Layer.LAYERS)
            && (
               !blockStateDown.hasProperty(Slab.LAYERS)
                  || blockStateDown.getValue(Slab.TYPE_UPDOWN) != net.minecraft.world.level.block.state.properties.Half.BOTTOM
            )
         ? (BlockState)super.updateShape(stateIn, facing, facingState, worldIn, currentPos, facingPos).setValue(LAYERS, 8)
         : (BlockState)super.updateShape(stateIn, facing, facingState, worldIn, currentPos, facingPos).setValue(LAYERS, (Integer)blockStateDown.getValue(LAYERS));
   }

   @Override
   protected void addProperties(Builder<Block, BlockState> builder) {
      super.addProperties(builder);
      builder.add(new Property[]{LAYERS});
   }

   @NotNull
   @Override
   public BlockState getStateForPlacement(BlockPlaceContext context) {
      BlockGetter iblockreader = context.getLevel();
      BlockPos blockpos = context.getClickedPos();
      BlockPos down = blockpos.below();
      BlockState blockStateDown = iblockreader.getBlockState(down);
      return !blockStateDown.hasProperty(Layer.LAYERS)
            && (
               !blockStateDown.hasProperty(Slab.LAYERS)
                  || blockStateDown.getValue(Slab.TYPE_UPDOWN) != net.minecraft.world.level.block.state.properties.Half.BOTTOM
            )
         ? (BlockState)super.getStateForPlacement(context).setValue(LAYERS, 8)
         : (BlockState)super.getStateForPlacement(context).setValue(LAYERS, (Integer)blockStateDown.getValue(LAYERS));
   }

   public static class Half extends OverlayVerticalQuarterThin {
      private static final VoxelShape NORTH_SHAPE = Block.box(14.0, 0.0, 14.0, 16.0, 8.0, 16.0);
      private static final VoxelShape SOUTH_SHAPE = Block.box(0.0, 0.0, 0.0, 2.0, 8.0, 2.0);
      private static final VoxelShape EAST_SHAPE = Block.box(0.0, 0.0, 14.0, 2.0, 8.0, 16.0);
      private static final VoxelShape WEST_SHAPE = Block.box(14.0, 0.0, 0.0, 16.0, 8.0, 2.0);

      public Half(Properties properties) {
         super(((BlockSettingsAccessor)properties).setCustomOffsetter(CustomOffsetType.LAYERS_STATIC).offsetType(OffsetType.NONE).dynamicShape());
         this.registerDefaultState((BlockState)((BlockState)((BlockState)this.stateDefinition.any()).setValue(LAYERS, 8)).setValue(WATERLOGGED, false));
      }

      @Override
      public VoxelShape getShape(BlockState state) {
         switch ((Direction)state.getValue(DIRECTION)) {
            case NORTH:
            default:
               return NORTH_SHAPE;
            case SOUTH:
               return SOUTH_SHAPE;
            case WEST:
               return WEST_SHAPE;
            case EAST:
               return EAST_SHAPE;
         }
      }
   }
}
