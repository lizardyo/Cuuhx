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
import net.minecraft.core.Direction.Axis;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.AnvilBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour.OffsetType;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

@Render(RenderLayer.CUTOUT)
@SpecialOffset(offsetType = SpecialOffsetType.XYZ)
public class Anvil extends AnvilBlock {
   public static final BooleanProperty OFFSET_TOGGLE = ModBlockProperties.OFFSET_TOGGLE;
   public static final IntegerProperty DAMAGE = IntegerProperty.create("damage", 1, 3);
   private static final VoxelShape PART_BASE = Block.box(2.0, 0.0, 2.0, 14.0, 10.0, 14.0);
   private static final VoxelShape PART_UPPER_X = Block.box(0.0, 10.0, 3.0, 16.0, 16.0, 13.0);
   private static final VoxelShape PART_UPPER_Z = Block.box(3.0, 10.0, 0.0, 13.0, 16.0, 16.0);
   private static final VoxelShape X_AXIS_AABB = Shapes.or(PART_BASE, PART_UPPER_X);
   private static final VoxelShape Z_AXIS_AABB = Shapes.or(PART_BASE, PART_UPPER_Z);

   public Anvil(Properties properties) {
      super(((BlockSettingsAccessor)properties).setCustomOffsetter(CustomOffsetType.LAYER_XYZ).offsetType(OffsetType.NONE).dynamicShape());
      this.registerDefaultState((BlockState)((BlockState)this.stateDefinition.any()).setValue(OFFSET_TOGGLE, false));
   }

   public VoxelShape getCollisionShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
      return state.getValue(OFFSET_TOGGLE) ? Shapes.empty() : super.getCollisionShape(state, worldIn, pos, context);
   }

   @NotNull
   public BlockState getStateForPlacement(BlockPlaceContext context) {
      boolean isSlab = PlacementHelper.isFacingSlab(context);
      return (BlockState)super.getStateForPlacement(context).setValue(OFFSET_TOGGLE, isSlab);
   }

   public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
      Direction direction = (Direction)state.getValue(FACING);
      return direction.getAxis() == Axis.X ? X_AXIS_AABB : Z_AXIS_AABB;
   }

   protected void createBlockStateDefinition(Builder<Block, BlockState> builder) {
      builder.add(new Property[]{FACING, DAMAGE, OFFSET_TOGGLE});
   }

   public static class AnvilSmall extends Anvil {
      private static final VoxelShape PART_BASE = box(2.0, 0.0, 2.0, 14.0, 10.0, 14.0);

      public AnvilSmall(Properties settings) {
         super(settings);
      }

      @Override
      public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
         return PART_BASE;
      }
   }
}
