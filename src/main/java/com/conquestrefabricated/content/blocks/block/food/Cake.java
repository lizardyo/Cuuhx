package com.conquestrefabricated.content.blocks.block.food;

import com.conquestrefabricated.content.blocks.BlockSettingsAccessor;
import com.conquestrefabricated.content.blocks.CustomOffsetType;
import com.conquestrefabricated.content.blocks.util.PlacementHelper;
import com.conquestrefabricated.core.asset.annotation.SpecialOffset;
import com.conquestrefabricated.core.block.builder.SpecialOffsetType;
import com.conquestrefabricated.core.block.properties.ModBlockProperties;
import com.conquestrefabricated.core.block.properties.PropertyVariant;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CakeBlock;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour.OffsetType;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

@SpecialOffset(offsetType = SpecialOffsetType.XYZ)
public class Cake extends CakeBlock implements PropertyVariant {
   public static final BooleanProperty OFFSET_TOGGLE = ModBlockProperties.OFFSET_TOGGLE;

   public Cake(Properties properties) {
      super(((BlockSettingsAccessor)properties).setCustomOffsetter(CustomOffsetType.LAYER_XYZ).offsetType(OffsetType.NONE).dynamicShape());
      this.registerDefaultState((BlockState)((BlockState)this.stateDefinition.any()).setValue(OFFSET_TOGGLE, false));
   }

   public boolean canSurvive(BlockState state, LevelReader world, BlockPos pos) {
      return true;
   }

   @Override
   public Property<?> getVariantProperty() {
      return CakeBlock.BITES;
   }

   protected void createBlockStateDefinition(Builder<Block, BlockState> builder) {
      builder.add(new Property[]{BITES, OFFSET_TOGGLE});
   }

   @NotNull
   public BlockState getStateForPlacement(BlockPlaceContext context) {
      boolean isSlab = PlacementHelper.isFacingSlab(context);
      return (BlockState)super.getStateForPlacement(context).setValue(OFFSET_TOGGLE, isSlab);
   }

   public VoxelShape getCollisionShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
      return state.getValue(OFFSET_TOGGLE) ? Shapes.empty() : super.getCollisionShape(state, worldIn, pos, context);
   }

   public static class Directional extends Cake {
      public static final DirectionProperty DIRECTION = BlockStateProperties.HORIZONTAL_FACING;

      public Directional(Properties properties) {
         super(properties);
      }

      public BlockState rotate(BlockState state, Rotation rot) {
         return (BlockState)state.setValue(DIRECTION, rot.rotate((Direction)state.getValue(DIRECTION)));
      }

      public BlockState mirror(BlockState state, Mirror mirrorIn) {
         return state.rotate(mirrorIn.getRotation((Direction)state.getValue(DIRECTION)));
      }

      @NotNull
      @Override
      public BlockState getStateForPlacement(BlockPlaceContext context) {
         Direction facing = context.getHorizontalDirection().getOpposite();
         return (BlockState)super.getStateForPlacement(context).setValue(DIRECTION, facing);
      }

      @Override
      protected final void createBlockStateDefinition(Builder<Block, BlockState> builder) {
         builder.add(new Property[]{DIRECTION, BITES, OFFSET_TOGGLE});
      }
   }
}
