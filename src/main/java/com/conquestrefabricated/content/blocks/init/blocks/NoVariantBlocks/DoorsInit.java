package com.conquestrefabricated.content.blocks.init.blocks.NoVariantBlocks;

import com.conquestrefabricated.content.blocks.block.Door;
import com.conquestrefabricated.core.block.builder.VanillaProps;
import com.conquestrefabricated.core.block.factory.TypeList;
import com.conquestrefabricated.core.util.RenderLayer;

public class DoorsInit {
   public DoorsInit() {
   }

   public static void init() {
      VanillaProps.planks().name("oak_door").manual().register(TypeList.of(Door.class));
      VanillaProps.planks().name("heavy_wooden_door").manual().render(RenderLayer.CUTOUT).register(TypeList.of(Door.class));
      VanillaProps.planks().name("fancy_oak_door").manual().register(TypeList.of(Door.class));
      VanillaProps.planks().name("elegant_birch_door").manual().register(TypeList.of(Door.class));
      VanillaProps.planks().name("elegant_oak_door").manual().register(TypeList.of(Door.class));
      VanillaProps.planks().name("weathered_red_painted_plank_door").family("weathered_red_painted_planks").manual().register(TypeList.of(Door.class));
      VanillaProps.planks().name("red_painted_plank_door").family("red_painted_planks").manual().register(TypeList.of(Door.class));
      VanillaProps.planks()
         .name("weathered_light_red_painted_plank_door")
         .family("weathered_light_red_painted_planks")
         .manual()
         .register(TypeList.of(Door.class));
      VanillaProps.planks().name("light_red_painted_plank_door").family("light_red_painted_planks").manual().register(TypeList.of(Door.class));
      VanillaProps.planks().name("weathered_orange_painted_plank_door").family("weathered_orange_painted_planks").manual().register(TypeList.of(Door.class));
      VanillaProps.planks().name("orange_painted_plank_door").family("orange_painted_planks").manual().register(TypeList.of(Door.class));
      VanillaProps.planks().name("weathered_yellow_painted_plank_door").family("weathered_yellow_painted_planks").manual().register(TypeList.of(Door.class));
      VanillaProps.planks().name("yellow_painted_plank_door").family("yellow_painted_planks").manual().register(TypeList.of(Door.class));
      VanillaProps.planks().name("weathered_green_painted_plank_door").family("weathered_green_painted_planks").manual().register(TypeList.of(Door.class));
      VanillaProps.planks().name("green_painted_plank_door").family("green_painted_planks").manual().register(TypeList.of(Door.class));
      VanillaProps.planks()
         .name("weathered_light_green_painted_plank_door")
         .family("weathered_light_green_painted_planks")
         .manual()
         .register(TypeList.of(Door.class));
      VanillaProps.planks().name("light_green_painted_plank_door").family("light_green_painted_planks").manual().register(TypeList.of(Door.class));
      VanillaProps.planks().name("weathered_cyan_painted_plank_door").family("weathered_cyan_painted_planks").manual().register(TypeList.of(Door.class));
      VanillaProps.planks().name("cyan_painted_plank_door").family("cyan_painted_planks").manual().register(TypeList.of(Door.class));
      VanillaProps.planks()
         .name("weathered_dark_blue_painted_plank_door")
         .family("weathered_dark_blue_painted_planks")
         .manual()
         .register(TypeList.of(Door.class));
      VanillaProps.planks().name("dark_blue_painted_plank_door").family("dark_blue_painted_planks").manual().register(TypeList.of(Door.class));
      VanillaProps.planks().name("weathered_blue_painted_plank_door").family("weathered_blue_painted_planks").manual().register(TypeList.of(Door.class));
      VanillaProps.planks()
         .name("weathered_light_blue_painted_plank_door")
         .family("weathered_light_blue_painted_planks")
         .manual()
         .register(TypeList.of(Door.class));
      VanillaProps.planks().name("light_blue_painted_plank_door").family("light_blue_painted_planks").manual().register(TypeList.of(Door.class));
      VanillaProps.planks().name("blue_painted_plank_door").family("blue_painted_planks").manual().register(TypeList.of(Door.class));
      VanillaProps.planks().name("weathered_purple_painted_plank_door").family("weathered_purple_painted_planks").manual().register(TypeList.of(Door.class));
      VanillaProps.planks().name("purple_painted_plank_door").family("purple_painted_planks").manual().register(TypeList.of(Door.class));
      VanillaProps.planks().name("weathered_brown_painted_plank_door").family("weathered_brown_painted_planks").manual().register(TypeList.of(Door.class));
      VanillaProps.planks().name("brown_painted_plank_door").family("brown_painted_planks").manual().register(TypeList.of(Door.class));
      VanillaProps.planks().name("weathered_white_painted_plank_door").family("weathered_white_painted_planks").manual().register(TypeList.of(Door.class));
      VanillaProps.planks().name("white_painted_plank_door").family("white_painted_planks").manual().register(TypeList.of(Door.class));
      VanillaProps.planks().name("white_painted_horizontal_plank_door").family("white_painted_horizontal_planks").manual().register(TypeList.of(Door.class));
      VanillaProps.planks().name("gray_painted_horizontal_plank_door").family("gray_painted_horizontal_planks").manual().register(TypeList.of(Door.class));
      VanillaProps.planks().name("baroque_oak_door").manual().register(TypeList.of(Door.class));
      VanillaProps.planks().name("tall_baroque_oak_door").manual().register(TypeList.of(Door.class));
      VanillaProps.planks().name("tall_baroque_windowed_oak_door").manual().register(TypeList.of(Door.class));
      VanillaProps.metal().name("tall_oxidized_bronze_door").manual().register(TypeList.of(Door.class));
      VanillaProps.planks().name("green_wooden_door").manual().register(TypeList.of(Door.class));
      VanillaProps.planks().name("white_wooden_door").manual().register(TypeList.of(Door.class));
      VanillaProps.planks().name("gray_windowed_wooden_door").manual().register(TypeList.of(Door.class));
      VanillaProps.metal().name("reinforced_prison_door").manual().register(TypeList.of(Door.class));
      VanillaProps.metal().name("prison_door").manual().register(TypeList.of(Door.class));
      VanillaProps.metal().name("spiked_iron_gate").manual().register(TypeList.of(Door.class));
      VanillaProps.planks().name("tall_reinforced_oak_door").manual().register(TypeList.of(Door.class));
      VanillaProps.planks().name("reinforced_oak_door").manual().register(TypeList.of(Door.class));
      VanillaProps.planks().name("tall_oak_door").manual().register(TypeList.of(Door.class));
      VanillaProps.metal().name("office_door").manual().register(TypeList.of(Door.class));
      VanillaProps.metal().name("blue_office_door").manual().register(TypeList.of(Door.class));
      VanillaProps.planks().name("red_studded_door").manual().register(TypeList.of(Door.class));
      VanillaProps.planks().name("black_studded_door").manual().register(TypeList.of(Door.class));
      VanillaProps.planks().name("tall_gray_wooden_door").manual().register(TypeList.of(Door.class));
      VanillaProps.planks().name("tall_old_oak_door").manual().register(TypeList.of(Door.class));
      VanillaProps.planks().name("simple_tall_wooden_door").manual().register(TypeList.of(Door.class));
      VanillaProps.planks().name("simple_wooden_door").manual().register(TypeList.of(Door.class));
      VanillaProps.planks().name("simple_worn_wooden_door").manual().register(TypeList.of(Door.class));
   }
}
