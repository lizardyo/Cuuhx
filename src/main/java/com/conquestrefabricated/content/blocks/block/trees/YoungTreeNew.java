package com.conquestrefabricated.content.blocks.block.trees;

import com.conquestrefabricated.content.blocks.BlockVoxelShapes;
import com.conquestrefabricated.content.blocks.block.Layer;
import com.conquestrefabricated.core.asset.annotation.Render;
import com.conquestrefabricated.core.block.base.WaterloggedShape;
import com.conquestrefabricated.core.block.builder.Props;
import com.conquestrefabricated.core.util.RenderLayer;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

@Render(RenderLayer.CUTOUT)
public class YoungTreeNew extends WaterloggedShape {
   public static final BooleanProperty UP = BlockStateProperties.UP;
   public static final BooleanProperty DOWN = BlockStateProperties.DOWN;

   public YoungTreeNew(Props props) {
      super(props.toSettings());
      this.registerDefaultState(
         (BlockState)((BlockState)((BlockState)((BlockState)this.stateDefinition.any()).setValue(UP, false)).setValue(DOWN, false)).setValue(WATERLOGGED, false)
      );
   }

   @Override
   public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
      return BlockVoxelShapes.pillarShape.get(0);
   }

   @Override
   public VoxelShape getShape(BlockState state) {
      return BlockVoxelShapes.pillarShape.get(0);
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
      return (BlockState)((BlockState)super.getStateForPlacement(context).setValue(UP, this.attachesTo(up))).setValue(DOWN, this.attachesToDown(down));
   }

   public BlockState updateShape(BlockState stateIn, Direction facing, BlockState facingState, LevelAccessor world, BlockPos currentPos, BlockPos facingPos) {
      if ((Boolean)stateIn.getValue(WATERLOGGED)) {
         world.scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickDelay(world));
      }

      boolean flag = this.canConnectTo(world, currentPos.above());
      boolean flag1 = this.canConnectToDown(world, currentPos.below());
      return (BlockState)((BlockState)stateIn.setValue(UP, flag)).setValue(DOWN, flag1);
   }

   private boolean attachesTo(BlockState blockstate) {
      Block block = blockstate.getBlock();
      return !Block.isExceptionForConnection(blockstate) && (block == this || block instanceof YoungTreeNew);
   }

   private boolean attachesToDown(BlockState blockstate) {
      Block block = blockstate.getBlock();
      return block instanceof Layer;
   }

   private boolean canConnectTo(LevelAccessor world, BlockPos pos) {
      BlockState blockState = world.getBlockState(pos);
      Block block = blockState.getBlock();
      return !Block.isExceptionForConnection(blockState) && (block == this || block instanceof YoungTreeNew);
   }

   private boolean canConnectToDown(LevelAccessor world, BlockPos pos) {
      BlockState blockState = world.getBlockState(pos);
      Block block = blockState.getBlock();
      return block instanceof Layer;
   }
}
