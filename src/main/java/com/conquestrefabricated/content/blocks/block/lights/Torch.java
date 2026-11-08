package com.conquestrefabricated.content.blocks.block.lights;

import com.conquestrefabricated.content.blocks.BlockSettingsAccessor;
import com.conquestrefabricated.content.blocks.CustomOffsetType;
import com.conquestrefabricated.content.blocks.util.PlacementHelper;
import com.conquestrefabricated.core.asset.annotation.Render;
import com.conquestrefabricated.core.asset.annotation.SpecialOffset;
import com.conquestrefabricated.core.block.builder.SpecialOffsetType;
import com.conquestrefabricated.core.block.properties.ModBlockProperties;
import com.conquestrefabricated.core.block.properties.Waterloggable;
import com.conquestrefabricated.core.util.RenderLayer;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour.OffsetType;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

@Render(RenderLayer.CUTOUT)
@SpecialOffset(offsetType = SpecialOffsetType.XYZ)
public class Torch extends Block implements Waterloggable {
   public static final BooleanProperty OFFSET_TOGGLE = ModBlockProperties.OFFSET_TOGGLE;
   public static final DirectionProperty FACING = DirectionalBlock.FACING;
   public static final IntegerProperty LIGHT_0_3 = IntegerProperty.create("light", 0, 3);
   protected static final VoxelShape EAST_OPEN_AABB = Block.box(0.0, 0.0, 4.0, 10.0, 16.0, 11.0);
   protected static final VoxelShape WEST_OPEN_AABB = Block.box(6.0, 0.0, 4.0, 16.0, 16.0, 11.0);
   protected static final VoxelShape SOUTH_OPEN_AABB = Block.box(4.0, 0.0, 0.0, 11.0, 16.0, 10.0);
   protected static final VoxelShape NORTH_OPEN_AABB = Block.box(4.0, 0.0, 6.0, 11.0, 16.0, 16.0);
   protected static final VoxelShape BOTTOM_AABB = Block.box(4.0, 0.0, 4.0, 12.0, 16.0, 12.0);
   protected static final VoxelShape TOP_AABB = Block.box(4.0, 0.0, 4.0, 12.0, 16.0, 12.0);

   public Torch(Properties properties) {
      super(((BlockSettingsAccessor)properties).setCustomOffsetter(CustomOffsetType.LAYER_XYZ_NSEWUD).offsetType(OffsetType.NONE).dynamicShape());
      this.registerDefaultState(
         (BlockState)((BlockState)((BlockState)((BlockState)((BlockState)this.stateDefinition.any()).setValue(FACING, Direction.NORTH)).setValue(LIGHT_0_3, 0))
               .setValue(WATERLOGGED, false))
            .setValue(OFFSET_TOGGLE, false)
      );
   }

   public BlockState getStateForPlacement(BlockPlaceContext context) {
      boolean isSlab = PlacementHelper.isFacingSlab(context);
      return (BlockState)((BlockState)this.defaultBlockState().setValue(FACING, context.getClickedFace())).setValue(OFFSET_TOGGLE, isSlab);
   }

   public VoxelShape getCollisionShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
      return state.getValue(OFFSET_TOGGLE) ? Shapes.empty() : super.getCollisionShape(state, worldIn, pos, context);
   }

   public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
      switch ((Direction)state.getValue(FACING)) {
         case NORTH:
         default:
            return NORTH_OPEN_AABB;
         case SOUTH:
            return SOUTH_OPEN_AABB;
         case WEST:
            return WEST_OPEN_AABB;
         case EAST:
            return EAST_OPEN_AABB;
         case DOWN:
            return TOP_AABB;
         case UP:
            return BOTTOM_AABB;
      }
   }

   protected void createBlockStateDefinition(Builder<Block, BlockState> builder) {
      builder.add(new Property[]{FACING, LIGHT_0_3, WATERLOGGED, OFFSET_TOGGLE});
   }

   public InteractionResult use(BlockState state, Level level, BlockPos blockPos, Player player, InteractionHand hand, BlockHitResult hitResult) {
      if (!player.getAbilities().mayBuild) {
         return InteractionResult.FAIL;
      } else if (player.getItemInHand(hand).getItem() == Items.FLINT_AND_STEEL) {
         level.setBlock(blockPos, (BlockState)state.setValue(LIGHT_0_3, 3), 3);
         return InteractionResult.SUCCESS;
      } else {
         return InteractionResult.FAIL;
      }
   }

   public FluidState getFluidState(BlockState state) {
      return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
   }

   public BlockState updateShape(BlockState stateIn, Direction facing, BlockState facingState, LevelAccessor world, BlockPos currentPos, BlockPos facingPos) {
      if ((Boolean)stateIn.getValue(WATERLOGGED)) {
         world.scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickDelay(world));
      }

      return super.updateShape(stateIn, facing, facingState, world, currentPos, facingPos);
   }
}
