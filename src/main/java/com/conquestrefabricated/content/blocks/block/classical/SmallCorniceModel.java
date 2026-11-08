package com.conquestrefabricated.content.blocks.block.classical;

import com.conquestrefabricated.content.blocks.BlockSettingsAccessor;
import com.conquestrefabricated.content.blocks.CustomOffsetType;
import com.conquestrefabricated.content.blocks.block.decor.Lintels;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour.OffsetType;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class SmallCorniceModel extends Lintels {
   public SmallCorniceModel(Properties properties) {
      super(((BlockSettingsAccessor)properties).setCustomOffsetter(CustomOffsetType.LAYER_XYZ).offsetType(OffsetType.NONE).dynamicShape());
      this.registerDefaultState((BlockState)((BlockState)this.stateDefinition.any()).setValue(OFFSET_TOGGLE, false));
   }

   @Override
   public VoxelShape getCollisionShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
      return state.getValue(OFFSET_TOGGLE) ? Shapes.empty() : super.getShape(state);
   }
}
