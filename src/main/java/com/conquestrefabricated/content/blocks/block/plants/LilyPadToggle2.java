package com.conquestrefabricated.content.blocks.block.plants;

import com.conquestrefabricated.content.blocks.util.Interactions;
import com.conquestrefabricated.core.asset.annotation.Render;
import com.conquestrefabricated.core.util.RenderLayer;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.WaterlilyBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.phys.BlockHitResult;

@Render(RenderLayer.CUTOUT)
public class LilyPadToggle2 extends WaterlilyBlock {
   private static final IntegerProperty TOGGLE = IntegerProperty.create("toggle", 1, 2);

   public LilyPadToggle2(Properties properties) {
      super(properties);
   }

   public void entityInside(BlockState state, Level world, BlockPos pos, Entity entity) {
   }

   public InteractionResult use(BlockState state, Level level, BlockPos blockPos, Player player, InteractionHand hand, BlockHitResult hitResult) {
      return Interactions.onUseToggleItem(player, level, blockPos, state, TOGGLE);
   }

   protected void createBlockStateDefinition(Builder<Block, BlockState> builder) {
      builder.add(new Property[]{TOGGLE});
   }

   protected boolean mayPlaceOn(BlockState blockState, BlockGetter blockView, BlockPos blockPos) {
      return true;
   }
}
