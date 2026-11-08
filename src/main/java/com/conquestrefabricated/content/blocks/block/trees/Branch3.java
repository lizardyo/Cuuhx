package com.conquestrefabricated.content.blocks.block.trees;

import com.conquestrefabricated.content.blocks.block.Layer;
import com.conquestrefabricated.content.blocks.block.Slab;
import com.conquestrefabricated.content.blocks.block.directional.HorizontalDirectional;
import com.conquestrefabricated.content.blocks.util.Interactions;
import com.conquestrefabricated.core.asset.annotation.ItemDescription;
import com.conquestrefabricated.core.block.builder.Props;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.Half;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.NotNull;

@ItemDescription(description = "toggle_3")
public class Branch3 extends HorizontalDirectional {
   public static final BooleanProperty DOWN = BlockStateProperties.DOWN;
   public static final IntegerProperty TOGGLE = IntegerProperty.create("toggle", 1, 3);

   public Branch3(Props props) {
      super(props);
      this.registerDefaultState((BlockState)((BlockState)this.stateDefinition.any()).setValue(DOWN, false));
   }

   @Override
   protected void addProperties(Builder<Block, BlockState> builder) {
      builder.add(new Property[]{TOGGLE, DOWN});
   }

   @NotNull
   @Override
   public BlockState getStateForPlacement(BlockPlaceContext context) {
      BlockGetter blockreader = context.getLevel();
      BlockPos blockpos = context.getClickedPos();
      BlockPos blockposDown = blockpos.below();
      BlockState blockstateDown = blockreader.getBlockState(blockposDown);
      boolean isDown = false;
      if (blockstateDown.getBlock() instanceof Layer || blockstateDown.getBlock() instanceof Slab && blockstateDown.getValue(Slab.TYPE_UPDOWN) == Half.BOTTOM) {
         isDown = true;
      }

      return (BlockState)((BlockState)super.getStateForPlacement(context).setValue(TOGGLE, 1)).setValue(DOWN, isDown);
   }

   public BlockState updateShape(BlockState stateIn, Direction facing, BlockState facingState, LevelAccessor worldIn, BlockPos currentPos, BlockPos facingPos) {
      BlockState down = worldIn.getBlockState(currentPos.below());
      return !(down.getBlock() instanceof Layer) && (!(down.getBlock() instanceof Slab) || down.getValue(Slab.TYPE_UPDOWN) != Half.BOTTOM)
         ? super.updateShape(stateIn, facing, facingState, worldIn, currentPos, facingPos)
         : (BlockState)stateIn.setValue(DOWN, true);
   }

   public InteractionResult use(BlockState state, Level level, BlockPos blockPos, Player player, InteractionHand hand, BlockHitResult hitResult) {
      return Interactions.onUseToggleItem(player, level, blockPos, state, TOGGLE);
   }
}
