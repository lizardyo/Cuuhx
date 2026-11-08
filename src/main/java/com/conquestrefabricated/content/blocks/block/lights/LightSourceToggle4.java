package com.conquestrefabricated.content.blocks.block.lights;

import com.conquestrefabricated.content.blocks.block.directional.HorizontalDirectional;
import com.conquestrefabricated.core.asset.annotation.ItemDescription;
import com.conquestrefabricated.core.asset.annotation.Render;
import com.conquestrefabricated.core.asset.annotation.SpecialOffset;
import com.conquestrefabricated.core.block.builder.Props;
import com.conquestrefabricated.core.block.builder.SpecialOffsetType;
import com.conquestrefabricated.core.util.RenderLayer;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.phys.BlockHitResult;

@ItemDescription(description = "toggle_4")
@Render(RenderLayer.CUTOUT)
@SpecialOffset(offsetType = SpecialOffsetType.XYZ)
public class LightSourceToggle4 extends HorizontalDirectional.Waterlogged.Toggle4 {
   public static final IntegerProperty LIGHT_0_3 = IntegerProperty.create("light", 0, 3);

   public LightSourceToggle4(Props properties) {
      super(properties);
      this.registerDefaultState((BlockState)((BlockState)((BlockState)this.stateDefinition.any()).setValue(LIGHT_0_3, 0)).setValue(WATERLOGGED, false));
   }

   @Override
   public InteractionResult use(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
      if (!player.getAbilities().mayBuild) {
         return InteractionResult.FAIL;
      }

      if (player.getItemInHand(hand).getItem() == Items.FLINT_AND_STEEL) {
         world.setBlock(pos, (BlockState)state.setValue(LIGHT_0_3, 3), 3);
      } else {
         world.setBlock(pos, (BlockState)state.cycle(TOGGLE), 3);
      }

      return InteractionResult.SUCCESS;
   }

   @Override
   protected void addProperties(Builder<Block, BlockState> builder) {
      builder.add(new Property[]{TOGGLE}).add(new Property[]{LIGHT_0_3});
   }
}
