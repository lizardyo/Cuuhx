package com.conquestrefabricated.content.blocks.block.decor;

import com.conquestrefabricated.core.asset.annotation.Render;
import com.conquestrefabricated.core.block.base.WaterloggedHorizontalDirectionalShape;
import com.conquestrefabricated.core.util.RenderLayer;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.VoxelShape;

@Render(RenderLayer.CUTOUT)
public class RackHalberds extends WaterloggedHorizontalDirectionalShape {
   public static final BooleanProperty UP = BlockStateProperties.UP;
   public static final BooleanProperty DOWN = BlockStateProperties.DOWN;
   private static final VoxelShape EAST_SHAPE = Block.box(0.0, 0.0, 0.0, 8.0, 16.0, 16.0);
   private static final VoxelShape WEST_SHAPE = Block.box(8.0, 0.0, 0.0, 16.0, 16.0, 16.0);
   private static final VoxelShape SOUTH_SHAPE = Block.box(0.0, 0.0, 0.0, 16.0, 16.0, 8.0);
   private static final VoxelShape NORTH_SHAPE = Block.box(0.0, 0.0, 8.0, 16.0, 16.0, 16.0);

   public RackHalberds(Properties properties) {
      super(properties);
      this.registerDefaultState(
         (BlockState)((BlockState)((BlockState)((BlockState)((BlockState)this.stateDefinition.any()).setValue(UP, false)).setValue(DOWN, false))
               .setValue(DIRECTION, Direction.NORTH))
            .setValue(WATERLOGGED, false)
      );
   }

   @Override
   protected void addProperties(Builder<Block, BlockState> builder) {
      builder.add(new Property[]{UP, DOWN});
   }

   @Override
   public BlockState getStateForPlacement(BlockPlaceContext context) {
      BlockPos pos = context.getClickedPos();
      BlockGetter reader = context.getLevel();
      BlockState up = reader.getBlockState(pos.above());
      BlockState down = reader.getBlockState(pos.below());
      return (BlockState)((BlockState)super.getStateForPlacement(context).setValue(UP, this.attachesTo(up))).setValue(DOWN, this.attachesTo(down));
   }

   public BlockState updateShape(BlockState stateIn, Direction facing, BlockState facingState, LevelAccessor world, BlockPos currentPos, BlockPos facingPos) {
      if ((Boolean)stateIn.getValue(WATERLOGGED)) {
         world.scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickDelay(world));
      }

      boolean flag = this.canConnectTo(world, currentPos.above());
      boolean flag1 = this.canConnectTo(world, currentPos.below());
      return (BlockState)((BlockState)stateIn.setValue(UP, flag)).setValue(DOWN, flag1);
   }

   @Override
   public VoxelShape getShape(BlockState state) {
      switch ((Direction)state.getValue(DIRECTION)) {
         case NORTH:
         default:
            return NORTH_SHAPE;
         case SOUTH:
            return SOUTH_SHAPE;
         case WEST:
            return WEST_SHAPE;
         case EAST:
            return EAST_SHAPE;
      }
   }

   private boolean attachesTo(BlockState blockstate) {
      Block block = blockstate.getBlock();
      return !Block.isExceptionForConnection(blockstate) && (block == this || block instanceof RackHalberds);
   }

   private boolean canConnectTo(LevelAccessor world, BlockPos pos) {
      BlockState blockState = world.getBlockState(pos);
      Block block = blockState.getBlock();
      return !Block.isExceptionForConnection(blockState) && (block == this || block instanceof RackHalberds);
   }
}
