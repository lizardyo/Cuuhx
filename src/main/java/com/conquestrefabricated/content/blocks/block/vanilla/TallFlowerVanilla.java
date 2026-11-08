package com.conquestrefabricated.content.blocks.block.vanilla;

import com.conquestrefabricated.content.blocks.block.Layer;
import com.conquestrefabricated.content.blocks.block.Slab;
import com.conquestrefabricated.content.blocks.util.PlacementHelper;
import com.conquestrefabricated.core.block.properties.ModBlockProperties;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Direction.Axis;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.TallFlowerBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.Property;

public class TallFlowerVanilla extends TallFlowerBlock {
   public static final BooleanProperty OFFSET_TOGGLE = ModBlockProperties.OFFSET_TOGGLE;
   public static final IntegerProperty LAYERS = BlockStateProperties.LAYERS;

   public TallFlowerVanilla(Properties properties) {
      super(properties);
      this.registerDefaultState(
         (BlockState)((BlockState)((BlockState)((BlockState)this.stateDefinition.any()).setValue(HALF, DoubleBlockHalf.LOWER)).setValue(LAYERS, 8))
            .setValue(OFFSET_TOGGLE, false)
      );
   }

   public boolean mayPlaceOn(BlockState state, BlockGetter world, BlockPos pos) {
      return PlacementHelper.isDuringWorldGen(world) ? super.mayPlaceOn(state, world, pos) : true;
   }

   public boolean canSurvive(BlockState state, LevelReader worldIn, BlockPos pos) {
      if (state.getValue(HALF) != DoubleBlockHalf.UPPER) {
         return super.canSurvive(state, worldIn, pos);
      } else {
         BlockState blockstate = worldIn.getBlockState(pos.below());
         if (state.getBlock() != this) {
            return super.canSurvive(state, worldIn, pos);
         } else {
            return blockstate.getBlock() == this && blockstate.getValue(HALF) == DoubleBlockHalf.LOWER ? true : true;
         }
      }
   }

   public BlockState getStateForPlacement(BlockPlaceContext context) {
      BlockGetter iblockreader = context.getLevel();
      BlockPos blockpos = context.getClickedPos();
      BlockPos down = blockpos.below();
      BlockState blockStateDown = iblockreader.getBlockState(down);
      if (iblockreader.getBlockState(blockpos.above()).canBeReplaced(context)) {
         return !blockStateDown.hasProperty(Layer.LAYERS) && !blockStateDown.hasProperty(Slab.LAYERS) && !blockStateDown.hasProperty(LAYERS)
            ? (BlockState)super.getStateForPlacement(context).setValue(LAYERS, 8)
            : (BlockState)super.getStateForPlacement(context).setValue(LAYERS, (Integer)blockStateDown.getValue(LAYERS));
      } else {
         return null;
      }
   }

   public BlockState updateShape(BlockState stateIn, Direction facing, BlockState facingState, LevelAccessor worldIn, BlockPos currentPos, BlockPos facingPos) {
      DoubleBlockHalf doubleblockhalf = (DoubleBlockHalf)stateIn.getValue(HALF);
      BlockPos down = currentPos.below();
      BlockState blockStateDown = worldIn.getBlockState(down);
      if ((
            facing.getAxis() == Axis.Y
                  && doubleblockhalf == DoubleBlockHalf.LOWER == (facing == Direction.UP)
                  && (facingState.getBlock() != this || facingState.getValue(HALF) == doubleblockhalf)
               || !blockStateDown.hasProperty(Layer.LAYERS)
         )
         && !blockStateDown.hasProperty(Slab.LAYERS)
         && !blockStateDown.hasProperty(LAYERS)) {
         if (facing.getAxis() != Axis.Y
            || doubleblockhalf == DoubleBlockHalf.LOWER != (facing == Direction.UP)
            || facingState.getBlock() == this && facingState.getValue(HALF) != doubleblockhalf) {
            return doubleblockhalf == DoubleBlockHalf.LOWER && facing == Direction.DOWN && !stateIn.canSurvive(worldIn, currentPos)
               ? Blocks.AIR.defaultBlockState()
               : stateIn;
         } else {
            return Blocks.AIR.defaultBlockState();
         }
      } else {
         return doubleblockhalf == DoubleBlockHalf.LOWER && facing == Direction.DOWN && !stateIn.canSurvive(worldIn, currentPos)
            ? Blocks.AIR.defaultBlockState()
            : (BlockState)stateIn.setValue(LAYERS, (Integer)blockStateDown.getValue(LAYERS));
      }
   }

   protected void createBlockStateDefinition(Builder<Block, BlockState> builder) {
      builder.add(new Property[]{HALF, LAYERS, OFFSET_TOGGLE});
   }
}
