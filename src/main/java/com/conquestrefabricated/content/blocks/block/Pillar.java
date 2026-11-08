package com.conquestrefabricated.content.blocks.block;

import com.conquestrefabricated.content.blocks.util.PlacementHelper;
import com.conquestrefabricated.core.asset.annotation.Assets;
import com.conquestrefabricated.core.asset.annotation.Model;
import com.conquestrefabricated.core.asset.annotation.State;
import com.conquestrefabricated.core.block.base.WaterloggedShape;
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
   state = @State(name = "%s_pillar", template = "parent_pillar"),
   item = @Model(name = "item/%s_pillar", parent = "block/%s_pillar_4", template = "item/dragon_egg"),
   block = {
         @Model(name = "block/%s_pillar_2", template = "block/parent_pillar_2"),
         @Model(name = "block/%s_pillar_4", template = "block/parent_pillar_4"),
         @Model(name = "block/%s_pillar_6", template = "block/parent_pillar_6")
   }
)
public class Pillar extends WaterloggedShape {
   public static final IntegerProperty LAYERS = IntegerProperty.create("layer", 1, 3);
   protected static final VoxelShape[] SHAPE = new VoxelShape[]{
      Block.box(6.0, 0.0, 6.0, 10.0, 16.0, 10.0), Block.box(4.0, 0.0, 4.0, 12.0, 16.0, 12.0), Block.box(2.0, 0.0, 2.0, 14.0, 16.0, 14.0)
   };
   private Block fullBlock;

   public Pillar(Props properties) {
      super(properties.toSettings());
      this.registerDefaultState((BlockState)((BlockState)this.stateDefinition.any()).setValue(WATERLOGGED, false));
      this.fullBlock = properties.getParent().getBlock();
   }

   @Override
   public VoxelShape getShape(BlockState state) {
      return SHAPE[state.getValue(LAYERS) - 1];
   }

   public boolean canBeReplaced(BlockState state, BlockPlaceContext context) {
      int i = (Integer)state.getValue(LAYERS);
      Direction facing = context.getClickedFace();
      if (context.getItemInHand().getItem() != this.asItem() || i > 4) {
         return false;
      } else {
         return !PlacementHelper.replacingClickedOnBlock(context) ? true : facing != Direction.UP && facing != Direction.DOWN;
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
