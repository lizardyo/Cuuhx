package com.conquestrefabricated.content.blocks.block.arch;

import com.conquestrefabricated.core.asset.annotation.Assets;
import com.conquestrefabricated.core.asset.annotation.Model;
import com.conquestrefabricated.core.asset.annotation.State;
import com.conquestrefabricated.core.block.base.WaterloggedHorizontalDirectionalShape;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

@Assets(
   state = @State(name = "%s_small_arch_half", template = "parent_small_arch_half"),
   item = @Model(name = "item/%s_small_arch_half", parent = "block/%s_small_arch_half", template = "item/parent_segmental_arch"),
   block = @Model(name = "block/%s_small_arch_half", template = "block/parent_small_arch_half")
)
public class ArchSmallHalf extends WaterloggedHorizontalDirectionalShape {
   private static final VoxelShape EAST_SHAPE = Block.box(0.0, 8.0, 0.0, 8.0, 16.0, 16.0);
   private static final VoxelShape WEST_SHAPE = Block.box(8.0, 8.0, 0.0, 16.0, 16.0, 16.0);
   private static final VoxelShape SOUTH_SHAPE = Block.box(0.0, 8.0, 0.0, 16.0, 16.0, 8.0);
   private static final VoxelShape NORTH_SHAPE = Block.box(0.0, 8.0, 8.0, 16.0, 16.0, 16.0);

   public ArchSmallHalf(Properties properties) {
      super(properties);
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

   public VoxelShape getOcclusionShape(BlockState state, BlockGetter world, BlockPos pos) {
      return Shapes.empty();
   }
}
