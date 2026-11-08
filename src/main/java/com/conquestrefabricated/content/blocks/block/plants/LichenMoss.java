package com.conquestrefabricated.content.blocks.block.plants;

import com.conquestrefabricated.content.blocks.BlockSettingsAccessor;
import com.conquestrefabricated.content.blocks.CustomOffsetType;
import com.conquestrefabricated.content.blocks.block.VerticalSlab;
import com.conquestrefabricated.content.blocks.util.PlacementHelper;
import com.conquestrefabricated.core.asset.annotation.SpecialOffset;
import com.conquestrefabricated.core.block.builder.SpecialOffsetType;
import com.conquestrefabricated.core.block.properties.ModBlockProperties;
import com.conquestrefabricated.core.block.properties.Waterloggable;
import java.util.Map;
import java.util.Objects;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Direction.Plane;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.PipeBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour.OffsetType;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;

@SpecialOffset(offsetType = SpecialOffsetType.XYZ)
public class LichenMoss extends AbstractBush implements Waterloggable {
   public static final BooleanProperty OFFSET_TOGGLE = ModBlockProperties.OFFSET_TOGGLE;
   public static final BooleanProperty DOWN = PipeBlock.DOWN;
   public static final BooleanProperty NORTH = PipeBlock.NORTH;
   public static final BooleanProperty EAST = PipeBlock.EAST;
   public static final BooleanProperty SOUTH = PipeBlock.SOUTH;
   public static final BooleanProperty WEST = PipeBlock.WEST;
   protected static final Map<Direction, BooleanProperty> PROPERTY_BY_DIRECTION = PipeBlock.PROPERTY_BY_DIRECTION
      .entrySet()
      .stream()
      .filter(p_199775_0_ -> ((Direction)p_199775_0_.getKey()).getAxis().isHorizontal())
      .collect(Util.toMap());

   public LichenMoss(Properties properties) {
      super(((BlockSettingsAccessor)properties).setCustomOffsetter(CustomOffsetType.LAYER_XYZ_LICHEN).offsetType(OffsetType.NONE).dynamicShape());
      this.registerDefaultState((BlockState)((BlockState)((BlockState)this.stateDefinition.any()).setValue(WATERLOGGED, false)).setValue(OFFSET_TOGGLE, false));
   }

   public BlockState getStateForPlacement(BlockPlaceContext context) {
      BlockGetter blockreader = context.getLevel();
      BlockPos blockpos = context.getClickedPos();
      boolean isSlab = PlacementHelper.isFacingSlab(context);
      FluidState ifluidstate = context.getLevel().getFluidState(context.getClickedPos());
      BlockPos blockpos1 = blockpos.north();
      BlockPos blockpos2 = blockpos.east();
      BlockPos blockpos3 = blockpos.south();
      BlockPos blockpos4 = blockpos.west();
      BlockPos blockposDown = blockpos.below();
      BlockState blockstateNorth = blockreader.getBlockState(blockpos1);
      BlockState blockstateEast = blockreader.getBlockState(blockpos2);
      BlockState blockstateSouth = blockreader.getBlockState(blockpos3);
      BlockState blockstateWest = blockreader.getBlockState(blockpos4);
      BlockState blockstateDown = blockreader.getBlockState(blockposDown);
      return (BlockState)((BlockState)((BlockState)((BlockState)((BlockState)((BlockState)((BlockState)Objects.requireNonNull(super.getStateForPlacement(context))
                           .setValue(
                              NORTH,
                              blockstateNorth.isFaceSturdy(blockreader, blockpos1, Direction.SOUTH)
                                 || blockstateNorth.getBlock() instanceof VerticalSlab
                                    && blockstateNorth.getValue(BlockStateProperties.HORIZONTAL_FACING) == Direction.SOUTH
                           ))
                        .setValue(
                           EAST,
                           blockstateEast.isFaceSturdy(blockreader, blockpos1, Direction.WEST)
                              || blockstateEast.getBlock() instanceof VerticalSlab && blockstateEast.getValue(BlockStateProperties.HORIZONTAL_FACING) == Direction.WEST
                        ))
                     .setValue(
                        SOUTH,
                        blockstateSouth.isFaceSturdy(blockreader, blockpos3, Direction.NORTH)
                           || blockstateSouth.getBlock() instanceof VerticalSlab && blockstateSouth.getValue(BlockStateProperties.HORIZONTAL_FACING) == Direction.NORTH
                     ))
                  .setValue(
                     WEST,
                     blockstateWest.isFaceSturdy(blockreader, blockpos4, Direction.EAST)
                        || blockstateWest.getBlock() instanceof VerticalSlab && blockstateWest.getValue(BlockStateProperties.HORIZONTAL_FACING) == Direction.EAST
                  ))
               .setValue(DOWN, blockstateDown.isFaceSturdy(blockreader, blockposDown, Direction.DOWN)))
            .setValue(WATERLOGGED, ifluidstate.getType() == Fluids.WATER))
         .setValue(OFFSET_TOGGLE, isSlab);
   }

   public BlockState updateShape(BlockState stateIn, Direction facing, BlockState facingState, LevelAccessor world, BlockPos currentPos, BlockPos facingPos) {
      if ((Boolean)stateIn.getValue(WATERLOGGED)) {
         world.scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickDelay(world));
      }

      return facing.getAxis().getPlane() == Plane.HORIZONTAL
         ? (BlockState)stateIn.setValue(
            (Property)PROPERTY_BY_DIRECTION.get(facing),
            facingState.isFaceSturdy(world, facingPos, facing.getOpposite())
               || facingState.getBlock() instanceof VerticalSlab && facingState.getValue(BlockStateProperties.HORIZONTAL_FACING) == facing
         )
         : super.updateShape(stateIn, facing, facingState, world, currentPos, facingPos);
   }

   public FluidState getFluidState(BlockState state) {
      return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
   }

   protected void createBlockStateDefinition(Builder<Block, BlockState> builder) {
      builder.add(new Property[]{NORTH, EAST, WEST, SOUTH, DOWN, WATERLOGGED, OFFSET_TOGGLE});
   }

   public float getMaxHorizontalOffset() {
      return 0.0F;
   }

   public float getMaxVerticalOffset() {
      return 0.0F;
   }
}
