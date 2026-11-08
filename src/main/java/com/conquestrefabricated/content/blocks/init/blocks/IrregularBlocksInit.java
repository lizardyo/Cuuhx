package com.conquestrefabricated.content.blocks.init.blocks;

import com.conquestrefabricated.content.blocks.BlockVoxelShapes;
import com.conquestrefabricated.content.blocks.block.Balustrade;
import com.conquestrefabricated.content.blocks.block.Capital;
import com.conquestrefabricated.content.blocks.block.Cover;
import com.conquestrefabricated.content.blocks.block.Cube;
import com.conquestrefabricated.content.blocks.block.FenceGate;
import com.conquestrefabricated.content.blocks.block.FenceVanilla;
import com.conquestrefabricated.content.blocks.block.Layer;
import com.conquestrefabricated.content.blocks.block.Pillar;
import com.conquestrefabricated.content.blocks.block.Slab;
import com.conquestrefabricated.content.blocks.block.SlabBasic;
import com.conquestrefabricated.content.blocks.block.SlabCorner;
import com.conquestrefabricated.content.blocks.block.SlabEighth;
import com.conquestrefabricated.content.blocks.block.SlabLessLayers;
import com.conquestrefabricated.content.blocks.block.SlabQuarter;
import com.conquestrefabricated.content.blocks.block.SlabQuarterNoLayers;
import com.conquestrefabricated.content.blocks.block.Sphere;
import com.conquestrefabricated.content.blocks.block.Stairs;
import com.conquestrefabricated.content.blocks.block.VerticalCorner;
import com.conquestrefabricated.content.blocks.block.VerticalCornerExtendable;
import com.conquestrefabricated.content.blocks.block.VerticalQuarter;
import com.conquestrefabricated.content.blocks.block.VerticalQuarterExtendable;
import com.conquestrefabricated.content.blocks.block.VerticalQuarterHinged;
import com.conquestrefabricated.content.blocks.block.VerticalSlab;
import com.conquestrefabricated.content.blocks.block.VerticalSlabCorner;
import com.conquestrefabricated.content.blocks.block.VerticalSlabExtendable;
import com.conquestrefabricated.content.blocks.block.Wall;
import com.conquestrefabricated.content.blocks.block.WallNew;
import com.conquestrefabricated.content.blocks.block.classical.CapitalPlinth;
import com.conquestrefabricated.content.blocks.block.classical.CubeCapital;
import com.conquestrefabricated.content.blocks.block.classical.PillarCapital;
import com.conquestrefabricated.content.blocks.block.classical.SlabPlinth;
import com.conquestrefabricated.content.blocks.block.classical.SmallCorniceModel;
import com.conquestrefabricated.content.blocks.block.classical.SmallCorniceModelCorners;
import com.conquestrefabricated.content.blocks.block.classical.VerticalCornerCapital;
import com.conquestrefabricated.content.blocks.block.classical.VerticalQuarterCapital;
import com.conquestrefabricated.content.blocks.block.classical.VerticalSlabCapital;
import com.conquestrefabricated.content.blocks.block.directional.Half;
import com.conquestrefabricated.content.blocks.block.directional.HorizontalDirectional;
import com.conquestrefabricated.content.blocks.block.trees.Log;
import com.conquestrefabricated.content.blocks.block.windows.ArrowSlit;
import com.conquestrefabricated.content.blocks.block.windows.WindowSmall;
import com.conquestrefabricated.content.blocks.block.windows.WindowSmallHalf;
import com.conquestrefabricated.content.blocks.group.ModGroups;
import com.conquestrefabricated.core.block.builder.VanillaProps;
import com.conquestrefabricated.core.block.factory.TypeList;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;

public class IrregularBlocksInit {
   public IrregularBlocksInit() {
   }

   public static void init() {
      VanillaProps.stone()
         .name("simple_rune_carvings_1")
         .texture("*", "block/2_advanced_refined/1_stone/simple_rune_carvings")
         .register(TypeList.of(Cube.class));
      VanillaProps.stone()
         .name("simple_rune_carvings_2")
         .family("simple_rune_carvings_1")
         .texture("*", "block/2_advanced_refined/1_stone/simple_rune_carvings")
         .register(TypeList.of(Cube.class));
      VanillaProps.stone()
         .name("roman_orange_clay_brick_with_sandstone_base")
         .texture("top", "block/1_basic_refined/1_stone/1_clay/roman_orange_clay_brick")
         .texture("bottom", "minecraft:block/sandstone_bottom")
         .texture("*", "block/1_basic_refined/1_stone/1_clay/roman_orange_clay_brick_with_sandstone_base")
         .register(
            TypeList.of(
               Cube.class,
               ArrowSlit.class,
               WindowSmall.class,
               WindowSmallHalf.class,
               VerticalSlabExtendable.class,
               VerticalCornerExtendable.class,
               VerticalQuarterExtendable.class,
               Stairs.class,
               WallNew.class,
               Pillar.class
            )
         );
      VanillaProps.stone()
         .name("portcullis_groove")
         .texture("top", "block/2_advanced_refined/1_stone/portcullis_groove_top")
         .texture("bottom", "block/2_advanced_refined/1_stone/portcullis_groove_top")
         .texture("*", "block/2_advanced_refined/1_stone/portcullis_groove")
         .register(
            TypeList.of(
               Cube.class,
               ArrowSlit.class,
               WindowSmall.class,
               WindowSmallHalf.class,
               Balustrade.class,
               Capital.class,
               SlabBasic.class,
               SlabQuarterNoLayers.class,
               SlabCorner.class,
               SlabEighth.class,
               VerticalSlabCorner.class,
               VerticalSlab.class,
               VerticalCorner.class,
               VerticalQuarter.class,
               Stairs.class,
               WallNew.class,
               Pillar.class
            )
         );
      VanillaProps.stone()
         .name("fancy_blue_schist_dwarven_design")
         .texture("top", "block/8_topography/1_stone/blue_schist_topbottom")
         .texture("bottom", "block/8_topography/1_stone/blue_schist_topbottom")
         .texture("*", "block/2_advanced_refined/1_stone/6_schist/fancy_blue_schist_dwarven_design")
         .register(
            TypeList.of(
               Cube.class,
               ArrowSlit.class,
               WindowSmall.class,
               WindowSmallHalf.class,
               SlabBasic.class,
               SlabQuarterNoLayers.class,
               SlabCorner.class,
               SlabEighth.class,
               VerticalSlabCorner.class,
               VerticalSlab.class,
               VerticalCorner.class,
               VerticalQuarter.class,
               Stairs.class,
               WallNew.class,
               Pillar.class
            )
         );
      VanillaProps.stone()
         .name("fancy_granite_dwarven_design")
         .texture("top", "minecraft:block/granite")
         .texture("bottom", "minecraft:block/granite")
         .texture("*", "block/2_advanced_refined/1_stone/1_granite/fancy_granite_dwarven_design")
         .register(
            TypeList.of(
               Cube.class,
               ArrowSlit.class,
               WindowSmall.class,
               WindowSmallHalf.class,
               SlabBasic.class,
               SlabQuarterNoLayers.class,
               SlabCorner.class,
               SlabEighth.class,
               VerticalSlabCorner.class,
               VerticalSlab.class,
               VerticalCorner.class,
               VerticalQuarter.class,
               Stairs.class,
               WallNew.class,
               Pillar.class
            )
         );
      VanillaProps.stone()
         .name("fancy_dark_rough_marble_dwarven_design")
         .texture("top", "block/8_topography/1_stone/dark_rough_marble")
         .texture("bottom", "block/8_topography/1_stone/dark_rough_marble")
         .texture("*", "block/2_advanced_refined/1_stone/3_marble/fancy_dark_rough_marble_dwarven_design")
         .register(
            TypeList.of(
               Cube.class,
               ArrowSlit.class,
               WindowSmall.class,
               WindowSmallHalf.class,
               SlabBasic.class,
               SlabQuarterNoLayers.class,
               SlabCorner.class,
               SlabEighth.class,
               VerticalSlabCorner.class,
               VerticalSlab.class,
               VerticalCorner.class,
               VerticalQuarter.class,
               Stairs.class,
               WallNew.class,
               Pillar.class
            )
         );
      VanillaProps.planks()
         .name("horizontally_carved_oak_wood")
         .texture("top", "block/1_basic_refined/3_wood/oak/wall_of_oak_logs_topbottom")
         .texture("bottom", "block/1_basic_refined/3_wood/oak/wall_of_oak_logs_topbottom")
         .texture("*", "block/2_advanced_refined/7_wood/horizontally_carved_oak_wood")
         .register(
            TypeList.of(
               Cube.class,
               ArrowSlit.class,
               WindowSmall.class,
               WindowSmallHalf.class,
               SlabBasic.class,
               SlabQuarterNoLayers.class,
               SlabCorner.class,
               SlabEighth.class,
               VerticalSlabCorner.class,
               VerticalSlab.class,
               VerticalCorner.class,
               VerticalQuarter.class,
               Stairs.class,
               WallNew.class,
               Pillar.class
            )
         );
      VanillaProps.stone()
         .name("irregular_andesite_stone_wall_stone_brick_quoins")
         .texture("top", "block/1_basic_refined/1_stone/irregular_andesite_stone_wall")
         .texture("bottom", "block/1_basic_refined/1_stone/irregular_andesite_stone_wall")
         .texture("*", "block/1_basic_refined/1_stone/irregular_andesite_stone_wall_stone_brick_quoins")
         .register(TypeList.of(Cube.class, VerticalSlab.class));
      VanillaProps.stone()
         .name("limestone_cobble_with_quoins")
         .texture("top", "minecraft:block/stone_bricks")
         .texture("bottom", "minecraft:block/stone_bricks")
         .texture("*", "block/1_basic_refined/1_stone/3_limestone/limestone_cobble_with_quoins")
         .register(TypeList.of(Cube.class, VerticalSlab.class));
      VanillaProps.stone()
         .name("mossy_limestone_cobble_with_quoins")
         .texture("block/1_basic_refined/1_stone/3_limestone/mossy_limestone_cobble_with_quoins")
         .register(TypeList.of(Cube.class, VerticalSlab.class));
      VanillaProps.stone()
         .name("limestone_cobble_clay_brick_quoins")
         .texture("top", "minecraft:block/cobblestone")
         .texture("bottom", "minecraft:block/cobblestone")
         .texture("*", "block/1_basic_refined/1_stone/3_limestone/limestone_cobble_clay_brick_quoins")
         .register(TypeList.of(Cube.class, VerticalSlab.class));
      VanillaProps.stone()
         .name("blue_schist_dwarven_design")
         .texture("top", "block/2_advanced_refined/1_stone/6_schist/blue_schist_dwarven_design_topbottom")
         .texture("bottom", "block/2_advanced_refined/1_stone/6_schist/blue_schist_dwarven_design_topbottom")
         .texture("*", "block/2_advanced_refined/1_stone/6_schist/blue_schist_dwarven_design")
         .register(TypeList.of(Cube.class, VerticalSlab.class, VerticalCorner.class, VerticalQuarter.class, Stairs.class, WallNew.class, Pillar.class));
      VanillaProps.stone()
         .name("granite_dwarven_design")
         .texture("top", "minecraft:block/granite")
         .texture("bottom", "minecraft:block/granite")
         .texture("*", "block/2_advanced_refined/1_stone/1_granite/granite_dwarven_design")
         .register(TypeList.of(Cube.class, VerticalSlab.class, VerticalCorner.class, VerticalQuarter.class, Stairs.class, WallNew.class, Pillar.class));
      VanillaProps.stone()
         .name("dark_rough_marble_dwarven_design")
         .texture("top", "block/8_topography/1_stone/dark_rough_marble")
         .texture("bottom", "block/8_topography/1_stone/dark_rough_marble")
         .texture("*", "block/2_advanced_refined/1_stone/3_marble/dark_rough_marble_dwarven_design")
         .register(TypeList.of(Cube.class, VerticalSlab.class, VerticalCorner.class, VerticalQuarter.class, Stairs.class, WallNew.class, Pillar.class));
      VanillaProps.stone()
         .name("lapis_lazuli_sandstone_border")
         .texture("block/2_advanced_refined/1_stone/5_sandstone/lapis_lazuli_sandstone_border")
         .register(TypeList.of(Cube.class));
      VanillaProps.metal()
         .name("iron_block")
         .texture("minecraft:block/iron_block")
         .parent(Blocks.IRON_BLOCK.defaultBlockState())
         .register(
            TypeList.of(
               ArrowSlit.class,
               WindowSmall.class,
               WindowSmallHalf.class,
               Balustrade.class,
               Capital.class,
               Sphere.class,
               SlabLessLayers.class,
               SlabQuarter.class,
               SlabCorner.class,
               SlabEighth.class,
               VerticalSlabCorner.class,
               VerticalSlab.class,
               VerticalCorner.class,
               VerticalQuarter.class,
               Stairs.class,
               WallNew.class,
               Pillar.class
            )
         );
      VanillaProps.metal()
         .name("iron_block_2")
         .texture("minecraft:block/iron_block")
         .register(
            TypeList.of(
               Cube.class,
               SlabLessLayers.class,
               SlabQuarter.class,
               SlabCorner.class,
               SlabEighth.class,
               VerticalSlabCorner.class,
               VerticalSlab.class,
               VerticalCorner.class,
               VerticalQuarter.class,
               Stairs.class
            )
         );
      VanillaProps.metal()
         .name("rusty_iron_block")
         .texture("block/2_advanced_refined/2_metal/rusty_iron_block")
         .register(
            TypeList.of(
               Cube.class,
               ArrowSlit.class,
               WindowSmall.class,
               WindowSmallHalf.class,
               Balustrade.class,
               Capital.class,
               Sphere.class,
               SlabLessLayers.class,
               SlabQuarter.class,
               SlabCorner.class,
               SlabEighth.class,
               VerticalSlabCorner.class,
               VerticalSlab.class,
               VerticalCorner.class,
               VerticalQuarter.class,
               Stairs.class,
               WallNew.class,
               Pillar.class
            )
         );
      VanillaProps.metal()
         .name("old_iron_block")
         .texture("block/2_advanced_refined/2_metal/old_iron_block")
         .woodType(new WoodType("IRON", BlockSetType.IRON, SoundType.METAL, SoundType.HANGING_SIGN, SoundEvents.FENCE_GATE_CLOSE, SoundEvents.FENCE_GATE_OPEN))
         .register(
            TypeList.of(
               Cube.class,
               ArrowSlit.class,
               WindowSmall.class,
               WindowSmallHalf.class,
               Balustrade.class,
               Capital.class,
               Sphere.class,
               SlabLessLayers.class,
               SlabQuarter.class,
               SlabCorner.class,
               SlabEighth.class,
               VerticalSlabCorner.class,
               VerticalSlab.class,
               VerticalCorner.class,
               VerticalQuarter.class,
               Stairs.class,
               WallNew.class,
               Pillar.class,
               FenceVanilla.class,
               FenceGate.class
            )
         );
      VanillaProps.stone()
         .name("sandstone_frieze")
         .texture("top", "block/2_advanced_refined/1_stone/5_sandstone/complex_sandstone_design")
         .texture("bottom", "block/2_advanced_refined/1_stone/5_sandstone/complex_sandstone_design")
         .texture("*", "block/2_advanced_refined/1_stone/5_sandstone/sandstone_frieze")
         .register(
            TypeList.of(
               Cube.class,
               ArrowSlit.class,
               WindowSmall.class,
               WindowSmallHalf.class,
               Balustrade.class,
               Capital.class,
               VerticalSlab.class,
               VerticalCorner.class,
               VerticalQuarter.class,
               Wall.class,
               Pillar.class
            )
         );
      VanillaProps.stone()
         .name("sandstone_frieze")
         .family("sandstone_frieze")
         .texture("top", "block/2_advanced_refined/1_stone/5_sandstone/complex_sandstone_design")
         .texture("bottom", "block/2_advanced_refined/1_stone/5_sandstone/complex_sandstone_design")
         .texture("*", "block/2_advanced_refined/1_stone/5_sandstone/sandstone_frieze_slab")
         .parent(Blocks.COBBLESTONE.defaultBlockState())
         .register(TypeList.of(SlabBasic.class, Stairs.class));
      VanillaProps.stone()
         .name("sandstone_cornice")
         .manual()
         .register(
            TypeList.of(
               CubeCapital.class,
               Balustrade.class,
               CapitalPlinth.class,
               VerticalSlabCapital.class,
               VerticalCornerCapital.class,
               VerticalQuarterCapital.class,
               Pillar.class,
               Wall.class
            )
         );
      VanillaProps.stone()
         .name("sandstone_architrave")
         .manual()
         .register(
            TypeList.of(CubeCapital.class, VerticalSlabCapital.class, VerticalCornerCapital.class, VerticalQuarterCapital.class, Pillar.class, Wall.class)
         );
      VanillaProps.stone()
         .name("sandstone_plinth")
         .manual()
         .register(
            TypeList.of(
               CubeCapital.class,
               SlabPlinth.class,
               Balustrade.class,
               CapitalPlinth.class,
               VerticalSlabCapital.class,
               VerticalCornerCapital.class,
               VerticalQuarterCapital.class,
               PillarCapital.class,
               Stairs.class,
               Wall.class
            )
         );
      VanillaProps.stone()
         .name("sandstone_plinth_1")
         .family("sandstone_plinth")
         .manual()
         .parent(Blocks.COBBLESTONE.defaultBlockState())
         .register(TypeList.of(SlabPlinth.class));
      VanillaProps.stone()
         .name("sandstone_plinth_flat")
         .family("sandstone_plinth")
         .texture("top", "block/8_topography/1_stone/mudstone_topbottom")
         .texture("bottom", "block/8_topography/1_stone/mudstone_topbottom")
         .texture("*", "block/2_advanced_refined/1_stone/5_sandstone/sandstone_plinth")
         .register(TypeList.of(Cube.class));
      VanillaProps.stone()
         .name("marble_and_sandstone_plinth")
         .manual()
         .register(
            TypeList.of(
               CubeCapital.class,
               Balustrade.class,
               CapitalPlinth.class,
               VerticalSlabCapital.class,
               VerticalCornerCapital.class,
               VerticalQuarterCapital.class,
               PillarCapital.class,
               Stairs.class,
               Wall.class
            )
         );
      VanillaProps.stone()
         .name("marble_and_sandstone_plinth_flat")
         .texture("top", "minecraft:block/quartz_block_top")
         .texture("bottom", "block/2_advanced_refined/1_stone/5_sandstone/complex_sandstone_design")
         .texture("*", "block/2_advanced_refined/1_stone/3_marble/marble_and_sandstone_plinth")
         .register(TypeList.of(Cube.class));
      VanillaProps.stone()
         .name("marble_cornice")
         .manual()
         .register(
            TypeList.of(
               CubeCapital.class,
               Balustrade.class,
               CapitalPlinth.class,
               VerticalSlabCapital.class,
               VerticalCornerCapital.class,
               VerticalQuarterCapital.class,
               Pillar.class,
               Wall.class
            )
         );
      VanillaProps.stone()
         .name("marble_architrave")
         .manual()
         .register(
            TypeList.of(CubeCapital.class, VerticalSlabCapital.class, VerticalCornerCapital.class, VerticalQuarterCapital.class, Pillar.class, Wall.class)
         );
      VanillaProps.stone()
         .name("marble_plinth")
         .manual()
         .register(
            TypeList.of(
               CubeCapital.class,
               SlabPlinth.class,
               Balustrade.class,
               CapitalPlinth.class,
               VerticalSlabCapital.class,
               VerticalCornerCapital.class,
               VerticalQuarterCapital.class,
               PillarCapital.class,
               Stairs.class,
               Wall.class
            )
         );
      VanillaProps.stone().name("marble_plinth_1").family("marble_plinth").manual().parent(Blocks.COBBLESTONE.defaultBlockState()).register(TypeList.of(SlabPlinth.class));
      VanillaProps.stone()
         .name("marble_plinth_flat")
         .family("marble_plinth")
         .texture("top", "minecraft:block/quartz_block_top")
         .texture("bottom", "minecraft:block/quartz_block_top")
         .texture("*", "block/2_advanced_refined/1_stone/3_marble/marble_plinth")
         .register(TypeList.of(Cube.class));
      VanillaProps.metal()
         .name("fancy_gilded_pattern")
         .texture("top", "block/2_advanced_refined/2_metal/fancy_gilded_pattern_topbottom")
         .texture("bottom", "block/2_advanced_refined/2_metal/fancy_gilded_pattern_topbottom")
         .texture("*", "block/2_advanced_refined/2_metal/fancy_gilded_pattern")
         .register(TypeList.of(Cube.class, SlabLessLayers.class, VerticalSlab.class, VerticalCorner.class, VerticalQuarter.class, Cover.class));
      VanillaProps.metal()
         .name("bronze_block")
         .texture("block/2_advanced_refined/2_metal/bronze_block")
         .register(
            TypeList.of(
               Cube.class,
               Balustrade.class,
               Capital.class,
               Sphere.class,
               VerticalSlab.class,
               VerticalCorner.class,
               VerticalQuarter.class,
               Stairs.class,
               SlabLessLayers.class
            )
         );
      VanillaProps.metal()
         .name("bronze_block_with_dragon_design")
         .texture("top", "block/2_advanced_refined/2_metal/bronze_block")
         .texture("bottom", "block/2_advanced_refined/2_metal/bronze_block")
         .texture("*", "block/2_advanced_refined/2_metal/bronze_block_with_dragon_design")
         .register(TypeList.of(Cube.class, VerticalSlab.class, VerticalCorner.class, VerticalQuarter.class));
      VanillaProps.metal()
         .name("gold_bar_pile")
         .texture("top", "block/2_advanced_refined/2_metal/gold_bar_pile_topbottom")
         .texture("bottom", "block/2_advanced_refined/2_metal/gold_bar_pile_topbottom")
         .texture("*", "block/2_advanced_refined/2_metal/gold_bar_pile")
         .register(
            TypeList.of(
               Cube.class,
               Slab.class,
               SlabQuarter.class,
               SlabCorner.class,
               SlabEighth.class,
               VerticalSlabCorner.class,
               VerticalSlab.class,
               VerticalCorner.class,
               VerticalQuarter.class,
               Stairs.class
            )
         );
      VanillaProps.stone()
         .name("rope_wrapped_around_log")
         .texture("end", "block/2_advanced_refined/7_wood/rope_wrapped_around_log_topbottom")
         .texture("*", "block/2_advanced_refined/7_wood/rope_wrapped_around_log")
         .register(TypeList.of(Log.class));
      VanillaProps.stone()
         .group(ModGroups.CLOTH_AND_FIBERS)
         .name("pile_of_rope")
         .family("rope_wrapped_around_log")
         .texture("block/2_advanced_refined/7_wood/rope_wrapped_around_log")
         .register(
            TypeList.of(
               Cube.class,
               Slab.class,
               SlabQuarter.class,
               SlabCorner.class,
               SlabEighth.class,
               VerticalSlabCorner.class,
               VerticalSlab.class,
               VerticalCorner.class,
               VerticalQuarter.class,
               Stairs.class,
               WallNew.class,
               Pillar.class
            )
         );
      VanillaProps.stone()
         .name("chain_wrapped_around_log")
         .texture("end", "block/2_advanced_refined/7_wood/chain_wrapped_around_log_topbottom")
         .texture("*", "block/2_advanced_refined/7_wood/chain_wrapped_around_log")
         .register(TypeList.of(Log.class));
      VanillaProps.stone()
         .name("pile_of_chains")
         .family("chain_wrapped_around_log")
         .texture("block/2_advanced_refined/7_wood/chain_wrapped_around_log")
         .register(
            TypeList.of(
               Cube.class,
               Slab.class,
               SlabQuarter.class,
               SlabCorner.class,
               SlabEighth.class,
               VerticalSlabCorner.class,
               VerticalSlab.class,
               VerticalCorner.class,
               VerticalQuarter.class,
               Stairs.class,
               WallNew.class,
               Pillar.class
            )
         );
      VanillaProps.earth()
         .group(ModGroups.GRASS_AND_DIRT)
         .name("dirt_path")
         .texture("minecraft:block/coarse_dirt")
         .parent(Blocks.COARSE_DIRT.defaultBlockState())
         .register(TypeList.of(Layer.class));
      VanillaProps.earth()
         .group(ModGroups.GRASS_AND_DIRT)
         .name("loamy_dirt_with_cobblestone_border")
         .texture("top", "block/8_topography/5_dirt/loamy_dirt_with_cobblestone_border")
         .texture("bottom", "block/8_topography/5_dirt/loamy_dirt_with_cobblestone_border")
         .texture("*", "minecraft:block/cobblestone")
         .register(TypeList.of(Cube.class, Slab.class));
      VanillaProps.earth()
         .group(ModGroups.GRASS_AND_DIRT)
         .name("loamy_dirt_with_log_border")
         .texture("top", "block/8_topography/5_dirt/loamy_dirt_with_log_border")
         .texture("bottom", "block/8_topography/5_dirt/loamy_dirt_with_log_border")
         .texture("*", "block/1_basic_refined/3_wood/bundled_firewood")
         .register(TypeList.of(Cube.class, Slab.class));
      VanillaProps.logs()
         .name("bundled_firewood")
         .texture("top", "block/1_basic_refined/3_wood/bundled_firewood_topbottom")
         .texture("bottom", "block/1_basic_refined/3_wood/bundled_firewood_topbottom")
         .texture("end", "block/1_basic_refined/3_wood/bundled_firewood_topbottom")
         .texture("*", "block/1_basic_refined/3_wood/bundled_firewood")
         .register(
            TypeList.of(
               Log.class,
               WallNew.class,
               Pillar.class,
               SlabLessLayers.class,
               SlabQuarterNoLayers.class,
               SlabCorner.class,
               SlabEighth.class,
               VerticalSlabCorner.class,
               VerticalSlab.class,
               VerticalCorner.class,
               VerticalQuarter.class,
               Stairs.class
            )
         );
      VanillaProps.logs()
         .name("stacked_firewood")
         .texture("top", "block/1_basic_refined/3_wood/bundled_firewood_topbottom")
         .texture("bottom", "block/1_basic_refined/3_wood/bundled_firewood_topbottom")
         .texture("end", "block/1_basic_refined/3_wood/bundled_firewood_topbottom")
         .texture("*", "block/1_basic_refined/3_wood/stacked_firewood")
         .register(
            TypeList.of(
               Log.class,
               WallNew.class,
               Pillar.class,
               SlabLessLayers.class,
               SlabQuarterNoLayers.class,
               SlabCorner.class,
               SlabEighth.class,
               VerticalSlabCorner.class,
               VerticalSlabExtendable.class,
               VerticalCornerExtendable.class,
               VerticalQuarterExtendable.class,
               Stairs.class
            )
         );
      VanillaProps.logs()
         .name("horizontally_bundled_firewood")
         .family("stacked_firewood")
         .manual()
         .register(
            TypeList.of(
               Half.DirectionalWaterlogged.FamilyVariant.class,
               SlabQuarterNoLayers.class,
               SlabEighth.class,
               VerticalSlabCorner.class,
               VerticalSlab.class,
               VerticalQuarterHinged.class,
               Stairs.class
            )
         );
      VanillaProps.logs()
         .name("horizontally_stacked_firewood")
         .family("stacked_firewood")
         .parent(Blocks.OAK_PLANKS.defaultBlockState())
         .manual()
         .register(TypeList.of(SlabQuarter.class, VerticalSlab.class));
      VanillaProps.stone()
         .name("marble_plinth_cornice_model")
         .manual()
         .solid(false)
         .with("hitBox", BlockVoxelShapes.stairTopShapes)
         .register(TypeList.of(SmallCorniceModel.class));
      VanillaProps.stone()
         .name("marble_plinth_cornice_model_corner")
         .family("marble_plinth_cornice_model")
         .manual()
         .solid(false)
         .with("hitBox", BlockVoxelShapes.smallCorniceCornerShape)
         .register(TypeList.of(SmallCorniceModelCorners.class));
      VanillaProps.stone()
         .name("marble_plinth_cornice_model_quarter")
         .family("marble_plinth_cornice_model")
         .manual()
         .solid(false)
         .with("hitBox", BlockVoxelShapes.smallCorniceQuarterShape)
         .register(TypeList.of(SmallCorniceModelCorners.class));
      VanillaProps.stone()
         .name("marble_cornice_model")
         .manual()
         .solid(false)
         .with("hitBox", BlockVoxelShapes.stairTopShapes)
         .register(TypeList.of(HorizontalDirectional.OffsetXZ.class));
      VanillaProps.stone()
         .name("marble_cornice_model_corner")
         .family("marble_cornice_model")
         .manual()
         .solid(false)
         .with("hitBox", BlockVoxelShapes.stairInnerCornerTopShapes)
         .register(TypeList.of(HorizontalDirectional.OffsetXZ.Hinge.class));
      VanillaProps.stone()
         .name("marble_cornice_model_quarter")
         .family("marble_cornice_model")
         .manual()
         .solid(false)
         .with("hitBox", BlockVoxelShapes.stairOuterCornerTopShapes)
         .register(TypeList.of(HorizontalDirectional.OffsetXZ.Hinge.class));
      VanillaProps.stone()
         .name("marble_small_cornice_model")
         .manual()
         .solid(false)
         .with("hitBox", BlockVoxelShapes.stairTopShapes)
         .register(TypeList.of(SmallCorniceModel.class));
      VanillaProps.stone()
         .name("marble_small_cornice_model_corner")
         .family("marble_small_cornice_model")
         .manual()
         .solid(false)
         .with("hitBox", BlockVoxelShapes.smallCorniceCornerShape)
         .register(TypeList.of(SmallCorniceModelCorners.class));
      VanillaProps.stone()
         .name("marble_small_cornice_model_quarter")
         .family("marble_small_cornice_model")
         .manual()
         .solid(false)
         .with("hitBox", BlockVoxelShapes.smallCorniceQuarterShape)
         .register(TypeList.of(SmallCorniceModelCorners.class));
      VanillaProps.stone()
         .name("limestone_plinth_cornice_model")
         .manual()
         .solid(false)
         .with("hitBox", BlockVoxelShapes.stairTopShapes)
         .register(TypeList.of(SmallCorniceModel.class));
      VanillaProps.stone()
         .name("limestone_plinth_cornice_model_corner")
         .family("limestone_plinth_cornice_model")
         .manual()
         .solid(false)
         .with("hitBox", BlockVoxelShapes.smallCorniceCornerShape)
         .register(TypeList.of(SmallCorniceModelCorners.class));
      VanillaProps.stone()
         .name("limestone_plinth_cornice_model_quarter")
         .family("limestone_plinth_cornice_model")
         .manual()
         .solid(false)
         .with("hitBox", BlockVoxelShapes.smallCorniceQuarterShape)
         .register(TypeList.of(SmallCorniceModelCorners.class));
      VanillaProps.stone()
         .name("limestone_cornice_model")
         .manual()
         .solid(false)
         .with("hitBox", BlockVoxelShapes.stairTopShapes)
         .register(TypeList.of(HorizontalDirectional.OffsetXZ.class));
      VanillaProps.stone()
         .name("limestone_cornice_model_corner")
         .family("limestone_cornice_model")
         .manual()
         .solid(false)
         .with("hitBox", BlockVoxelShapes.stairInnerCornerTopShapes)
         .register(TypeList.of(HorizontalDirectional.OffsetXZ.Hinge.class));
      VanillaProps.stone()
         .name("limestone_cornice_model_quarter")
         .family("limestone_cornice_model")
         .manual()
         .solid(false)
         .with("hitBox", BlockVoxelShapes.stairOuterCornerTopShapes)
         .register(TypeList.of(HorizontalDirectional.OffsetXZ.Hinge.class));
      VanillaProps.stone()
         .name("limestone_small_cornice_model")
         .manual()
         .solid(false)
         .with("hitBox", BlockVoxelShapes.stairTopShapes)
         .register(TypeList.of(SmallCorniceModel.class));
      VanillaProps.stone()
         .name("limestone_small_cornice_model_corner")
         .family("limestone_small_cornice_model")
         .manual()
         .solid(false)
         .with("hitBox", BlockVoxelShapes.smallCorniceCornerShape)
         .register(TypeList.of(SmallCorniceModelCorners.class));
      VanillaProps.stone()
         .name("limestone_small_cornice_model_quarter")
         .family("limestone_small_cornice_model")
         .manual()
         .solid(false)
         .with("hitBox", BlockVoxelShapes.smallCorniceQuarterShape)
         .register(TypeList.of(SmallCorniceModelCorners.class));
      VanillaProps.stone()
         .name("caution_stripes")
         .texture("block/2_advanced_refined/8_concrete/caution_stripes")
         .register(
            TypeList.of(
               Cube.class,
               WindowSmall.class,
               WindowSmallHalf.class,
               Balustrade.class,
               Capital.class,
               Sphere.class,
               SlabLessLayers.class,
               SlabQuarter.class,
               SlabCorner.class,
               SlabEighth.class,
               VerticalSlabCorner.class,
               VerticalSlab.class,
               VerticalCorner.class,
               VerticalQuarter.class,
               Stairs.class,
               WallNew.class,
               Pillar.class
            )
         );
      VanillaProps.stone()
         .name("dark_sandstone_cornice")
         .manual()
         .register(
            TypeList.of(
               CubeCapital.class,
               Balustrade.class,
               CapitalPlinth.class,
               VerticalSlabCapital.class,
               VerticalCornerCapital.class,
               VerticalQuarterCapital.class,
               Pillar.class,
               Wall.class
            )
         );
      VanillaProps.stone()
         .name("dark_sandstone_architrave")
         .manual()
         .register(
            TypeList.of(CubeCapital.class, VerticalSlabCapital.class, VerticalCornerCapital.class, VerticalQuarterCapital.class, Pillar.class, Wall.class)
         );
      VanillaProps.stone()
         .name("dark_sandstone_plinth")
         .manual()
         .register(
            TypeList.of(
               CubeCapital.class,
               SlabPlinth.class,
               Balustrade.class,
               CapitalPlinth.class,
               VerticalSlabCapital.class,
               VerticalCornerCapital.class,
               VerticalQuarterCapital.class,
               PillarCapital.class,
               Stairs.class,
               Wall.class
            )
         );
      VanillaProps.stone()
         .name("dark_sandstone_plinth_1")
         .family("dark_sandstone_plinth")
         .manual()
         .parent(Blocks.COBBLESTONE.defaultBlockState())
         .register(TypeList.of(SlabPlinth.class));
      VanillaProps.stone()
         .name("dark_sandstone_plinth_flat")
         .family("dark_sandstone_plinth")
         .texture("top", "block/1_basic_refined/1_stone/5_sandstone/brown_sandstone_brick")
         .texture("bottom", "block/1_basic_refined/1_stone/5_sandstone/brown_sandstone_brick")
         .texture("*", "block/2_advanced_refined/1_stone/5_sandstone/dark_sandstone_plinth")
         .register(TypeList.of(Cube.class));
      VanillaProps.stone()
         .name("dark_sandstone_plinth_cornice_model")
         .manual()
         .solid(false)
         .with("hitBox", BlockVoxelShapes.stairTopShapes)
         .register(TypeList.of(SmallCorniceModel.class));
      VanillaProps.stone()
         .name("dark_sandstone_plinth_cornice_model_corner")
         .family("dark_sandstone_plinth_cornice_model")
         .manual()
         .solid(false)
         .with("hitBox", BlockVoxelShapes.smallCorniceCornerShape)
         .register(TypeList.of(SmallCorniceModelCorners.class));
      VanillaProps.stone()
         .name("dark_sandstone_plinth_cornice_model_quarter")
         .family("dark_sandstone_plinth_cornice_model")
         .manual()
         .solid(false)
         .with("hitBox", BlockVoxelShapes.smallCorniceQuarterShape)
         .register(TypeList.of(SmallCorniceModelCorners.class));
      VanillaProps.stone()
         .name("dark_sandstone_cornice_model")
         .manual()
         .solid(false)
         .with("hitBox", BlockVoxelShapes.stairTopShapes)
         .register(TypeList.of(HorizontalDirectional.OffsetXZ.class));
      VanillaProps.stone()
         .name("dark_sandstone_cornice_model_corner")
         .family("dark_sandstone_cornice_model")
         .manual()
         .solid(false)
         .with("hitBox", BlockVoxelShapes.stairInnerCornerTopShapes)
         .register(TypeList.of(HorizontalDirectional.OffsetXZ.Hinge.class));
      VanillaProps.stone()
         .name("dark_sandstone_cornice_model_quarter")
         .family("dark_sandstone_cornice_model")
         .manual()
         .solid(false)
         .with("hitBox", BlockVoxelShapes.stairOuterCornerTopShapes)
         .register(TypeList.of(HorizontalDirectional.OffsetXZ.Hinge.class));
      VanillaProps.stone()
         .name("dark_sandstone_small_cornice_model")
         .manual()
         .solid(false)
         .with("hitBox", BlockVoxelShapes.stairTopShapes)
         .register(TypeList.of(SmallCorniceModel.class));
      VanillaProps.stone()
         .name("dark_sandstone_small_cornice_model_corner")
         .family("dark_sandstone_small_cornice_model")
         .manual()
         .solid(false)
         .with("hitBox", BlockVoxelShapes.smallCorniceCornerShape)
         .register(TypeList.of(SmallCorniceModelCorners.class));
      VanillaProps.stone()
         .name("dark_sandstone_small_cornice_model_quarter")
         .family("dark_sandstone_small_cornice_model")
         .manual()
         .solid(false)
         .with("hitBox", BlockVoxelShapes.smallCorniceQuarterShape)
         .register(TypeList.of(SmallCorniceModelCorners.class));
      VanillaProps.stone()
         .name("sandstone_plinth_cornice_model")
         .manual()
         .solid(false)
         .with("hitBox", BlockVoxelShapes.stairTopShapes)
         .register(TypeList.of(SmallCorniceModel.class));
      VanillaProps.stone()
         .name("sandstone_plinth_cornice_model_corner")
         .family("sandstone_plinth_cornice_model")
         .manual()
         .solid(false)
         .with("hitBox", BlockVoxelShapes.smallCorniceCornerShape)
         .register(TypeList.of(SmallCorniceModelCorners.class));
      VanillaProps.stone()
         .name("sandstone_plinth_cornice_model_quarter")
         .family("sandstone_plinth_cornice_model")
         .manual()
         .solid(false)
         .with("hitBox", BlockVoxelShapes.smallCorniceQuarterShape)
         .register(TypeList.of(SmallCorniceModelCorners.class));
      VanillaProps.stone()
         .name("sandstone_cornice_model")
         .manual()
         .solid(false)
         .with("hitBox", BlockVoxelShapes.stairTopShapes)
         .register(TypeList.of(HorizontalDirectional.OffsetXZ.class));
      VanillaProps.stone()
         .name("sandstone_cornice_model_corner")
         .family("sandstone_cornice_model")
         .manual()
         .solid(false)
         .with("hitBox", BlockVoxelShapes.stairInnerCornerTopShapes)
         .register(TypeList.of(HorizontalDirectional.OffsetXZ.Hinge.class));
      VanillaProps.stone()
         .name("sandstone_cornice_model_quarter")
         .family("sandstone_cornice_model")
         .manual()
         .solid(false)
         .with("hitBox", BlockVoxelShapes.stairOuterCornerTopShapes)
         .register(TypeList.of(HorizontalDirectional.OffsetXZ.Hinge.class));
      VanillaProps.stone()
         .name("sandstone_small_cornice_model")
         .manual()
         .solid(false)
         .with("hitBox", BlockVoxelShapes.stairTopShapes)
         .register(TypeList.of(SmallCorniceModel.class));
      VanillaProps.stone()
         .name("sandstone_small_cornice_model_corner")
         .family("sandstone_small_cornice_model")
         .manual()
         .solid(false)
         .with("hitBox", BlockVoxelShapes.smallCorniceCornerShape)
         .register(TypeList.of(SmallCorniceModelCorners.class));
      VanillaProps.stone()
         .name("sandstone_small_cornice_model_quarter")
         .family("sandstone_small_cornice_model")
         .manual()
         .solid(false)
         .with("hitBox", BlockVoxelShapes.smallCorniceQuarterShape)
         .register(TypeList.of(SmallCorniceModelCorners.class));
      VanillaProps.stone()
         .name("limestone_cornice")
         .manual()
         .register(
            TypeList.of(
               CubeCapital.class,
               Balustrade.class,
               CapitalPlinth.class,
               VerticalSlabCapital.class,
               VerticalCornerCapital.class,
               VerticalQuarterCapital.class,
               Pillar.class,
               Wall.class
            )
         );
      VanillaProps.stone()
         .name("limestone_architrave")
         .manual()
         .register(
            TypeList.of(CubeCapital.class, VerticalSlabCapital.class, VerticalCornerCapital.class, VerticalQuarterCapital.class, Pillar.class, Wall.class)
         );
      VanillaProps.stone()
         .name("limestone_plinth")
         .manual()
         .register(
            TypeList.of(
               CubeCapital.class,
               SlabPlinth.class,
               Balustrade.class,
               CapitalPlinth.class,
               VerticalSlabCapital.class,
               VerticalCornerCapital.class,
               VerticalQuarterCapital.class,
               PillarCapital.class,
               Stairs.class,
               Wall.class
            )
         );
      VanillaProps.stone()
         .name("limestone_plinth_1")
         .family("limestone_plinth")
         .manual()
         .parent(Blocks.COBBLESTONE.defaultBlockState())
         .register(TypeList.of(SlabPlinth.class));
      VanillaProps.stone()
         .name("limestone_plinth_flat")
         .family("limestone_plinth")
         .texture("top", "minecraft:block/stone")
         .texture("bottom", "minecraft:block/stone")
         .texture("*", "block/2_advanced_refined/1_stone/2_limestone/limestone_plinth")
         .register(TypeList.of(Cube.class));
      VanillaProps.stone()
         .name("travertine_cornice")
         .manual()
         .register(
            TypeList.of(
               CubeCapital.class,
               Balustrade.class,
               CapitalPlinth.class,
               VerticalSlabCapital.class,
               VerticalCornerCapital.class,
               VerticalQuarterCapital.class,
               Pillar.class,
               Wall.class
            )
         );
      VanillaProps.stone()
         .name("travertine_architrave")
         .manual()
         .register(
            TypeList.of(CubeCapital.class, VerticalSlabCapital.class, VerticalCornerCapital.class, VerticalQuarterCapital.class, Pillar.class, Wall.class)
         );
      VanillaProps.stone()
         .name("travertine_plinth")
         .manual()
         .register(
            TypeList.of(
               CubeCapital.class,
               SlabPlinth.class,
               Balustrade.class,
               CapitalPlinth.class,
               VerticalSlabCapital.class,
               VerticalCornerCapital.class,
               VerticalQuarterCapital.class,
               PillarCapital.class,
               Stairs.class,
               Wall.class
            )
         );
      VanillaProps.stone()
         .name("travertine_plinth_1")
         .family("travertine_plinth")
         .manual()
         .parent(Blocks.COBBLESTONE.defaultBlockState())
         .register(TypeList.of(SlabPlinth.class));
      VanillaProps.stone()
         .name("travertine_plinth_flat")
         .family("travertine_plinth")
         .texture("top", "block/1_basic_refined/1_stone/4_marble/travertine_slab_topbottom")
         .texture("bottom", "block/1_basic_refined/1_stone/4_marble/travertine_slab_topbottom")
         .texture("*", "block/2_advanced_refined/1_stone/3_marble/travertine_plinth")
         .register(TypeList.of(Cube.class));
      VanillaProps.stone()
         .name("travertine_plinth_cornice_model")
         .manual()
         .solid(false)
         .with("hitBox", BlockVoxelShapes.stairTopShapes)
         .register(TypeList.of(SmallCorniceModel.class));
      VanillaProps.stone()
         .name("travertine_plinth_cornice_model_corner")
         .family("travertine_plinth_cornice_model")
         .manual()
         .solid(false)
         .with("hitBox", BlockVoxelShapes.smallCorniceCornerShape)
         .register(TypeList.of(SmallCorniceModelCorners.class));
      VanillaProps.stone()
         .name("travertine_plinth_cornice_model_quarter")
         .family("travertine_plinth_cornice_model")
         .manual()
         .solid(false)
         .with("hitBox", BlockVoxelShapes.smallCorniceQuarterShape)
         .register(TypeList.of(SmallCorniceModelCorners.class));
      VanillaProps.stone()
         .name("travertine_cornice_model")
         .manual()
         .solid(false)
         .with("hitBox", BlockVoxelShapes.stairTopShapes)
         .register(TypeList.of(HorizontalDirectional.OffsetXZ.class));
      VanillaProps.stone()
         .name("travertine_cornice_model_corner")
         .family("travertine_cornice_model")
         .manual()
         .solid(false)
         .with("hitBox", BlockVoxelShapes.stairInnerCornerTopShapes)
         .register(TypeList.of(HorizontalDirectional.OffsetXZ.Hinge.class));
      VanillaProps.stone()
         .name("travertine_cornice_model_quarter")
         .family("travertine_cornice_model")
         .manual()
         .solid(false)
         .with("hitBox", BlockVoxelShapes.stairOuterCornerTopShapes)
         .register(TypeList.of(HorizontalDirectional.OffsetXZ.Hinge.class));
      VanillaProps.stone()
         .name("travertine_small_cornice_model")
         .manual()
         .solid(false)
         .with("hitBox", BlockVoxelShapes.stairTopShapes)
         .register(TypeList.of(SmallCorniceModel.class));
      VanillaProps.stone()
         .name("travertine_small_cornice_model_corner")
         .family("travertine_small_cornice_model")
         .manual()
         .solid(false)
         .with("hitBox", BlockVoxelShapes.smallCorniceCornerShape)
         .register(TypeList.of(SmallCorniceModelCorners.class));
      VanillaProps.stone()
         .name("travertine_small_cornice_model_quarter")
         .family("travertine_small_cornice_model")
         .manual()
         .solid(false)
         .with("hitBox", BlockVoxelShapes.smallCorniceQuarterShape)
         .register(TypeList.of(SmallCorniceModelCorners.class));
      VanillaProps.metal()
         .name("copper")
         .texture("minecraft:block/copper_block")
         .parent(Blocks.WAXED_COPPER_BLOCK.defaultBlockState())
         .register(
            TypeList.of(
               Balustrade.class,
               Capital.class,
               Sphere.class,
               VerticalSlab.class,
               VerticalCorner.class,
               VerticalQuarter.class,
               Stairs.class,
               SlabLessLayers.class
            )
         );
      VanillaProps.metal()
         .name("exposed_copper")
         .texture("minecraft:block/exposed_copper")
         .parent(Blocks.WAXED_EXPOSED_COPPER.defaultBlockState())
         .register(
            TypeList.of(
               Balustrade.class,
               Capital.class,
               Sphere.class,
               VerticalSlab.class,
               VerticalCorner.class,
               VerticalQuarter.class,
               Stairs.class,
               SlabLessLayers.class
            )
         );
      VanillaProps.metal()
         .name("weathered_copper")
         .texture("minecraft:block/weathered_copper")
         .parent(Blocks.WAXED_WEATHERED_COPPER.defaultBlockState())
         .register(
            TypeList.of(
               Balustrade.class,
               Capital.class,
               Sphere.class,
               VerticalSlab.class,
               VerticalCorner.class,
               VerticalQuarter.class,
               Stairs.class,
               SlabLessLayers.class
            )
         );
      VanillaProps.metal()
         .name("oxidized_copper")
         .texture("minecraft:block/oxidized_copper")
         .parent(Blocks.WAXED_OXIDIZED_COPPER.defaultBlockState())
         .register(
            TypeList.of(
               Balustrade.class,
               Capital.class,
               Sphere.class,
               VerticalSlab.class,
               VerticalCorner.class,
               VerticalQuarter.class,
               Stairs.class,
               SlabLessLayers.class
            )
         );
   }
}
