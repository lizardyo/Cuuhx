package com.conquestrefabricated.content.blocks.block.trees;

import com.conquestrefabricated.content.blocks.util.Interactions;
import com.conquestrefabricated.core.asset.annotation.Render;
import com.conquestrefabricated.core.block.builder.Props;
import com.conquestrefabricated.core.util.RenderLayer;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.phys.BlockHitResult;

@Render(RenderLayer.CUTOUT_MIPPED)
public class LeavesWillow extends LeavesBlock {
   public static final IntegerProperty TOGGLE = IntegerProperty.create("toggle", 1, 2);
   private final ItemLike sapling;

   public LeavesWillow(Props props) {
      super(props.toSettings());
      this.sapling = props.get("sapling", ItemLike.class);
   }

   public InteractionResult use(BlockState state, Level level, BlockPos blockPos, Player player, InteractionHand hand, BlockHitResult hitResult) {
      return Interactions.onUseToggleItem(player, level, blockPos, state, TOGGLE);
   }

   protected void createBlockStateDefinition(Builder<Block, BlockState> stateList) {
      stateList.add(new Property[]{TOGGLE, DISTANCE, PERSISTENT, WATERLOGGED});
   }
}
