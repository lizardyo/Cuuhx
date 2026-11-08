package com.conquestrefabricated.content.blocks.block.food;

import com.conquestrefabricated.content.blocks.block.directional.HorizontalDirectional;
import com.conquestrefabricated.core.asset.annotation.Render;
import com.conquestrefabricated.core.asset.annotation.SpecialOffset;
import com.conquestrefabricated.core.block.builder.Props;
import com.conquestrefabricated.core.block.builder.SpecialOffsetType;
import com.conquestrefabricated.core.util.RenderLayer;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.phys.BlockHitResult;

@Render(RenderLayer.CUTOUT)
@SpecialOffset(offsetType = SpecialOffsetType.XYZ)
public class CastIronCauldron extends HorizontalDirectional.OffsetXYZ {
   public static final IntegerProperty FOOD = IntegerProperty.create("food", 1, 8);

   public CastIronCauldron(Props properties) {
      super(properties);
   }

   public InteractionResult use(BlockState state, Level level, BlockPos blockPos, Player player, InteractionHand hand, BlockHitResult hitResult) {
      ItemStack itemstack = player.getItemInHand(hand);
      Item item = itemstack.getItem();
      if (!level.isClientSide) {
         if ((Integer)state.getValue(FOOD) < 8 && item.isEdible()) {
            level.setBlock(blockPos, (BlockState)state.setValue(FOOD, (Integer)state.getValue(FOOD) + 1), 3);
            return InteractionResult.SUCCESS;
         }

         if ((Integer)state.getValue(FOOD) > 0 && item == Items.BOWL) {
            level.setBlock(blockPos, (BlockState)state.setValue(FOOD, (Integer)state.getValue(FOOD) - 1), 3);
            return InteractionResult.CONSUME;
         }
      }

      return super.use(state, level, blockPos, player, hand, hitResult);
   }

   @Override
   protected void addProperties(Builder<Block, BlockState> container) {
      container.add(new Property[]{FOOD, OFFSET_TOGGLE});
   }
}
