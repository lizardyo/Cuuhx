package com.conquestrefabricated.content.blocks.init.blocks;

import com.conquestrefabricated.content.blocks.BlockVoxelShapes;
import com.conquestrefabricated.content.blocks.block.BeamHorizontal;
import com.conquestrefabricated.content.blocks.block.BeamVertical;
import com.conquestrefabricated.content.blocks.block.decor.DiagonalBeam;
import com.conquestrefabricated.content.blocks.block.decor.DiagonalBeamCentered;
import com.conquestrefabricated.content.blocks.block.decor.DiagonalBeamUpDown;
import com.conquestrefabricated.content.blocks.block.decor.DiagonalBeamUpDownCentered;
import com.conquestrefabricated.content.blocks.block.decor.ModelBlock;
import com.conquestrefabricated.content.blocks.block.directional.HorizontalDirectional;
import com.conquestrefabricated.core.block.builder.VanillaProps;
import com.conquestrefabricated.core.block.factory.TypeList;
import com.conquestrefabricated.core.util.RenderLayer;
import net.minecraft.world.level.block.Blocks;

public class BeamsInit {
   public BeamsInit() {
   }

   public static void init(TypeList types, TypeList typesVanilla) {
      VanillaProps.logs()
         .name("oak_wood_beam")
         .texture("end", "minecraft:block/stripped_oak_log_top")
         .texture("sidewall", "block/1_basic_refined/3_wood/oak/oak_wood_beam_wall")
         .texture("topwall", "block/1_basic_refined/3_wood/oak/oak_wood_beam_wall_top")
         .texture("*", "minecraft:block/stripped_oak_log")
         .parent(Blocks.STRIPPED_OAK_LOG.defaultBlockState())
         .register(typesVanilla);
      VanillaProps.logs()
         .name("spruce_wood_beam")
         .texture("end", "minecraft:block/stripped_spruce_log_top")
         .texture("sidewall", "block/1_basic_refined/3_wood/spruce/spruce_wood_beam_wall")
         .texture("topwall", "block/1_basic_refined/3_wood/spruce/spruce_wood_beam_wall_top")
         .texture("*", "minecraft:block/stripped_spruce_log")
         .parent(Blocks.STRIPPED_SPRUCE_LOG.defaultBlockState())
         .register(typesVanilla);
      VanillaProps.logs()
         .name("birch_wood_beam")
         .texture("end", "minecraft:block/stripped_birch_log_top")
         .texture("sidewall", "block/1_basic_refined/3_wood/birch/birch_wood_beam_wall")
         .texture("topwall", "block/1_basic_refined/3_wood/birch/birch_wood_beam_wall_top")
         .texture("*", "minecraft:block/stripped_birch_log")
         .parent(Blocks.STRIPPED_BIRCH_LOG.defaultBlockState())
         .register(typesVanilla);
      VanillaProps.logs()
         .name("palm_wood_beam")
         .texture("end", "minecraft:block/stripped_jungle_log_top")
         .texture("sidewall", "block/1_basic_refined/3_wood/palm_wood_beam_wall")
         .texture("topwall", "block/1_basic_refined/3_wood/palm_wood_beam_wall_top")
         .texture("*", "minecraft:block/stripped_jungle_log")
         .parent(Blocks.STRIPPED_JUNGLE_LOG.defaultBlockState())
         .register(typesVanilla);
      VanillaProps.logs()
         .name("dark_oak_wood_beam")
         .texture("end", "minecraft:block/stripped_dark_oak_log_top")
         .texture("sidewall", "block/1_basic_refined/3_wood/dark_oak_wood_beam_wall")
         .texture("topwall", "block/1_basic_refined/3_wood/dark_oak_wood_beam_wall_top")
         .texture("*", "minecraft:block/stripped_dark_oak_log")
         .parent(Blocks.STRIPPED_DARK_OAK_LOG.defaultBlockState())
         .register(typesVanilla);
      VanillaProps.logs()
         .name("goat_sallow_wood_beam")
         .texture("end", "block/1_basic_refined/3_wood/goat_sallow_wood_beam_top")
         .texture("*", "block/1_basic_refined/3_wood/goat_sallow_wood_beam")
         .register(types);
      VanillaProps.logs()
         .name("acacia_wood_beam")
         .texture("end", "minecraft:block/stripped_acacia_log_top")
         .texture("sidewall", "block/1_basic_refined/3_wood/acacia_wood_beam_wall")
         .texture("topwall", "block/1_basic_refined/3_wood/acacia_wood_beam_wall_top")
         .texture("*", "minecraft:block/stripped_acacia_log")
         .parent(Blocks.STRIPPED_ACACIA_LOG.defaultBlockState())
         .register(typesVanilla);
      VanillaProps.logs()
         .name("apple_wood_beam")
         .texture("end", "block/1_basic_refined/3_wood/apple_wood_beam_top")
         .texture("sidewall", "block/1_basic_refined/3_wood/apple_wood_beam_wall")
         .texture("topwall", "block/1_basic_refined/3_wood/apple_wood_beam_wall_top")
         .texture("*", "block/1_basic_refined/3_wood/apple_wood_beam")
         .register(types);
      VanillaProps.logs()
         .name("ash_wood_beam")
         .texture("end", "block/1_basic_refined/3_wood/ash_wood_beam_top")
         .texture("sidewall", "block/1_basic_refined/3_wood/ash_wood_beam_wall")
         .texture("topwall", "block/1_basic_refined/3_wood/ash_wood_beam_wall_top")
         .texture("*", "block/1_basic_refined/3_wood/ash_wood_beam")
         .register(types);
      VanillaProps.logs()
         .name("aspen_wood_beam")
         .texture("end", "block/1_basic_refined/3_wood/aspen_wood_beam_top")
         .texture("sidewall", "block/1_basic_refined/3_wood/aspen_wood_beam_wall")
         .texture("topwall", "block/1_basic_refined/3_wood/aspen_wood_beam_wall_top")
         .texture("*", "block/1_basic_refined/3_wood/aspen_wood_beam")
         .register(types);
      VanillaProps.logs()
         .name("beech_wood_beam")
         .texture("end", "block/1_basic_refined/3_wood/beech_wood_beam_top")
         .texture("sidewall", "block/1_basic_refined/3_wood/beech_wood_beam_wall")
         .texture("topwall", "block/1_basic_refined/3_wood/beech_wood_beam_wall_top")
         .texture("*", "block/1_basic_refined/3_wood/beech_wood_beam")
         .register(types);
      VanillaProps.logs()
         .name("horse_chestnut_wood_beam")
         .texture("end", "block/1_basic_refined/3_wood/horse_chestnut_wood_beam_top")
         .texture("sidewall", "block/1_basic_refined/3_wood/horse_chestnut_wood_beam_wall")
         .texture("topwall", "block/1_basic_refined/3_wood/horse_chestnut_wood_beam_wall_top")
         .texture("*", "block/1_basic_refined/3_wood/horse_chestnut_wood_beam")
         .register(types);
      VanillaProps.logs()
         .name("larch_wood_beam")
         .texture("end", "block/1_basic_refined/3_wood/larch_wood_beam_top")
         .texture("sidewall", "block/1_basic_refined/3_wood/larch_wood_beam_wall")
         .texture("topwall", "block/1_basic_refined/3_wood/larch_wood_beam_wall_top")
         .texture("*", "block/1_basic_refined/3_wood/larch_wood_beam")
         .register(types);
      VanillaProps.logs()
         .name("mallorn_wood_beam")
         .texture("end", "block/1_basic_refined/3_wood/mallorn_wood_beam_top")
         .texture("sidewall", "block/1_basic_refined/3_wood/mallorn_wood_beam_wall")
         .texture("topwall", "block/1_basic_refined/3_wood/mallorn_wood_beam_wall_top")
         .texture("*", "block/1_basic_refined/3_wood/mallorn_wood_beam")
         .register(types);
      VanillaProps.logs()
         .name("norway_spruce_wood_beam")
         .texture("end", "block/1_basic_refined/3_wood/norway_spruce_wood_beam_top")
         .texture("sidewall", "block/1_basic_refined/3_wood/norway_spruce_wood_beam_wall")
         .texture("topwall", "block/1_basic_refined/3_wood/norway_spruce_wood_beam_wall_top")
         .texture("*", "block/1_basic_refined/3_wood/norway_spruce_wood_beam")
         .register(types);
      VanillaProps.logs()
         .name("pine_wood_beam")
         .texture("end", "block/1_basic_refined/3_wood/pine_wood_beam_top")
         .texture("sidewall", "block/1_basic_refined/3_wood/pine_wood_beam_wall")
         .texture("topwall", "block/1_basic_refined/3_wood/pine_wood_beam_wall_top")
         .texture("*", "block/1_basic_refined/3_wood/pine_wood_beam")
         .register(types);
      VanillaProps.logs()
         .name("rowan_wood_beam")
         .texture("end", "block/1_basic_refined/3_wood/rowan_wood_beam_top")
         .texture("sidewall", "block/1_basic_refined/3_wood/rowan_wood_beam_wall")
         .texture("topwall", "block/1_basic_refined/3_wood/rowan_wood_beam_wall_top")
         .texture("*", "block/1_basic_refined/3_wood/rowan_wood_beam")
         .register(types);
      VanillaProps.logs()
         .name("willow_wood_beam")
         .texture("end", "block/1_basic_refined/3_wood/willow_wood_beam_top")
         .texture("sidewall", "block/1_basic_refined/3_wood/willow_wood_beam_wall")
         .texture("topwall", "block/1_basic_refined/3_wood/willow_wood_beam_wall_top")
         .texture("*", "block/1_basic_refined/3_wood/willow_wood_beam")
         .register(types);
      VanillaProps.logs()
         .name("olive_wood_beam")
         .texture("end", "block/1_basic_refined/3_wood/olive_wood_beam_top")
         .texture("*", "block/1_basic_refined/3_wood/olive_wood_beam")
         .register(types);
      VanillaProps.logs()
         .name("alder_wood_beam")
         .texture("end", "block/1_basic_refined/3_wood/alder_wood_beam_top")
         .texture("*", "block/1_basic_refined/3_wood/alder_wood_beam")
         .register(types);
      VanillaProps.logs().name("wooden_beam").manual().register(TypeList.of(BeamVertical.class, BeamHorizontal.class));
      VanillaProps.logs().family("wooden_beam_vertical").name("cross_beams").manual().solid(false).register(TypeList.of(DiagonalBeamCentered.class));
      VanillaProps.logs().family("wooden_beam_vertical").name("diagonal_beam_1").manual().solid(false).register(TypeList.of(DiagonalBeamCentered.class));
      VanillaProps.logs().family("wooden_beam_vertical").name("diagonal_beam_2").manual().solid(false).register(TypeList.of(DiagonalBeamUpDownCentered.class));
      VanillaProps.logs().family("wooden_beam_vertical").name("diagonal_beam_3").manual().solid(false).register(TypeList.of(DiagonalBeamUpDownCentered.class));
      VanillaProps.logs().family("wooden_beam_vertical").name("cross_beams_side").manual().solid(false).register(TypeList.of(DiagonalBeam.class));
      VanillaProps.logs().family("wooden_beam_vertical").name("diagonal_beam_side_1").manual().solid(false).register(TypeList.of(DiagonalBeam.class));
      VanillaProps.logs().family("wooden_beam_vertical").name("diagonal_beam_side_2").manual().solid(false).register(TypeList.of(DiagonalBeamUpDown.class));
      VanillaProps.logs().family("wooden_beam_vertical").name("diagonal_beam_side_3").manual().solid(false).register(TypeList.of(DiagonalBeamUpDown.class));
      VanillaProps.logs()
         .family("wooden_beam_vertical")
         .name("wooden_beam_vertical_centered")
         .manual()
         .solid(false)
         .with("hitBox", BlockVoxelShapes.pillarShape)
         .register(TypeList.of(ModelBlock.class));
      VanillaProps.logs()
         .family("wooden_beam_vertical")
         .name("wooden_beam_horizontal_centered")
         .manual()
         .solid(false)
         .with("hitBox", BlockVoxelShapes.pillarHorizontalShape)
         .register(TypeList.of(HorizontalDirectional.Waterlogged.class));
      VanillaProps.planks()
         .name("wooden_fancy_corbel")
         .manual()
         .solid(false)
         .blocking(false)
         .render(RenderLayer.CUTOUT)
         .with("hitBox", BlockVoxelShapes.verticalSlabShapes)
         .register(TypeList.of(HorizontalDirectional.Toggle4.class));
      VanillaProps.planks()
         .name("white_painted_corbel")
         .manual()
         .solid(false)
         .blocking(false)
         .render(RenderLayer.CUTOUT)
         .with("hitBox", BlockVoxelShapes.verticalSlabShapes)
         .register(TypeList.of(HorizontalDirectional.Toggle5.class));
      VanillaProps.logs()
         .name("tarred_birch_wood_beam")
         .texture("end", "block/1_basic_refined/3_wood/tarred_birch_wood_beam_top")
         .texture("*", "block/1_basic_refined/3_wood/tarred_birch_wood_beam")
         .register(types);
      VanillaProps.logs()
         .name("tarred_dark_oak_wood_beam")
         .texture("end", "block/1_basic_refined/3_wood/tarred_dark_oak_wood_beam_top")
         .texture("*", "block/1_basic_refined/3_wood/tarred_dark_oak_wood_beam")
         .register(types);
      VanillaProps.logs()
         .name("tarred_oak_wood_beam")
         .texture("end", "block/1_basic_refined/3_wood/tarred_oak_wood_beam_top")
         .texture("*", "block/1_basic_refined/3_wood/tarred_oak_wood_beam")
         .register(types);
      VanillaProps.logs()
         .name("tarred_spruce_wood_beam")
         .texture("end", "block/1_basic_refined/3_wood/tarred_spruce_wood_beam_top")
         .texture("*", "block/1_basic_refined/3_wood/tarred_spruce_wood_beam")
         .register(types);
      VanillaProps.logs()
         .name("green_bamboo_wood_beam")
         .texture("end", "minecraft:block/bamboo_block_top")
         .texture("*", "minecraft:block/bamboo_block")
         .parent(Blocks.BAMBOO_BLOCK.defaultBlockState())
         .register(typesVanilla);
      VanillaProps.logs()
         .name("bamboo_wood_beam")
         .texture("end", "minecraft:block/stripped_bamboo_block_top")
         .texture("*", "minecraft:block/stripped_bamboo_block")
         .parent(Blocks.STRIPPED_BAMBOO_BLOCK.defaultBlockState())
         .register(typesVanilla);
      VanillaProps.logs()
         .name("mangrove_wood_beam")
         .texture("end", "minecraft:block/stripped_mangrove_log_top")
         .texture("*", "minecraft:block/stripped_mangrove_log")
         .parent(Blocks.STRIPPED_MANGROVE_LOG.defaultBlockState())
         .register(typesVanilla);
      VanillaProps.logs()
         .name("cherry_wood_beam")
         .texture("end", "minecraft:block/stripped_cherry_log_top")
         .texture("*", "minecraft:block/stripped_cherry_log")
         .parent(Blocks.STRIPPED_CHERRY_LOG.defaultBlockState())
         .register(typesVanilla);
      VanillaProps.metal().name("steel_beam").manual().solid(false).register(TypeList.of(BeamVertical.class));
      VanillaProps.metal().family("steel_beam_vertical").name("steel_beam").manual().solid(false).register(TypeList.of(BeamHorizontal.class));
      VanillaProps.metal().family("steel_beam_vertical").name("steel_cross_beams").manual().solid(false).register(TypeList.of(DiagonalBeamCentered.class));
      VanillaProps.metal().family("steel_beam_vertical").name("steel_diagonal_beam_1").manual().solid(false).register(TypeList.of(DiagonalBeamCentered.class));
      VanillaProps.metal()
         .family("steel_beam_vertical")
         .name("steel_diagonal_beam_2")
         .manual()
         .solid(false)
         .register(TypeList.of(DiagonalBeamUpDownCentered.class));
      VanillaProps.metal()
         .family("steel_beam_vertical")
         .name("steel_diagonal_beam_3")
         .manual()
         .solid(false)
         .register(TypeList.of(DiagonalBeamUpDownCentered.class));
      VanillaProps.metal().family("steel_beam_vertical").name("steel_cross_beams_side").manual().solid(false).register(TypeList.of(DiagonalBeam.class));
      VanillaProps.metal().family("steel_beam_vertical").name("steel_diagonal_beam_side_1").manual().solid(false).register(TypeList.of(DiagonalBeam.class));
      VanillaProps.metal()
         .family("steel_beam_vertical")
         .name("steel_diagonal_beam_side_2")
         .manual()
         .solid(false)
         .register(TypeList.of(DiagonalBeamUpDown.class));
      VanillaProps.metal()
         .family("steel_beam_vertical")
         .name("steel_diagonal_beam_side_3")
         .manual()
         .solid(false)
         .register(TypeList.of(DiagonalBeamUpDown.class));
      VanillaProps.metal().name("red_painted_steel_beam").manual().solid(false).register(TypeList.of(BeamVertical.class));
      VanillaProps.metal()
         .family("red_painted_steel_beam_vertical")
         .name("red_painted_steel_beam")
         .manual()
         .solid(false)
         .register(TypeList.of(BeamHorizontal.class));
      VanillaProps.metal()
         .family("red_painted_steel_beam_vertical")
         .name("red_painted_steel_cross_beams")
         .manual()
         .solid(false)
         .register(TypeList.of(DiagonalBeamCentered.class));
      VanillaProps.metal()
         .family("red_painted_steel_beam_vertical")
         .name("red_painted_steel_diagonal_beam_1")
         .manual()
         .solid(false)
         .register(TypeList.of(DiagonalBeamCentered.class));
      VanillaProps.metal()
         .family("red_painted_steel_beam_vertical")
         .name("red_painted_steel_diagonal_beam_2")
         .manual()
         .solid(false)
         .register(TypeList.of(DiagonalBeamUpDownCentered.class));
      VanillaProps.metal()
         .family("red_painted_steel_beam_vertical")
         .name("red_painted_steel_diagonal_beam_3")
         .manual()
         .solid(false)
         .register(TypeList.of(DiagonalBeamUpDownCentered.class));
      VanillaProps.metal()
         .family("red_painted_steel_beam_vertical")
         .name("red_painted_steel_cross_beams_side")
         .manual()
         .solid(false)
         .register(TypeList.of(DiagonalBeam.class));
      VanillaProps.metal()
         .family("red_painted_steel_beam_vertical")
         .name("red_painted_steel_diagonal_beam_side_1")
         .manual()
         .solid(false)
         .register(TypeList.of(DiagonalBeam.class));
      VanillaProps.metal()
         .family("red_painted_steel_beam_vertical")
         .name("red_painted_steel_diagonal_beam_side_2")
         .manual()
         .solid(false)
         .register(TypeList.of(DiagonalBeamUpDown.class));
      VanillaProps.metal()
         .family("red_painted_steel_beam_vertical")
         .name("red_painted_steel_diagonal_beam_side_3")
         .manual()
         .solid(false)
         .register(TypeList.of(DiagonalBeamUpDown.class));
      VanillaProps.metal().name("worn_red_painted_steel_beam").manual().solid(false).register(TypeList.of(BeamVertical.class));
      VanillaProps.metal()
         .family("worn_red_painted_steel_beam_vertical")
         .name("worn_red_painted_steel_beam")
         .manual()
         .solid(false)
         .register(TypeList.of(BeamHorizontal.class));
      VanillaProps.metal()
         .family("worn_red_painted_steel_beam_vertical")
         .name("worn_red_painted_steel_cross_beams")
         .manual()
         .solid(false)
         .register(TypeList.of(DiagonalBeamCentered.class));
      VanillaProps.metal()
         .family("worn_red_painted_steel_beam_vertical")
         .name("worn_red_painted_steel_diagonal_beam_1")
         .manual()
         .solid(false)
         .register(TypeList.of(DiagonalBeamCentered.class));
      VanillaProps.metal()
         .family("worn_red_painted_steel_beam_vertical")
         .name("worn_red_painted_steel_diagonal_beam_2")
         .manual()
         .solid(false)
         .register(TypeList.of(DiagonalBeamUpDownCentered.class));
      VanillaProps.metal()
         .family("worn_red_painted_steel_beam_vertical")
         .name("worn_red_painted_steel_diagonal_beam_3")
         .manual()
         .solid(false)
         .register(TypeList.of(DiagonalBeamUpDownCentered.class));
      VanillaProps.metal()
         .family("worn_red_painted_steel_beam_vertical")
         .name("worn_red_painted_steel_cross_beams_side")
         .manual()
         .solid(false)
         .register(TypeList.of(DiagonalBeam.class));
      VanillaProps.metal()
         .family("worn_red_painted_steel_beam_vertical")
         .name("worn_red_painted_steel_diagonal_beam_side_1")
         .manual()
         .solid(false)
         .register(TypeList.of(DiagonalBeam.class));
      VanillaProps.metal()
         .family("worn_red_painted_steel_beam_vertical")
         .name("worn_red_painted_steel_diagonal_beam_side_2")
         .manual()
         .solid(false)
         .register(TypeList.of(DiagonalBeamUpDown.class));
      VanillaProps.metal()
         .family("worn_red_painted_steel_beam_vertical")
         .name("worn_red_painted_steel_diagonal_beam_side_3")
         .manual()
         .solid(false)
         .register(TypeList.of(DiagonalBeamUpDown.class));
   }
}
