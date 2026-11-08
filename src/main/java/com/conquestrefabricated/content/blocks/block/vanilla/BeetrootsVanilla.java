package com.conquestrefabricated.content.blocks.block.vanilla;

import com.conquestrefabricated.content.blocks.block.Layer;
import com.conquestrefabricated.content.blocks.block.Slab;
import com.conquestrefabricated.content.blocks.util.PlacementHelper;
import com.conquestrefabricated.core.block.builder.Props;
import com.conquestrefabricated.core.block.properties.ModBlockProperties;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.BeetrootBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.Property;

public class BeetrootsVanilla extends BeetrootBlock {
   public static final BooleanProperty OFFSET_TOGGLE = ModBlockProperties.OFFSET_TOGGLE;
   public static final IntegerProperty LAYERS = BlockStateProperties.LAYERS;

   public BeetrootsVanilla(Props props) {
      super(props.toSettings());
      this.registerDefaultState((BlockState)((BlockState)((BlockState)this.stateDefinition.any()).setValue(LAYERS, 8)).setValue(OFFSET_TOGGLE, false));
   }

   public boolean canSurvive(BlockState state, LevelReader reader, BlockPos pos) {
      return PlacementHelper.isDuringWorldGen(reader) ? super.canSurvive(state, reader, pos) : true;
   }

   public boolean mayPlaceOn(BlockState state, BlockGetter reader, BlockPos pos) {
      return PlacementHelper.isDuringWorldGen(reader) ? super.mayPlaceOn(state, reader, pos) : true;
   }

   public BlockState getStateForPlacement(BlockPlaceContext context) {
      BlockGetter iblockreader = context.getLevel();
      BlockPos blockpos = context.getClickedPos();
      BlockPos down = blockpos.below();
      BlockState blockStateDown = iblockreader.getBlockState(down);
      return !blockStateDown.hasProperty(Layer.LAYERS) && !blockStateDown.hasProperty(Slab.LAYERS)
         ? (BlockState)super.getStateForPlacement(context).setValue(LAYERS, 8)
         : (BlockState)super.getStateForPlacement(context).setValue(LAYERS, (Integer)blockStateDown.getValue(LAYERS));
   }

   public BlockState updateShape(BlockState stateIn, Direction facing, BlockState facingState, LevelAccessor worldIn, BlockPos currentPos, BlockPos facingPos) {
      BlockPos down = currentPos.below();
      BlockState blockStateDown = worldIn.getBlockState(down);
      return !blockStateDown.hasProperty(Layer.LAYERS) && !blockStateDown.hasProperty(Slab.LAYERS)
         ? (BlockState)super.updateShape(stateIn, facing, facingState, worldIn, currentPos, facingPos).setValue(LAYERS, 8)
         : (BlockState)super.updateShape(stateIn, facing, facingState, worldIn, currentPos, facingPos).setValue(LAYERS, (Integer)blockStateDown.getValue(LAYERS));
   }

   protected void createBlockStateDefinition(Builder<Block, BlockState> builder) {
      builder.add(new Property[]{AGE, LAYERS, OFFSET_TOGGLE});
   }

   public void growCrops(Level worldIn, BlockPos pos, BlockState state) {
      int i = this.getAge(state) + this.getBonemealAgeIncrease(worldIn);
      int j = this.getMaxAge();
      if (i > j) {
         i = j;
      }

      worldIn.setBlock(pos, (BlockState)state.setValue(AGE, i), 2);
   }

   public void tick(BlockState state, ServerLevel worldIn, BlockPos pos, RandomSource rand) {
   }
}
