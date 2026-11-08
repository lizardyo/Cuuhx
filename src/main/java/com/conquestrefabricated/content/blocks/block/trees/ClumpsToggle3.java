package com.conquestrefabricated.content.blocks.block.trees;

import com.conquestrefabricated.api.tags.ModTags;
import com.conquestrefabricated.content.blocks.CustomOffsetType;
import com.conquestrefabricated.content.blocks.block.Layer;
import com.conquestrefabricated.content.blocks.block.Slab;
import com.conquestrefabricated.content.blocks.block.directional.HorizontalDirectional;
import com.conquestrefabricated.core.asset.annotation.ItemDescription;
import com.conquestrefabricated.core.block.builder.Props;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour.OffsetType;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.Half;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.phys.BlockHitResult;

@ItemDescription(description = "toggle_2")
public class ClumpsToggle3 extends HorizontalDirectional {
   public static final IntegerProperty TOGGLE = IntegerProperty.create("toggle", 1, 3);
   public static final IntegerProperty POSITION = IntegerProperty.create("position", 1, 3);
   public static final IntegerProperty LAYER = BlockStateProperties.LAYERS;

   public ClumpsToggle3(Props props) {
      super(props.customOffsetType(CustomOffsetType.LAYER_STATE_Y).offset(OffsetType.NONE).dynamicBounds(true));
      this.registerDefaultState((BlockState)((BlockState)this.stateDefinition.any()).setValue(LAYER, 8));
   }

   @Override
   protected void addProperties(Builder<Block, BlockState> builder) {
      builder.add(new Property[]{TOGGLE, POSITION, LAYER});
   }

   @Override
   public BlockState getStateForPlacement(BlockPlaceContext context) {
      BlockGetter iblockreader = context.getLevel();
      BlockPos blockpos = context.getClickedPos();
      BlockPos down = blockpos.below();
      BlockState blockStateDown = iblockreader.getBlockState(down);
      return !blockStateDown.hasProperty(Layer.LAYERS) && (!blockStateDown.hasProperty(Slab.LAYERS) || blockStateDown.getValue(Slab.TYPE_UPDOWN) != Half.BOTTOM)
         ? (BlockState)super.getStateForPlacement(context).setValue(LAYER, 8)
         : (BlockState)super.getStateForPlacement(context).setValue(LAYER, (Integer)blockStateDown.getValue(LAYER));
   }

   public InteractionResult use(BlockState state, Level level, BlockPos blockPos, Player player, InteractionHand hand, BlockHitResult hitResult) {
      if (player.getAbilities().instabuild) {
         if (player.isShiftKeyDown()) {
            level.setBlock(blockPos, (BlockState)state.cycle(POSITION), 3);
            return InteractionResult.SUCCESS;
         } else {
            level.setBlock(blockPos, (BlockState)state.cycle(TOGGLE), 3);
            return InteractionResult.SUCCESS;
         }
      } else if (player.getMainHandItem().is(ModTags.CYCLING_TOOLS)) {
         if (player.isShiftKeyDown()) {
            level.setBlock(blockPos, (BlockState)state.cycle(POSITION), 3);
            return InteractionResult.SUCCESS;
         } else {
            level.setBlock(blockPos, (BlockState)state.cycle(TOGGLE), 3);
            return InteractionResult.SUCCESS;
         }
      } else {
         return InteractionResult.FAIL;
      }
   }
}
