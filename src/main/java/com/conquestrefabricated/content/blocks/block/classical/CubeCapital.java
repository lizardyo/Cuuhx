package com.conquestrefabricated.content.blocks.block.classical;

import com.conquestrefabricated.core.asset.annotation.Assets;
import com.conquestrefabricated.core.asset.annotation.Model;
import com.conquestrefabricated.core.asset.annotation.State;
import java.util.Map;
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
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.Property;

@Assets(
   state = @State(name = "%s", template = "parent_doric_capital", plural = true),
   item = @Model(name = "item/%s", parent = "block/%s", template = "item/parent_cube", plural = true),
   block = {
         @Model(name = "block/%s", template = "block/parent_doric_capital_full", plural = true),
         @Model(name = "block/%s_n", template = "block/parent_doric_capital_n", plural = true),
         @Model(name = "block/%s_ne", template = "block/parent_doric_capital_ne", plural = true),
         @Model(name = "block/%s_nse", template = "block/parent_doric_capital_nse", plural = true),
         @Model(name = "block/%s_fullplain", template = "block/parent_doric_capital_fullplain", plural = true),
         @Model(name = "block/%s_ns", template = "block/parent_doric_capital_ns", plural = true),
         @Model(name = "block/%s_ns_1", template = "block/parent_doric_capital_ns_1", plural = true)
   }
)
public class CubeCapital extends Block {
   public static final BooleanProperty NORTH = PipeBlock.NORTH;
   public static final BooleanProperty EAST = PipeBlock.EAST;
   public static final BooleanProperty SOUTH = PipeBlock.SOUTH;
   public static final BooleanProperty WEST = PipeBlock.WEST;
   protected static final Map<Direction, BooleanProperty> PROPERTY_BY_DIRECTION = PipeBlock.PROPERTY_BY_DIRECTION
      .entrySet()
      .stream()
      .filter(p_52346_ -> ((Direction)p_52346_.getKey()).getAxis().isHorizontal())
      .collect(Util.toMap());

   public CubeCapital(Properties properties) {
      super(properties);
      this.registerDefaultState(
         (BlockState)((BlockState)((BlockState)((BlockState)((BlockState)this.stateDefinition.any()).setValue(NORTH, false)).setValue(EAST, false))
               .setValue(SOUTH, false))
            .setValue(WEST, false)
      );
   }

   public boolean func_220111_a(BlockState p_220111_1_, boolean p_220111_2_, Direction p_220111_3_) {
      Block block = p_220111_1_.getBlock();
      return block instanceof CubeCapital;
   }

   public BlockState getStateForPlacement(BlockPlaceContext context) {
      BlockGetter iblockreader = context.getLevel();
      BlockPos blockpos = context.getClickedPos();
      BlockPos blockpos1 = blockpos.north();
      BlockPos blockpos2 = blockpos.east();
      BlockPos blockpos3 = blockpos.south();
      BlockPos blockpos4 = blockpos.west();
      BlockState blockstate = iblockreader.getBlockState(blockpos1);
      BlockState blockstate1 = iblockreader.getBlockState(blockpos2);
      BlockState blockstate2 = iblockreader.getBlockState(blockpos3);
      BlockState blockstate3 = iblockreader.getBlockState(blockpos4);
      return (BlockState)((BlockState)((BlockState)((BlockState)super.getStateForPlacement(context)
                  .setValue(NORTH, this.func_220111_a(blockstate, blockstate.isFaceSturdy(iblockreader, blockpos1, Direction.SOUTH), Direction.SOUTH)))
               .setValue(EAST, this.func_220111_a(blockstate1, blockstate1.isFaceSturdy(iblockreader, blockpos2, Direction.WEST), Direction.WEST)))
            .setValue(SOUTH, this.func_220111_a(blockstate2, blockstate2.isFaceSturdy(iblockreader, blockpos3, Direction.NORTH), Direction.NORTH)))
         .setValue(WEST, this.func_220111_a(blockstate3, blockstate3.isFaceSturdy(iblockreader, blockpos4, Direction.EAST), Direction.EAST));
   }

   public BlockState updateShape(BlockState stateIn, Direction facing, BlockState facingState, LevelAccessor world, BlockPos currentPos, BlockPos facingPos) {
      return facing.getAxis().getPlane() == Plane.HORIZONTAL
         ? (BlockState)stateIn.setValue(
            (Property)PROPERTY_BY_DIRECTION.get(facing),
            this.func_220111_a(facingState, facingState.isFaceSturdy(world, facingPos, facing.getOpposite()), facing.getOpposite())
         )
         : super.updateShape(stateIn, facing, facingState, world, currentPos, facingPos);
   }

   protected void createBlockStateDefinition(Builder<Block, BlockState> builder) {
      builder.add(new Property[]{NORTH, EAST, WEST, SOUTH});
   }
}
