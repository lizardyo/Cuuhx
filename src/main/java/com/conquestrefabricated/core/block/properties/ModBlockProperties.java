package com.conquestrefabricated.core.block.properties;

import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;

public class ModBlockProperties {
   public static final IntegerProperty TOGGLE_2 = IntegerProperty.create("toggle", 1, 2);
   public static final BooleanProperty OFFSET_TOGGLE = BooleanProperty.create("offset_toggle");

   public ModBlockProperties() {
   }
}
