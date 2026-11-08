package com.conquestrefabricated.content.blocks.block.uniquetexture;

import com.conquestrefabricated.content.blocks.block.Layer;
import com.conquestrefabricated.content.blocks.block.Slab;
import com.conquestrefabricated.content.blocks.block.trees.LogPillar;
import com.conquestrefabricated.core.asset.annotation.Assets;
import com.conquestrefabricated.core.asset.annotation.Model;
import com.conquestrefabricated.core.asset.annotation.State;
import com.conquestrefabricated.core.block.builder.Props;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.Half;
import net.minecraft.world.level.block.state.properties.Property;

@Assets(
   state = @State(name = "%s_pillar", template = "parent_stump"),
   item = @Model(name = "item/%s_pillar", parent = "block/%s_stump_4", template = "item/dragon_egg"),
   block = {
         @Model(name = "block/%s_pillar_2", template = "block/parent_pillar_2"),
         @Model(name = "block/%s_pillar_4", template = "block/parent_pillar_4"),
         @Model(name = "block/%s_pillar_6", template = "block/parent_pillar_6"),
         @Model(name = "block/%s_pillar_down_2", template = "block/parent_stump_down_2"),
         @Model(name = "block/%s_pillar_down_4", template = "block/parent_stump_down_4"),
         @Model(name = "block/%s_pillar_down_6", template = "block/parent_stump_down_6")
   }
)
public class BeamPillar extends LogPillar {
   public static final BooleanProperty DOWN = BlockStateProperties.DOWN;
   private Block fullBlock;

   public BeamPillar(Props properties) {
      super(properties);
      this.fullBlock = properties.getParent().getBlock();
   }

   @Override
   public BlockState updateShape(BlockState stateIn, Direction facing, BlockState facingState, LevelAccessor worldIn, BlockPos currentPos, BlockPos facingPos) {
      BlockState down = worldIn.getBlockState(currentPos.below());
      return !(down.getBlock() instanceof Layer) && (!(down.getBlock() instanceof Slab) || down.getValue(Slab.TYPE_UPDOWN) != Half.BOTTOM)
         ? super.updateShape(stateIn, facing, facingState, worldIn, currentPos, facingPos)
         : (BlockState)stateIn.setValue(DOWN, true);
   }

   @Override
   public BlockState getStateForPlacement(BlockPlaceContext context) {
      BlockGetter iblockreader = context.getLevel();
      BlockPos blockpos = context.getClickedPos();
      BlockPos blockposDown = blockpos.below();
      BlockState blockstateDown = iblockreader.getBlockState(blockposDown);
      boolean isDown = false;
      BlockState blockstate = context.getLevel().getBlockState(context.getClickedPos());
      if (blockstateDown.getBlock() instanceof Layer || blockstateDown.getBlock() instanceof Slab && blockstateDown.getValue(Slab.TYPE_UPDOWN) == Half.BOTTOM) {
         isDown = true;
      }

      if (blockstate.getBlock() == this) {
         int i = (Integer)blockstate.getValue(LAYERS);
         if (i == 5) {
            return (BlockState)blockstate.setValue(LAYERS, 1);
         } else if (i == 1) {
            return (BlockState)blockstate.setValue(LAYERS, 4);
         } else if (i == 2) {
            return (BlockState)blockstate.setValue(LAYERS, 3);
         } else if (i == 4) {
            return (BlockState)blockstate.setValue(LAYERS, 2);
         } else {
            return i == 3 ? this.fullBlock.defaultBlockState() : (BlockState)blockstate.setValue(LAYERS, 3);
         }
      } else {
         return (BlockState)super.getStateForPlacement(context).setValue(DOWN, isDown);
      }
   }

   @Override
   protected void addProperties(Builder<Block, BlockState> builder) {
      builder.add(new Property[]{LAYERS, DOWN});
   }
}
