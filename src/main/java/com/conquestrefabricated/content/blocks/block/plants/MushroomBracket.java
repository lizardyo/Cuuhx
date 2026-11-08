package com.conquestrefabricated.content.blocks.block.plants;

import com.conquestrefabricated.api.tags.ModTags;
import com.conquestrefabricated.content.blocks.block.VerticalSlab;
import com.conquestrefabricated.content.blocks.block.directional.Half;
import com.conquestrefabricated.content.blocks.block.directional.HorizontalDirectional;
import com.conquestrefabricated.content.blocks.block.trees.LogPillar;
import com.conquestrefabricated.content.blocks.block.trees.LogVerticalQuarter;
import com.conquestrefabricated.core.asset.annotation.ItemDescription;
import com.conquestrefabricated.core.block.builder.Props;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.phys.BlockHitResult;

@ItemDescription(description = "toggle_3")
public class MushroomBracket extends HorizontalDirectional {
   public static final IntegerProperty TOGGLE = IntegerProperty.create("toggle", 1, 3);
   public static final IntegerProperty POSITION = IntegerProperty.create("position", 1, 16);

   public MushroomBracket(Props props) {
      super(props);
   }

   @Override
   protected void addProperties(Builder<Block, BlockState> builder) {
      builder.add(new Property[]{TOGGLE, POSITION});
   }

   @Override
   public BlockState getStateForPlacement(BlockPlaceContext context) {
      Direction facing = context.getClickedFace().getOpposite();
      BlockGetter blockreader = context.getLevel();
      BlockPos blockpos = context.getClickedPos();
      BlockState state = blockreader.getBlockState(blockpos.relative(facing));
      Block block = state.getBlock();
      int pos = 1;
      if (block instanceof LogPillar) {
         switch (state.getValue(LogPillar.LAYERS)) {
            case 1:
               pos = 3;
               break;
            case 2:
               pos = 5;
               break;
            case 3:
               pos = 6;
               break;
            case 4:
               pos = 4;
               break;
            case 5:
               pos = 2;
         }
      } else if (block instanceof LogVerticalQuarter) {
         pos = (Integer)state.getValue(LogVerticalQuarter.LAYERS) + 6;
      } else if (block instanceof VerticalSlab) {
         pos = (Integer)state.getValue(VerticalSlab.LAYERS) + 10;
      } else if (BuiltInRegistries.BLOCK.getKey(block).toString().contains("branch_flat") && state.hasProperty(Half.TYPE_UPDOWN)) {
         if (state.getValue(Half.TYPE_UPDOWN) == net.minecraft.world.level.block.state.properties.Half.TOP) {
            pos = 16;
         } else {
            pos = 15;
         }
      }

      return (BlockState)((BlockState)super.getStateForPlacement(context).setValue(TOGGLE, 1)).setValue(POSITION, pos);
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
