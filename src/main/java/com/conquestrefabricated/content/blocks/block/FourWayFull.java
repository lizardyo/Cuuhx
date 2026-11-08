package com.conquestrefabricated.content.blocks.block;

import com.conquestrefabricated.core.asset.annotation.Assets;
import com.conquestrefabricated.core.asset.annotation.Model;
import com.conquestrefabricated.core.asset.annotation.Render;
import com.conquestrefabricated.core.asset.annotation.State;
import com.conquestrefabricated.core.util.RenderLayer;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.IronBarsBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

@Assets(
   state = @State(name = "%s", template = "parent_pane"),
   item = @Model(name = "item/%s", parent = "block/%s_pane_ns", template = "item/parent_pane"),
   render = @Render(RenderLayer.CUTOUT),
   block = {
         @Model(name = "block/%s_pane_n", template = "block/parent_flatpane_n"),
         @Model(name = "block/%s_pane_ne", template = "block/parent_flatpane_ne"),
         @Model(name = "block/%s_pane_ns", template = "block/parent_flatpane_ns"),
         @Model(name = "block/%s_pane_nse", template = "block/parent_flatpane_nse"),
         @Model(name = "block/%s_pane_nsew", template = "block/parent_flatpane_nsew"),
         @Model(name = "block/%s_pane_post", template = "block/parent_flatpane_post")
   }
)
public class FourWayFull extends IronBarsBlock {
   protected static final VoxelShape SHAPE = Block.box(1.0, 10.0, 1.0, 15.0, 16.0, 15.0);

   public FourWayFull(Properties properties) {
      super(properties);
   }

   public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
      return Shapes.block();
   }

   public VoxelShape getCollisionShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
      return SHAPE;
   }

   public VoxelShape getOcclusionShape(BlockState state, BlockGetter world, BlockPos pos) {
      return Shapes.empty();
   }
}
