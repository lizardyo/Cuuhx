package com.conquestrefabricated.content.blocks.block.decor;

import com.conquestrefabricated.content.blocks.BlockSettingsAccessor;
import com.conquestrefabricated.content.blocks.CustomOffsetType;
import com.conquestrefabricated.content.blocks.util.PlacementHelper;
import com.conquestrefabricated.core.asset.annotation.Render;
import com.conquestrefabricated.core.asset.annotation.SpecialOffset;
import com.conquestrefabricated.core.block.builder.SpecialOffsetType;
import com.conquestrefabricated.core.block.properties.ModBlockProperties;
import com.conquestrefabricated.core.util.RenderLayer;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BedBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour.OffsetType;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.level.block.state.properties.BedPart;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

@Render(RenderLayer.CUTOUT)
@SpecialOffset(offsetType = SpecialOffsetType.XYZ)
public class Bed extends BedBlock {
   public static final BooleanProperty OFFSET_TOGGLE = ModBlockProperties.OFFSET_TOGGLE;

   public Bed(Properties properties) {
      super(DyeColor.WHITE, ((BlockSettingsAccessor)properties).setCustomOffsetter(CustomOffsetType.LAYER_XYZ).offsetType(OffsetType.NONE).dynamicShape());
      this.registerDefaultState(
         (BlockState)((BlockState)((BlockState)((BlockState)this.stateDefinition.any()).setValue(PART, BedPart.FOOT)).setValue(OCCUPIED, false))
            .setValue(OFFSET_TOGGLE, false)
      );
   }

   public VoxelShape getCollisionShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
      return state.getValue(OFFSET_TOGGLE) ? Shapes.empty() : super.getCollisionShape(state, worldIn, pos, context);
   }

   @NotNull
   public BlockState getStateForPlacement(BlockPlaceContext context) {
      boolean isSlab = PlacementHelper.isFacingSlab(context);
      Direction direction = context.getHorizontalDirection();
      BlockPos blockPos = context.getClickedPos();
      BlockPos blockPos2 = blockPos.relative(direction);
      Level world = context.getLevel();
      return world.getBlockState(blockPos2).canBeReplaced(context) && world.getWorldBorder().isWithinBounds(blockPos2)
         ? (BlockState)((BlockState)this.defaultBlockState().setValue(FACING, direction)).setValue(OFFSET_TOGGLE, isSlab)
         : null;
   }

   public RenderShape getRenderShape(BlockState p_49545_) {
      return RenderShape.MODEL;
   }

   protected void createBlockStateDefinition(Builder<Block, BlockState> builder) {
      builder.add(new Property[]{FACING, PART, OCCUPIED, OFFSET_TOGGLE});
   }
}
