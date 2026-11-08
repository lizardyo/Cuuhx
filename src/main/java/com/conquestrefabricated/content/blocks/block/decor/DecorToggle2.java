package com.conquestrefabricated.content.blocks.block.decor;

import com.conquestrefabricated.content.blocks.block.directional.HorizontalDirectional;
import com.conquestrefabricated.content.blocks.util.Interactions;
import com.conquestrefabricated.core.asset.annotation.ItemDescription;
import com.conquestrefabricated.core.block.builder.Props;
import java.util.function.Function;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.NotNull;

@ItemDescription(description = "toggle_2")
public class DecorToggle2 extends HorizontalDirectional {
   public static final IntegerProperty TOGGLE = IntegerProperty.create("toggle", 1, 2);
   private final Function<BlockPlaceContext, Integer> placementHandler;

   public DecorToggle2(Props props) {
      super(props);
      this.placementHandler = props.getOrDefault("placementHandler", Function.class, ctx -> ctx);
   }

   @Override
   protected void addProperties(Builder<Block, BlockState> builder) {
      builder.add(new Property[]{TOGGLE});
   }

   @NotNull
   @Override
   public BlockState getStateForPlacement(BlockPlaceContext context) {
      int toggle = this.placementHandler.apply(context);
      return (BlockState)super.getStateForPlacement(context).setValue(TOGGLE, toggle);
   }

   public InteractionResult use(BlockState state, Level level, BlockPos blockPos, Player player, InteractionHand hand, BlockHitResult hitResult) {
      return Interactions.onUseToggleItem(player, level, blockPos, state, TOGGLE);
   }
}
