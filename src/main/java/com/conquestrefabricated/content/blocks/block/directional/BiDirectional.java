package com.conquestrefabricated.content.blocks.block.directional;

import com.conquestrefabricated.core.block.base.BiDirectionalShape;
import com.conquestrefabricated.core.block.builder.Props;
import com.conquestrefabricated.core.block.properties.BidirectionalShape;
import java.util.List;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.VoxelShape;

public class BiDirectional extends BiDirectionalShape {
   private final List<VoxelShape> hitBox;

   public BiDirectional(Props props) {
      super(props.toSettings());
      this.hitBox = props.get("hitBox", List.class);
   }

   @Override
   public VoxelShape getShape(BlockState state) {
      boolean hasTwoShapes = this.hitBox.size() == 2;
      return state.getValue(DIRECTION) == BidirectionalShape.EAST_WEST ? this.hitBox.get(hasTwoShapes ? 1 : 0) : this.hitBox.get(0);
   }
}
