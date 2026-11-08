package com.conquestrefabricated.content.blocks.block;

import com.conquestrefabricated.content.blocks.util.PlacementHelper;
import com.conquestrefabricated.core.asset.annotation.Assets;
import com.conquestrefabricated.core.asset.annotation.Model;
import com.conquestrefabricated.core.asset.annotation.State;
import com.conquestrefabricated.core.block.base.WaterloggedHorizontalDirectionalShape;
import com.conquestrefabricated.core.block.builder.Props;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.phys.shapes.VoxelShape;

@Assets(
   state = @State(name = "%s_vertical_slab", template = "parent_vertical_slab_lesslayers"),
   item = @Model(name = "item/%s_vertical_slab", parent = "block/%s_vertical_slab_4", template = "item/parent_vertical_slab"),
   block = {
         @Model(name = "block/%s_vertical_slab_2", template = "block/parent_vertical_slab_2"),
         @Model(name = "block/%s_vertical_slab_4", template = "block/parent_vertical_slab_4"),
         @Model(name = "block/%s_vertical_slab_6", template = "block/parent_vertical_slab_6")
   }
)
public class VerticalSlabLessLayers extends WaterloggedHorizontalDirectionalShape {
   public static final IntegerProperty LAYERS = IntegerProperty.create("layer", 1, 3);
   protected Block fullBlock;

   public VerticalSlabLessLayers(Props props) {
      super(props.toSettings());
      this.registerDefaultState((BlockState)((BlockState)((BlockState)this.stateDefinition.any()).setValue(DIRECTION, Direction.NORTH)).setValue(WATERLOGGED, false));
      this.fullBlock = props.getParent().getBlock();
   }

   @Override
   public VoxelShape getShape(BlockState state) {
      switch ((Direction)state.getValue(DIRECTION)) {
         case NORTH:
         default:
            return VerticalSlab.NORTH_SHAPE[state.getValue(LAYERS)];
         case SOUTH:
            return VerticalSlab.SOUTH_SHAPE[state.getValue(LAYERS)];
         case WEST:
            return VerticalSlab.WEST_SHAPE[state.getValue(LAYERS)];
         case EAST:
            return VerticalSlab.EAST_SHAPE[state.getValue(LAYERS)];
      }
   }

   public boolean useShapeForLightOcclusion(BlockState state) {
      return true;
   }

   public boolean canBeReplaced(BlockState state, BlockPlaceContext context) {
      int i = (Integer)state.getValue(LAYERS);
      if (context.getItemInHand().getItem() != this.asItem() || i > 3) {
         return false;
      } else {
         return PlacementHelper.replacingClickedOnBlock(context) ? context.getClickedFace() == state.getValue(DIRECTION) : true;
      }
   }

   @Override
   public BlockState getStateForPlacement(BlockPlaceContext context) {
      BlockState blockstate = context.getLevel().getBlockState(context.getClickedPos());
      if (blockstate.getBlock() == this) {
         int i = (Integer)blockstate.getValue(LAYERS);
         return i == 3 ? this.fullBlock.defaultBlockState() : (BlockState)blockstate.setValue(LAYERS, Math.min(3, i + 1));
      } else {
         return super.getStateForPlacement(context);
      }
   }

   @Override
   protected void addProperties(Builder<Block, BlockState> builder) {
      builder.add(new Property[]{LAYERS});
   }
}
