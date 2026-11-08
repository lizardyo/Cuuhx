package com.conquestrefabricated.content.blocks.block;

import com.conquestrefabricated.core.asset.annotation.Assets;
import com.conquestrefabricated.core.asset.annotation.Model;
import com.conquestrefabricated.core.asset.annotation.State;
import com.conquestrefabricated.core.block.base.WaterloggedShape;
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
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.Half;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

@Assets(
   state = @State(name = "%s_slab", template = "parent_slab"),
   item = @Model(name = "item/%s_slab", parent = "block/%s_slab_bottom_4", template = "item/acacia_slab"),
   block = {
         @Model(name = "block/%s_slab_bottom_1", template = "block/parent_slab_bottom_1"),
         @Model(name = "block/%s_slab_bottom_2", template = "block/parent_slab_bottom_2"),
         @Model(name = "block/%s_slab_bottom_3", template = "block/parent_slab_bottom_3"),
         @Model(name = "block/%s_slab_bottom_4", template = "block/parent_slab_bottom_4"),
         @Model(name = "block/%s_slab_bottom_5", template = "block/parent_slab_bottom_5"),
         @Model(name = "block/%s_slab_bottom_6", template = "block/parent_slab_bottom_6"),
         @Model(name = "block/%s_slab_bottom_7", template = "block/parent_slab_bottom_7"),
         @Model(name = "block/%s_slab_top_1", template = "block/parent_slab_top_1"),
         @Model(name = "block/%s_slab_top_2", template = "block/parent_slab_top_2"),
         @Model(name = "block/%s_slab_top_3", template = "block/parent_slab_top_3"),
         @Model(name = "block/%s_slab_top_4", template = "block/parent_slab_top_4"),
         @Model(name = "block/%s_slab_top_5", template = "block/parent_slab_top_5"),
         @Model(name = "block/%s_slab_top_6", template = "block/parent_slab_top_6"),
         @Model(name = "block/%s_slab_top_7", template = "block/parent_slab_top_7"),
         @Model(name = "block/%s", template = "block/parent_cube")
   }
)
public class Slab extends WaterloggedShape {
   public static final IntegerProperty LAYERS = BlockStateProperties.LAYERS;
   public static final EnumProperty<Half> TYPE_UPDOWN = EnumProperty.create("type", Half.class);
   public static final VoxelShape[] BOTTOM_SHAPE = new VoxelShape[]{
      Block.box(0.0, 0.0, 0.0, 16.0, 2.0, 16.0),
      Block.box(0.0, 0.0, 0.0, 16.0, 4.0, 16.0),
      Block.box(0.0, 0.0, 0.0, 16.0, 6.0, 16.0),
      Block.box(0.0, 0.0, 0.0, 16.0, 8.0, 16.0),
      Block.box(0.0, 0.0, 0.0, 16.0, 10.0, 16.0),
      Block.box(0.0, 0.0, 0.0, 16.0, 12.0, 16.0),
      Block.box(0.0, 0.0, 0.0, 16.0, 14.0, 16.0),
      Block.box(0.0, 0.0, 0.0, 16.0, 16.0, 16.0)
   };
   public static final VoxelShape[] TOP_SHAPE = new VoxelShape[]{
      Block.box(0.0, 14.0, 0.0, 16.0, 16.0, 16.0),
      Block.box(0.0, 12.0, 0.0, 16.0, 16.0, 16.0),
      Block.box(0.0, 10.0, 0.0, 16.0, 16.0, 16.0),
      Block.box(0.0, 8.0, 0.0, 16.0, 16.0, 16.0),
      Block.box(0.0, 6.0, 0.0, 16.0, 16.0, 16.0),
      Block.box(0.0, 4.0, 0.0, 16.0, 16.0, 16.0),
      Block.box(0.0, 2.0, 0.0, 16.0, 16.0, 16.0),
      Block.box(0.0, 0.0, 0.0, 16.0, 16.0, 16.0)
   };

   public Slab(Props props) {
      super(props.toSettings());
      this.registerDefaultState((BlockState)((BlockState)((BlockState)this.stateDefinition.any()).setValue(TYPE_UPDOWN, Half.BOTTOM)).setValue(WATERLOGGED, false));
   }

   public boolean useShapeForLightOcclusion(BlockState state) {
      return true;
   }

   public boolean canBeReplaced(BlockState state, BlockPlaceContext context) {
      Direction facing = context.getClickedFace();
      ItemStack item = context.getItemInHand();
      if (item.getItem() != this.asItem() || (Integer)state.getValue(LAYERS) == 8) {
         return false;
      } else {
         return state.getValue(TYPE_UPDOWN) == Half.BOTTOM ? facing == Direction.UP : facing == Direction.DOWN;
      }
   }

   public boolean propagatesSkylightDown(BlockState p_200123_1_, BlockGetter p_200123_2_, BlockPos p_200123_3_) {
      return false;
   }

   @Override
   public VoxelShape getShape(BlockState state) {
      return state.getValue(TYPE_UPDOWN) == Half.TOP ? TOP_SHAPE[state.getValue(LAYERS) - 1] : BOTTOM_SHAPE[state.getValue(LAYERS) - 1];
   }

   @Override
   public VoxelShape getCollisionShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
      if ((Integer)state.getValue(LAYERS) == 8) {
         return Layer.SPECIAL_FULL_SHAPE_COLLISION;
      } else {
         return state.getValue(TYPE_UPDOWN) == Half.TOP ? TOP_SHAPE[state.getValue(LAYERS) - 1] : BOTTOM_SHAPE[state.getValue(LAYERS) - 1];
      }
   }

   @Override
   public BlockState getStateForPlacement(BlockPlaceContext context) {
      BlockPos blockpos = context.getClickedPos();
      BlockState state = context.getLevel().getBlockState(context.getClickedPos());
      if (state.getBlock() == this) {
         int i = (Integer)state.getValue(LAYERS);
         return (BlockState)state.setValue(LAYERS, Math.min(8, i + 1));
      } else {
         FluidState fluid = context.getLevel().getFluidState(context.getClickedPos());
         BlockState state2 = (BlockState)((BlockState)this.defaultBlockState().setValue(TYPE_UPDOWN, Half.BOTTOM))
            .setValue(WATERLOGGED, fluid.getType() == Fluids.WATER);
         Direction facing = context.getClickedFace();
         return facing == Direction.DOWN || facing != Direction.UP && context.getClickLocation().y - blockpos.getY() > 0.5
            ? (BlockState)state2.setValue(TYPE_UPDOWN, Half.TOP)
            : state2;
      }
   }

   @Override
   protected void addProperties(Builder<Block, BlockState> container) {
      container.add(new Property[]{TYPE_UPDOWN}).add(new Property[]{LAYERS});
   }
}
