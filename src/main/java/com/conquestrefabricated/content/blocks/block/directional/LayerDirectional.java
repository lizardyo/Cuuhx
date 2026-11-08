package com.conquestrefabricated.content.blocks.block.directional;

import com.conquestrefabricated.content.blocks.util.PlacementHelper;
import com.conquestrefabricated.core.asset.annotation.Assets;
import com.conquestrefabricated.core.asset.annotation.Model;
import com.conquestrefabricated.core.asset.annotation.State;
import com.conquestrefabricated.core.block.base.WaterloggedHorizontalDirectionalShape;
import com.conquestrefabricated.core.block.builder.Props;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

@Assets(
   state = @State(name = "%s_layer", template = "parent_layer"),
   item = @Model(name = "item/%s_layer", parent = "block/%s_layer_height2", template = "item/snow"),
   block = {
         @Model(name = "block/%s_layer_height2", template = "block/snow_height2"),
         @Model(name = "block/%s_layer_height4", template = "block/snow_height4"),
         @Model(name = "block/%s_layer_height6", template = "block/snow_height6"),
         @Model(name = "block/%s_layer_height8", template = "block/snow_height8"),
         @Model(name = "block/%s_layer_height10", template = "block/snow_height10"),
         @Model(name = "block/%s_layer_height12", template = "block/snow_height12"),
         @Model(name = "block/%s_layer_height14", template = "block/snow_height14")
   }
)
public class LayerDirectional extends WaterloggedHorizontalDirectionalShape {
   public static final IntegerProperty LAYERS = BlockStateProperties.LAYERS;
   private static final VoxelShape[] BOTTOM_SHAPE = new VoxelShape[]{
      Block.box(0.0, 0.0, 0.0, 16.0, 2.0, 16.0),
      Block.box(0.0, 0.0, 0.0, 16.0, 4.0, 16.0),
      Block.box(0.0, 0.0, 0.0, 16.0, 6.0, 16.0),
      Block.box(0.0, 0.0, 0.0, 16.0, 8.0, 16.0),
      Block.box(0.0, 0.0, 0.0, 16.0, 10.0, 16.0),
      Block.box(0.0, 0.0, 0.0, 16.0, 12.0, 16.0),
      Block.box(0.0, 0.0, 0.0, 16.0, 14.0, 16.0),
      Block.box(0.0, 0.0, 0.0, 16.0, 16.0, 16.0)
   };
   private static final VoxelShape SPECIAL_FULL_SHAPE_COLLISION = Block.box(0.0, 2.0, 0.0, 16.0, 16.0, 16.0);

   public LayerDirectional(Props props) {
      super(props.toSettings());
      this.registerDefaultState((BlockState)((BlockState)((BlockState)this.stateDefinition.any()).setValue(LAYERS, 1)).setValue(WATERLOGGED, false));
   }

   public boolean canBeReplaced(BlockState state, BlockPlaceContext context) {
      if (!PlacementHelper.replacingClickedOnBlock(context)) {
         return false;
      }

      ItemStack item = context.getItemInHand();
      return item.getItem() != this.asItem() ? false : context.getClickedFace() == Direction.UP;
   }

   @Override
   public VoxelShape getShape(BlockState state) {
      return BOTTOM_SHAPE[state.getValue(LAYERS) - 1];
   }

   @Override
   public VoxelShape getCollisionShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
      return state.getValue(LAYERS) == 8 ? SPECIAL_FULL_SHAPE_COLLISION : BOTTOM_SHAPE[state.getValue(LAYERS) - 1];
   }

   @Override
   public BlockState getStateForPlacement(BlockPlaceContext context) {
      BlockState state = context.getLevel().getBlockState(context.getClickedPos());
      if (state.getBlock() == this) {
         int i = (Integer)state.getValue(LAYERS);
         return (BlockState)state.setValue(LAYERS, Math.min(8, i + 1));
      } else {
         FluidState fluid = context.getLevel().getFluidState(context.getClickedPos());
         return (BlockState)super.getStateForPlacement(context).setValue(WATERLOGGED, fluid.getType() == Fluids.WATER);
      }
   }

   @Override
   protected void addProperties(Builder<Block, BlockState> container) {
      container.add(new Property[]{LAYERS});
   }
}
