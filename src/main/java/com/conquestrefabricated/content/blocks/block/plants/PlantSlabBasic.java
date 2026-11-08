package com.conquestrefabricated.content.blocks.block.plants;

import com.conquestrefabricated.content.blocks.block.Slab;
import com.conquestrefabricated.core.asset.annotation.Assets;
import com.conquestrefabricated.core.asset.annotation.Model;
import com.conquestrefabricated.core.asset.annotation.State;
import com.conquestrefabricated.core.block.builder.Props;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour.OffsetType;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.Half;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

@Assets(
   state = @State(name = "%s_slab", template = "parent_slab_basic"),
   item = @Model(name = "item/%s_slab", parent = "block/%s_slab_bottom_4", template = "item/acacia_slab"),
   block = {
         @Model(name = "block/%s_slab_bottom_4", template = "block/parent_slab_bottom_4"),
         @Model(name = "block/%s_slab_top_4", template = "block/parent_slab_top_4")
   }
)
public class PlantSlabBasic extends Bush {
   public static final EnumProperty<Half> TYPE_UPDOWN = EnumProperty.create("type", Half.class);

   public PlantSlabBasic(Props props) {
      super(props);
      this.registerDefaultState(
         (BlockState)((BlockState)((BlockState)((BlockState)((BlockState)this.stateDefinition.any()).setValue(TYPE_UPDOWN, Half.BOTTOM))
                  .setValue(WATERLOGGED, false))
               .setValue(LAYERS, 8))
            .setValue(OFFSET_TOGGLE, false)
      );
   }

   public boolean useShapeForLightOcclusion(BlockState state) {
      return true;
   }

   @Override
   public OffsetType getOffsetType() {
      return OffsetType.NONE;
   }

   public boolean propagatesSkylightDown(BlockState p_200123_1_, BlockGetter p_200123_2_, BlockPos p_200123_3_) {
      return false;
   }

   @Override
   public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
      return state.getValue(TYPE_UPDOWN) == Half.TOP ? Slab.TOP_SHAPE[3] : Slab.BOTTOM_SHAPE[3];
   }

   public VoxelShape getCollisionShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
      return state.getValue(LAYERS) < 8 ? Shapes.empty() : super.getShape(state, world, pos, context);
   }

   @Override
   public BlockState getStateForPlacement(BlockPlaceContext context) {
      BlockPos blockpos = context.getClickedPos();
      FluidState fluid = context.getLevel().getFluidState(context.getClickedPos());
      BlockState state2 = (BlockState)((BlockState)super.getStateForPlacement(context).setValue(TYPE_UPDOWN, Half.BOTTOM))
         .setValue(WATERLOGGED, fluid.getType() == Fluids.WATER);
      Direction facing = context.getClickedFace();
      return facing == Direction.DOWN || facing != Direction.UP && context.getClickLocation().y - blockpos.getY() > 0.5
         ? (BlockState)state2.setValue(TYPE_UPDOWN, Half.TOP)
         : state2;
   }

   @Override
   protected void createBlockStateDefinition(Builder<Block, BlockState> builder) {
      builder.add(new Property[]{WATERLOGGED, LAYERS, TYPE_UPDOWN, OFFSET_TOGGLE});
   }
}
