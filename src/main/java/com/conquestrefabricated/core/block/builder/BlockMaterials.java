package com.conquestrefabricated.core.block.builder;

import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;

public class BlockMaterials {
   public static final Properties WOOD_SPECIAL = Properties.of().mapColor(MapColor.WOOD).forceSolidOn().replaceable().pushReaction(PushReaction.NORMAL);
   public static final Properties ROCK_SPECIAL = Properties.of().mapColor(MapColor.STONE).forceSolidOn().pushReaction(PushReaction.NORMAL);

   public BlockMaterials() {
   }
}
