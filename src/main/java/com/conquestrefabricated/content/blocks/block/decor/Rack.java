package com.conquestrefabricated.content.blocks.block.decor;

import com.conquestrefabricated.core.asset.annotation.Render;
import com.conquestrefabricated.core.block.builder.Props;
import com.conquestrefabricated.core.block.properties.ParallelConnectionShape2;
import com.conquestrefabricated.core.util.RenderLayer;
import java.util.List;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.phys.shapes.VoxelShape;

@Render(RenderLayer.CUTOUT)
public class Rack extends ParallelConnecting {
   protected static final VoxelShape SHAPE = Block.box(1.0, 0.0, 1.0, 15.0, 16.0, 15.0);
   private final List<VoxelShape> hitBox;
   public static final EnumProperty<ParallelConnectionShape2> FORM = EnumProperty.create("shape", ParallelConnectionShape2.class);

   public Rack(Props props) {
      super(props);
      this.hitBox = props.get("hitBox", List.class);
   }

   @Override
   public VoxelShape getShape(BlockState state) {
      boolean hasFourShapes = this.hitBox.size() == 4;
      switch ((Direction)state.getValue(DIRECTION)) {
         case EAST:
            return this.hitBox.get(hasFourShapes ? 1 : 0);
         case SOUTH:
            return this.hitBox.get(hasFourShapes ? 2 : 0);
         case WEST:
            return this.hitBox.get(hasFourShapes ? 3 : 0);
         default:
            return this.hitBox.get(0);
      }
   }
}
