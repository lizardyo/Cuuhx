package com.conquestrefabricated.content.blocks.block.food;

import com.conquestrefabricated.content.blocks.block.directional.HorizontalDirectional;
import com.conquestrefabricated.core.asset.annotation.Render;
import com.conquestrefabricated.core.asset.annotation.SpecialOffset;
import com.conquestrefabricated.core.block.builder.Props;
import com.conquestrefabricated.core.block.builder.SpecialOffsetType;
import com.conquestrefabricated.core.util.RenderLayer;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.Property;

@Render(RenderLayer.CUTOUT)
@SpecialOffset(offsetType = SpecialOffsetType.XYZ)
public class Tankard extends HorizontalDirectional.OffsetXYZ {
   public static final IntegerProperty TANKARDS = IntegerProperty.create("tankards", 1, 2);

   public Tankard(Props properties) {
      super(properties);
   }

   public boolean canBeReplaced(BlockState state, BlockPlaceContext context) {
      return context.getItemInHand().getItem() == this.asItem() && state.getValue(TANKARDS) < 2 ? true : super.canBeReplaced(state, context);
   }

   @Override
   public BlockState getStateForPlacement(BlockPlaceContext context) {
      BlockState blockstate = context.getLevel().getBlockState(context.getClickedPos());
      return blockstate.getBlock() == this && blockstate.getValue(TANKARDS) < 2
         ? (BlockState)blockstate.setValue(TANKARDS, Math.min(4, (Integer)blockstate.getValue(TANKARDS) + 1))
         : super.getStateForPlacement(context);
   }

   @Override
   protected void addProperties(Builder<Block, BlockState> container) {
      container.add(new Property[]{TANKARDS, OFFSET_TOGGLE});
   }
}
