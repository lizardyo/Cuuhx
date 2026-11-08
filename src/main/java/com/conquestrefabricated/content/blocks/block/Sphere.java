package com.conquestrefabricated.content.blocks.block;

import com.conquestrefabricated.content.blocks.util.Interactions;
import com.conquestrefabricated.core.asset.annotation.Assets;
import com.conquestrefabricated.core.asset.annotation.ItemDescription;
import com.conquestrefabricated.core.asset.annotation.Model;
import com.conquestrefabricated.core.asset.annotation.State;
import com.conquestrefabricated.core.block.base.WaterloggedShape;
import com.conquestrefabricated.core.block.builder.Props;
import com.conquestrefabricated.core.block.properties.SphereShape;
import com.conquestrefabricated.core.block.properties.Waterloggable;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

@Assets(
   state = @State(name = "%s_sphere", template = "parent_sphere"),
   item = @Model(name = "item/%s_sphere", parent = "block/%s_sphere", template = "item/dragon_egg"),
   block = {
         @Model(name = "block/%s_sphere", template = "block/templates/parent_sphere"),
         @Model(name = "block/%s_sphere_dragonegg", template = "block/templates/parent_dragonegg"),
         @Model(name = "block/%s_sphere_small", template = "block/templates/parent_sphere_small")
   }
)
@ItemDescription(description = "toggle_3")
public class Sphere extends WaterloggedShape implements Waterloggable {
   private static final VoxelShape SPHERE_SMALL = Shapes.or(
      Block.box(4.0, 1.0, 4.0, 12.0, 9.0, 12.0),
      new VoxelShape[]{
         Block.box(5.5, 0.0, 5.5, 10.5, 1.0, 10.5),
         Block.box(5.5, 9.0, 5.5, 10.5, 10.0, 10.5),
         Block.box(3.0, 2.5, 5.5, 4.0, 7.5, 10.5),
         Block.box(12.0, 2.5, 5.5, 13.0, 7.5, 10.5),
         Block.box(5.5, 2.5, 12.0, 10.5, 7.5, 13.0),
         Block.box(5.5, 2.5, 2.9999, 10.5, 7.5, 3.9999)
      }
   );
   private static final VoxelShape SPHERE = Shapes.or(
      Block.box(1.64, 1.59, 1.64, 14.36, 14.31, 14.36),
      new VoxelShape[]{
         Block.box(4.025, 3.975, 0.04979, 11.975, 11.925, 1.63979),
         Block.box(4.025, 0.0, 4.025, 11.975, 1.59, 11.975),
         Block.box(4.025, 14.31, 4.025, 11.975, 15.9, 11.975),
         Block.box(0.05, 3.975, 4.025, 1.64, 11.925, 11.975),
         Block.box(14.36, 3.975, 4.025, 15.95, 11.925, 11.975),
         Block.box(4.025, 3.975, 14.36, 11.975, 11.925, 15.95)
      }
   );
   private static final VoxelShape DRAGONEGG = Shapes.or(
      Block.box(6.0, 15.0, 6.0, 10.0, 16.0, 10.0),
      new VoxelShape[]{
         Block.box(5.0, 14.0, 5.0, 11.0, 15.0, 11.0),
         Block.box(5.0, 13.0, 5.0, 11.0, 14.0, 11.0),
         Block.box(3.0, 11.0, 3.0, 13.0, 13.0, 13.0),
         Block.box(2.0, 8.0, 2.0, 14.0, 11.0, 14.0),
         Block.box(1.0, 3.0, 1.0, 15.0, 8.0, 15.0),
         Block.box(2.0, 1.0, 2.0, 14.0, 3.0, 14.0),
         Block.box(3.0, 0.0, 3.0, 13.0, 1.0, 13.0)
      }
   );
   public static final EnumProperty TYPE = EnumProperty.create("type", SphereShape.class);

   public Sphere(Props properties) {
      super(properties.toSettings());
      this.registerDefaultState((BlockState)((BlockState)((BlockState)this.stateDefinition.any()).setValue(TYPE, SphereShape.LARGE)).setValue(WATERLOGGED, false));
   }

   @Override
   public VoxelShape getShape(BlockState state) {
      switch (state.getValue(TYPE).toString()) {
         case "egg":
         default:
            return DRAGONEGG;
         case "small":
            return SPHERE_SMALL;
         case "large":
            return SPHERE;
      }
   }

   public InteractionResult use(BlockState state, Level level, BlockPos blockPos, Player player, InteractionHand hand, BlockHitResult hitResult) {
      return Interactions.onUseToggleItem(player, level, blockPos, state, TYPE);
   }

   @Override
   protected void addProperties(Builder<Block, BlockState> builder) {
      builder.add(new Property[]{TYPE});
   }
}
