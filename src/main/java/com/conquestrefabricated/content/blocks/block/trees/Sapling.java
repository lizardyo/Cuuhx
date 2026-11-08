package com.conquestrefabricated.content.blocks.block.trees;

import com.conquestrefabricated.client.gui.config.ConquestConfig;
import com.conquestrefabricated.content.blocks.block.Layer;
import com.conquestrefabricated.content.blocks.block.Slab;
import com.conquestrefabricated.core.asset.annotation.Render;
import com.conquestrefabricated.core.block.builder.Props;
import com.conquestrefabricated.core.util.RenderLayer;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.block.grower.TreeGrower;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.Half;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.Property;

@Render(RenderLayer.CUTOUT)
public class Sapling extends SaplingBlock {
   public static final IntegerProperty LAYERS = BlockStateProperties.LAYERS;

   public Sapling(Props props) {
      super(props.get("tree", AbstractTreeGrower.class), props.toSettings());
      this.registerDefaultState((BlockState)((BlockState)this.stateDefinition.any()).setValue(LAYERS, 8));
   }

   protected void createBlockStateDefinition(Builder<Block, BlockState> builder) {
      builder.add(new Property[]{LAYERS, STAGE});
   }

   public BlockState getStateForPlacement(BlockPlaceContext context) {
      BlockGetter iblockreader = context.getLevel();
      BlockPos blockpos = context.getClickedPos();
      BlockPos down = blockpos.below();
      BlockState blockStateDown = iblockreader.getBlockState(down);
      return !blockStateDown.hasProperty(Layer.LAYERS) && (!blockStateDown.hasProperty(Slab.LAYERS) || blockStateDown.getValue(Slab.TYPE_UPDOWN) != Half.BOTTOM)
         ? (BlockState)super.getStateForPlacement(context).setValue(LAYERS, 8)
         : (BlockState)super.getStateForPlacement(context).setValue(LAYERS, (Integer)blockStateDown.getValue(LAYERS));
   }

   public BlockState updateShape(BlockState stateIn, Direction facing, BlockState facingState, LevelAccessor worldIn, BlockPos currentPos, BlockPos facingPos) {
      if (facing == Direction.DOWN && facingState.isAir() && (Boolean)ConquestConfig.INSTANCE.plantBreaking.get()) {
         return Blocks.AIR.defaultBlockState();
      }

      BlockPos down = currentPos.below();
      BlockState blockStateDown = worldIn.getBlockState(down);
      return !blockStateDown.hasProperty(Layer.LAYERS) && (!blockStateDown.hasProperty(Slab.LAYERS) || blockStateDown.getValue(Slab.TYPE_UPDOWN) != Half.BOTTOM)
         ? (BlockState)super.updateShape(stateIn, facing, facingState, worldIn, currentPos, facingPos).setValue(LAYERS, 8)
         : (BlockState)super.updateShape(stateIn, facing, facingState, worldIn, currentPos, facingPos).setValue(LAYERS, (Integer)blockStateDown.getValue(LAYERS));
   }

   public boolean mayPlaceOn(BlockState state, BlockGetter reader, BlockPos pos) {
      return true;
   }
}
