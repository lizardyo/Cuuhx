package com.conquestrefabricated.content.blocks.block.vanilla;

import com.conquestrefabricated.content.blocks.block.Layer;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.BaseCoralPlantTypeBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class CoralPlantVanilla extends BaseCoralPlantTypeBlock {
   public static final BooleanProperty LIVING = BooleanProperty.create("living");
   protected static final VoxelShape SHAPE = Block.box(2.0, 0.0, 2.0, 14.0, 15.0, 14.0);

   public CoralPlantVanilla(Properties properties) {
      super(properties);
      this.registerDefaultState((BlockState)((BlockState)this.stateDefinition.any()).setValue(LIVING, true));
   }

   public void onPlace(BlockState state, Level worldIn, BlockPos pos, BlockState oldState, boolean isMoving) {
      this.tryScheduleDieTick(state, worldIn, pos);
   }

   public void tick(BlockState state, ServerLevel worldIn, BlockPos pos, RandomSource rand) {
      if (!scanForWater(state, worldIn, pos)) {
         worldIn.setBlock(pos, (BlockState)((BlockState)this.defaultBlockState().setValue(LIVING, false)).setValue(WATERLOGGED, false), 2);
      }
   }

   public BlockState updateShape(BlockState stateIn, Direction facing, BlockState facingState, LevelAccessor worldIn, BlockPos currentPos, BlockPos facingPos) {
      if (facing == Direction.DOWN && !stateIn.canSurvive(worldIn, currentPos)) {
         return Blocks.AIR.defaultBlockState();
      }

      this.tryScheduleDieTick(stateIn, worldIn, currentPos);
      if ((Boolean)stateIn.getValue(WATERLOGGED)) {
         worldIn.scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickDelay(worldIn));
      }

      return super.updateShape(stateIn, facing, facingState, worldIn, currentPos, facingPos);
   }

   public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
      return SHAPE;
   }

   public boolean canSurvive(BlockState state, LevelReader worldIn, BlockPos pos) {
      BlockPos blockpos = pos.below();
      return worldIn.getBlockState(blockpos).isFaceSturdy(worldIn, blockpos, Direction.UP) || worldIn.getBlockState(blockpos).getBlock() instanceof Layer;
   }

   protected void createBlockStateDefinition(Builder<Block, BlockState> builder) {
      builder.add(new Property[]{WATERLOGGED, LIVING});
   }
}
