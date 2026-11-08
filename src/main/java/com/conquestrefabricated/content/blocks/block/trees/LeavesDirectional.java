package com.conquestrefabricated.content.blocks.block.trees;

import com.conquestrefabricated.content.blocks.BlockVoxelShapes;
import com.conquestrefabricated.core.asset.annotation.Render;
import com.conquestrefabricated.core.block.builder.Props;
import com.conquestrefabricated.core.util.RenderLayer;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

@Render(RenderLayer.CUTOUT_MIPPED)
public class LeavesDirectional extends Leaves {
   public static final DirectionProperty DIRECTION = BlockStateProperties.HORIZONTAL_FACING;

   public LeavesDirectional(Props props) {
      super(props);
   }

   public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
      return BlockVoxelShapes.cubePartialShape.get(0);
   }

   public BlockState rotate(BlockState state, Rotation rot) {
      return (BlockState)state.setValue(DIRECTION, rot.rotate((Direction)state.getValue(DIRECTION)));
   }

   public BlockState mirror(BlockState state, Mirror mirrorIn) {
      return state.rotate(mirrorIn.getRotation((Direction)state.getValue(DIRECTION)));
   }

   protected void createBlockStateDefinition(Builder<Block, BlockState> builder) {
      builder.add(new Property[]{DISTANCE, PERSISTENT, DIRECTION, WATERLOGGED});
   }

   public BlockState getStateForPlacement(BlockPlaceContext context) {
      Direction facing = context.getHorizontalDirection().getOpposite();
      return (BlockState)super.getStateForPlacement(context).setValue(DIRECTION, facing);
   }
}
