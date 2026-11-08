package com.conquestrefabricated.content.blocks.block.plants;

import com.conquestrefabricated.core.asset.annotation.Render;
import com.conquestrefabricated.core.block.properties.Waterloggable;
import com.conquestrefabricated.core.util.RenderLayer;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour.OffsetType;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.material.FluidState;

@Render(RenderLayer.CUTOUT)
public class BushStackable extends AbstractBush implements Waterloggable {
   public BushStackable(Properties properties) {
      super(properties);
      this.registerDefaultState((BlockState)((BlockState)this.stateDefinition.any()).setValue(WATERLOGGED, false));
   }

   public OffsetType getOffsetType() {
      return OffsetType.XYZ;
   }

   public FluidState getFluidState(BlockState state) {
      return Waterloggable.getFluidState(state);
   }

   protected void createBlockStateDefinition(Builder<Block, BlockState> builder) {
      builder.add(new Property[]{WATERLOGGED});
   }
}
