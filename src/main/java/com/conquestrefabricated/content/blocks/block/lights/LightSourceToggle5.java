package com.conquestrefabricated.content.blocks.block.lights;

import com.conquestrefabricated.content.blocks.CustomOffsetType;
import com.conquestrefabricated.content.blocks.block.directional.HorizontalDirectional;
import com.conquestrefabricated.content.blocks.util.Interactions;
import com.conquestrefabricated.core.asset.annotation.ItemDescription;
import com.conquestrefabricated.core.asset.annotation.Render;
import com.conquestrefabricated.core.asset.annotation.SpecialOffset;
import com.conquestrefabricated.core.block.builder.Props;
import com.conquestrefabricated.core.block.builder.SpecialOffsetType;
import com.conquestrefabricated.core.block.properties.ModBlockProperties;
import com.conquestrefabricated.core.util.RenderLayer;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour.OffsetType;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.phys.BlockHitResult;

@ItemDescription(description = "toggle_5")
@Render(RenderLayer.CUTOUT)
@SpecialOffset(offsetType = SpecialOffsetType.XYZ)
public class LightSourceToggle5 extends HorizontalDirectional.Waterlogged.OffsetXYZ.Toggle5 {
   public static final BooleanProperty OFFSET_TOGGLE = ModBlockProperties.OFFSET_TOGGLE;
   public static final IntegerProperty LIGHT_0_3 = IntegerProperty.create("light", 0, 3);

   public LightSourceToggle5(Props props) {
      super(props.customOffsetType(CustomOffsetType.LAYER_XYZ).offset(OffsetType.NONE).dynamicBounds(true));
      this.registerDefaultState(
         (BlockState)((BlockState)((BlockState)((BlockState)this.stateDefinition.any()).setValue(LIGHT_0_3, 0)).setValue(WATERLOGGED, false))
            .setValue(OFFSET_TOGGLE, false)
      );
   }

   @Override
   public InteractionResult use(BlockState state, Level level, BlockPos blockPos, Player player, InteractionHand hand, BlockHitResult hitResult) {
      if (!player.getAbilities().mayBuild) {
         return InteractionResult.FAIL;
      } else if (player.getItemInHand(hand).getItem() == Items.FLINT_AND_STEEL) {
         level.setBlock(blockPos, (BlockState)state.setValue(LIGHT_0_3, 3), 3);
         return InteractionResult.SUCCESS;
      } else {
         return Interactions.onUseToggleItem(player, level, blockPos, state, TOGGLE);
      }
   }

   @Override
   protected void addProperties(Builder<Block, BlockState> builder) {
      builder.add(new Property[]{TOGGLE}).add(new Property[]{LIGHT_0_3}).add(new Property[]{OFFSET_TOGGLE});
   }
}
