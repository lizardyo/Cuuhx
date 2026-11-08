package com.conquestrefabricated.core.block.base;

import com.conquestrefabricated.core.block.builder.Props;
import java.util.List;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public abstract class ShapeBlock extends Block {
   private final List<VoxelShape> hitBox;

   public ShapeBlock(Props props) {
      super(props.toSettings());
      this.hitBox = props.get("hitBox", List.class);
   }

   public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
      return this.getShape(state);
   }

   public VoxelShape getCollisionShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
      return !this.hasCollision ? Shapes.empty() : this.getShape(state);
   }

   public VoxelShape getInteractionShape(BlockState state, BlockGetter worldIn, BlockPos pos) {
      return !this.hasCollision ? Shapes.empty() : this.getShape(state);
   }

   public VoxelShape getShape(BlockState state) {
      return this.hitBox.get(0);
   }
}
