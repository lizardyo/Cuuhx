package com.conquestrefabricated.content.blocks.block.arch;

import com.conquestrefabricated.content.blocks.BlockSettingsAccessor;
import com.conquestrefabricated.content.blocks.CustomOffsetType;
import com.conquestrefabricated.content.blocks.util.PlacementHelper;
import com.conquestrefabricated.core.asset.annotation.SpecialOffset;
import com.conquestrefabricated.core.block.builder.SpecialOffsetType;
import com.conquestrefabricated.core.block.properties.ModBlockProperties;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour.OffsetType;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.Property;
import org.jetbrains.annotations.NotNull;

@SpecialOffset(offsetType = SpecialOffsetType.XZ)
public class ArchHalfOffset extends ArchHalf {
   public static final BooleanProperty OFFSET_TOGGLE = ModBlockProperties.OFFSET_TOGGLE;

   public ArchHalfOffset(Properties properties) {
      super(((BlockSettingsAccessor)properties).setCustomOffsetter(CustomOffsetType.LAYER_XZ).offsetType(OffsetType.NONE).dynamicShape());
      this.registerDefaultState((BlockState)((BlockState)this.stateDefinition.any()).setValue(OFFSET_TOGGLE, false));
   }

   @Override
   protected void createBlockStateDefinition(Builder<Block, BlockState> builder) {
      builder.add(new Property[]{FACING, FORM, TYPE_UPDOWN, OFFSET_TOGGLE});
   }

   @NotNull
   @Override
   public BlockState getStateForPlacement(BlockPlaceContext context) {
      boolean isSlab = PlacementHelper.isFacingSlab(context);
      return (BlockState)super.getStateForPlacement(context).setValue(OFFSET_TOGGLE, isSlab);
   }
}
