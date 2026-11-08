package com.conquestrefabricated.content.blocks.block.plants;

import com.conquestrefabricated.content.blocks.util.Interactions;
import com.conquestrefabricated.core.asset.annotation.ItemDescription;
import com.conquestrefabricated.core.block.builder.Props;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour.OffsetType;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.NotNull;

@ItemDescription(description = "toggle_2")
public class PlantToggleBlock2 extends Bush {
   public static final IntegerProperty TOGGLE = IntegerProperty.create("toggle", 1, 2);

   public PlantToggleBlock2(Props props) {
      super(props);
      this.registerDefaultState((BlockState)((BlockState)((BlockState)this.stateDefinition.any()).setValue(WATERLOGGED, false)).setValue(OFFSET_TOGGLE, false));
   }

   @Override
   protected void createBlockStateDefinition(Builder<Block, BlockState> builder) {
      builder.add(new Property[]{WATERLOGGED, LAYERS, TOGGLE, OFFSET_TOGGLE});
   }

   @NotNull
   @Override
   public BlockState getStateForPlacement(BlockPlaceContext context) {
      return (BlockState)super.getStateForPlacement(context).setValue(TOGGLE, 1);
   }

   @Override
   public OffsetType getOffsetType() {
      return OffsetType.NONE;
   }

   public InteractionResult use(BlockState state, Level level, BlockPos blockPos, Player player, InteractionHand hand, BlockHitResult hitResult) {
      return Interactions.onUseToggleItem(player, level, blockPos, state, TOGGLE);
   }
}
