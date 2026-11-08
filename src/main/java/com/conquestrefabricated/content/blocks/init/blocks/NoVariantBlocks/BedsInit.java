package com.conquestrefabricated.content.blocks.init.blocks.NoVariantBlocks;

import com.conquestrefabricated.content.blocks.block.decor.Bed;
import com.conquestrefabricated.content.blocks.group.ModGroups;
import com.conquestrefabricated.core.block.builder.VanillaProps;
import com.conquestrefabricated.core.block.factory.TypeList;

public class BedsInit {
   public BedsInit() {
   }

   public static void init() {
      VanillaProps.planks().group(ModGroups.FURNITURE).name("basic_green_bed").manual().register(TypeList.of(Bed.class));
      VanillaProps.planks().group(ModGroups.FURNITURE).name("bed_with_wolf_pelt").manual().register(TypeList.of(Bed.class));
      VanillaProps.planks().group(ModGroups.FURNITURE).name("carved_palm_wood_bed").manual().register(TypeList.of(Bed.class));
      VanillaProps.planks().group(ModGroups.FURNITURE).name("carved_spruce_wood_bed_with_bear_pelt").manual().register(TypeList.of(Bed.class));
      VanillaProps.planks().group(ModGroups.FURNITURE).name("fancy_blue_bed").manual().register(TypeList.of(Bed.class));
      VanillaProps.planks().group(ModGroups.FURNITURE).name("fancy_white_bed").manual().register(TypeList.of(Bed.class));
      VanillaProps.planks().group(ModGroups.FURNITURE).name("fancy_red_bed").manual().register(TypeList.of(Bed.class));
      VanillaProps.planks().group(ModGroups.FURNITURE).name("old_rustic_bed").manual().register(TypeList.of(Bed.class));
      VanillaProps.planks().group(ModGroups.FURNITURE).name("white_metal_frame_bed").manual().register(TypeList.of(Bed.class));
      VanillaProps.planks().group(ModGroups.FURNITURE).name("hay_covered_bed").manual().register(TypeList.of(Bed.class));
      VanillaProps.planks().group(ModGroups.FURNITURE).name("basic_bed_frame").manual().register(TypeList.of(Bed.class));
      VanillaProps.planks().group(ModGroups.FURNITURE).name("mossy_bed").manual().register(TypeList.of(Bed.class));
      VanillaProps.planks().group(ModGroups.FURNITURE).name("burnt_bed").manual().register(TypeList.of(Bed.class));
      VanillaProps.planks().group(ModGroups.FURNITURE).name("coffin").manual().register(TypeList.of(Bed.class));
      VanillaProps.planks().group(ModGroups.FURNITURE).name("asian_bed").manual().register(TypeList.of(Bed.class));
      VanillaProps.planks().group(ModGroups.FURNITURE).name("tech_bed").manual().register(TypeList.of(Bed.class));
      VanillaProps.planks().group(ModGroups.FURNITURE).name("luxurious_bed").manual().register(TypeList.of(Bed.class));
      VanillaProps.planks().group(ModGroups.FURNITURE).name("hammock").manual().register(TypeList.of(Bed.class));
      VanillaProps.planks().group(ModGroups.FURNITURE).name("nordic_bed").manual().register(TypeList.of(Bed.class));
      VanillaProps.planks().group(ModGroups.FURNITURE).name("fancy_nordic_bed").manual().register(TypeList.of(Bed.class));
      VanillaProps.planks().group(ModGroups.FURNITURE).name("wooden_bunk_bed").manual().register(TypeList.of(Bed.class));
      VanillaProps.planks().group(ModGroups.FURNITURE).name("wooden_bed_frame").manual().register(TypeList.of(Bed.class));
      VanillaProps.planks().group(ModGroups.FURNITURE).name("broken_wooden_bed_frame").manual().register(TypeList.of(Bed.class));
   }
}
