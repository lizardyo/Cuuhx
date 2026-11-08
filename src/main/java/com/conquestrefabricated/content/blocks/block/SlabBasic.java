package com.conquestrefabricated.content.blocks.block;

import com.conquestrefabricated.core.asset.annotation.Assets;
import com.conquestrefabricated.core.asset.annotation.Model;
import com.conquestrefabricated.core.asset.annotation.State;
import com.conquestrefabricated.core.block.base.WaterloggedShape;
import com.conquestrefabricated.core.block.builder.Props;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.Half;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.VoxelShape;

@Assets(
   state = @State(name = "%s_slab", template = "parent_slab_basic"),
   item = @Model(name = "item/%s_slab", parent = "block/%s_slab_bottom_4", template = "item/acacia_slab"),
   block = {
         @Model(name = "block/%s_slab_bottom_4", template = "block/parent_slab_bottom_4"),
         @Model(name = "block/%s_slab_top_4", template = "block/parent_slab_top_4")
   }
)
public class SlabBasic extends WaterloggedShape {
   public static final EnumProperty<Half> TYPE_UPDOWN = EnumProperty.create("type", Half.class);

   public SlabBasic(Props props) {
      super(props.toSettings());
      this.registerDefaultState((BlockState)((BlockState)((BlockState)this.stateDefinition.any()).setValue(TYPE_UPDOWN, Half.BOTTOM)).setValue(WATERLOGGED, false));
   }

   public boolean useShapeForLightOcclusion(BlockState state) {
      return true;
   }

   public boolean propagatesSkylightDown(BlockState p_200123_1_, BlockGetter p_200123_2_, BlockPos p_200123_3_) {
      return false;
   }

   @Override
   public VoxelShape getShape(BlockState state) {
      return state.getValue(TYPE_UPDOWN) == Half.TOP ? Slab.TOP_SHAPE[3] : Slab.BOTTOM_SHAPE[3];
   }

   @Override
   public BlockState getStateForPlacement(BlockPlaceContext context) {
      BlockPos blockpos = context.getClickedPos();
      FluidState fluid = context.getLevel().getFluidState(context.getClickedPos());
      BlockState state2 = (BlockState)((BlockState)this.defaultBlockState().setValue(TYPE_UPDOWN, Half.BOTTOM))
         .setValue(WATERLOGGED, fluid.getType() == Fluids.WATER);
      Direction facing = context.getClickedFace();
      return facing == Direction.DOWN || facing != Direction.UP && context.getClickLocation().y - blockpos.getY() > 0.5
         ? (BlockState)state2.setValue(TYPE_UPDOWN, Half.TOP)
         : state2;
   }

   @Override
   protected void addProperties(Builder<Block, BlockState> container) {
      container.add(new Property[]{TYPE_UPDOWN});
   }
}
