package com.conquestrefabricated.content.blocks.block.decor;

import com.conquestrefabricated.api.tags.ModTags;
import com.conquestrefabricated.core.asset.annotation.ItemDescription;
import com.conquestrefabricated.core.asset.annotation.Render;
import com.conquestrefabricated.core.util.RenderLayer;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.ChainBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.phys.BlockHitResult;

@Render(RenderLayer.CUTOUT)
@ItemDescription(description = "toggle_4")
public class Chains extends ChainBlock {
   public static final IntegerProperty TOGGLE = IntegerProperty.create("toggle", 1, 4);

   public Chains(Properties properties) {
      super(properties);
   }

   protected void createBlockStateDefinition(Builder<Block, BlockState> builder) {
      builder.add(new Property[]{TOGGLE, AXIS, WATERLOGGED});
   }

   public InteractionResult use(BlockState state, Level level, BlockPos blockPos, Player player, InteractionHand hand, BlockHitResult hitResult) {
      if (player.getAbilities().instabuild) {
         level.setBlock(blockPos, (BlockState)state.cycle(TOGGLE), 4);
         return InteractionResult.SUCCESS;
      } else if (player.getMainHandItem().is(ModTags.CYCLING_TOOLS)) {
         level.setBlock(blockPos, (BlockState)state.cycle(TOGGLE), 4);
         return InteractionResult.SUCCESS;
      } else {
         return InteractionResult.FAIL;
      }
   }

   public BlockState getStateForPlacement(BlockPlaceContext context) {
      return (BlockState)this.defaultBlockState().setValue(AXIS, context.getClickedFace().getAxis());
   }
}
