package com.conquestrefabricated.content.blocks.block.trees;

import com.conquestrefabricated.core.asset.annotation.Render;
import com.conquestrefabricated.core.block.builder.Props;
import com.conquestrefabricated.core.util.RenderLayer;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.LeavesBlock;

@Render(RenderLayer.CUTOUT_MIPPED)
public class Leaves extends LeavesBlock {
   private final ItemLike sapling;

   public Leaves(Props props) {
      super(props.toSettings());
      this.sapling = props.get("sapling", ItemLike.class);
   }
}
