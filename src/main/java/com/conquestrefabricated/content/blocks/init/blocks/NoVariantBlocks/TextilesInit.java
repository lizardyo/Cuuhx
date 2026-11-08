package com.conquestrefabricated.content.blocks.init.blocks.NoVariantBlocks;

import com.conquestrefabricated.content.blocks.BlockVoxelShapes;
import com.conquestrefabricated.content.blocks.block.FourWay;
import com.conquestrefabricated.content.blocks.block.decor.Ladder;
import com.conquestrefabricated.content.blocks.block.decor.Loom;
import com.conquestrefabricated.content.blocks.block.decor.ModelBlock;
import com.conquestrefabricated.content.blocks.block.decor.VineCurtain;
import com.conquestrefabricated.content.blocks.block.directional.HorizontalDirectional;
import com.conquestrefabricated.content.blocks.group.ModGroups;
import com.conquestrefabricated.core.block.builder.VanillaProps;
import com.conquestrefabricated.core.block.factory.TypeList;
import com.conquestrefabricated.core.util.RenderLayer;

public class TextilesInit {
   public TextilesInit() {
   }

   public static void init() {
      VanillaProps.cloth()
         .group(ModGroups.CLOTH_AND_FIBERS)
         .name("black_curtain")
         .texture("block/3_decoration/4_cloth/black_curtain")
         .register(TypeList.of(Ladder.LadderPane.class));
      VanillaProps.cloth().name("black_curtain_vine").family("black_curtain").render(RenderLayer.CUTOUT).register(TypeList.of(VineCurtain.class));
      VanillaProps.cloth()
         .group(ModGroups.CLOTH_AND_FIBERS)
         .name("blue_curtain")
         .texture("block/3_decoration/4_cloth/blue_curtain")
         .register(TypeList.of(Ladder.LadderPane.class));
      VanillaProps.cloth().name("blue_curtain_vine").family("blue_curtain").render(RenderLayer.CUTOUT).register(TypeList.of(VineCurtain.class));
      VanillaProps.cloth()
         .group(ModGroups.CLOTH_AND_FIBERS)
         .name("brown_curtain")
         .texture("block/3_decoration/4_cloth/brown_curtain")
         .register(TypeList.of(Ladder.LadderPane.class));
      VanillaProps.cloth().name("brown_curtain_vine").family("brown_curtain").render(RenderLayer.CUTOUT).register(TypeList.of(VineCurtain.class));
      VanillaProps.cloth()
         .group(ModGroups.CLOTH_AND_FIBERS)
         .name("green_curtain")
         .texture("block/3_decoration/4_cloth/green_curtain")
         .register(TypeList.of(Ladder.LadderPane.class));
      VanillaProps.cloth().name("green_curtain_vine").family("green_curtain").render(RenderLayer.CUTOUT).register(TypeList.of(VineCurtain.class));
      VanillaProps.cloth()
         .group(ModGroups.CLOTH_AND_FIBERS)
         .name("white_curtain")
         .texture("block/3_decoration/4_cloth/white_curtain")
         .register(TypeList.of(Ladder.LadderPane.class));
      VanillaProps.cloth().name("white_curtain_vine").family("white_curtain").render(RenderLayer.CUTOUT).register(TypeList.of(VineCurtain.class));
      VanillaProps.cloth()
         .group(ModGroups.CLOTH_AND_FIBERS)
         .name("purple_curtain")
         .texture("block/3_decoration/4_cloth/purple_curtain")
         .register(TypeList.of(Ladder.LadderPane.class));
      VanillaProps.cloth().name("purple_curtain_vine").family("purple_curtain").render(RenderLayer.CUTOUT).register(TypeList.of(VineCurtain.class));
      VanillaProps.cloth()
         .group(ModGroups.CLOTH_AND_FIBERS)
         .name("red_curtain")
         .texture("block/3_decoration/4_cloth/red_curtain")
         .register(TypeList.of(Ladder.LadderPane.class));
      VanillaProps.cloth().name("red_curtain_vine").family("red_curtain").render(RenderLayer.CUTOUT).register(TypeList.of(VineCurtain.class));
      VanillaProps.cloth().group(ModGroups.CLOTH_AND_FIBERS).name("clothes_line").manual().blocking(false).register(TypeList.of(FourWay.class));
      VanillaProps.cloth().group(ModGroups.CLOTH_AND_FIBERS).name("yellow_and_red_buntings").manual().blocking(false).register(TypeList.of(FourWay.class));
      VanillaProps.cloth().group(ModGroups.CLOTH_AND_FIBERS).name("blue_and_green_buntings").manual().blocking(false).register(TypeList.of(FourWay.class));
      VanillaProps.cloth()
         .group(ModGroups.CLOTH_AND_FIBERS)
         .name("bear_hide")
         .manual()
         .blocking(false)
         .render(RenderLayer.CUTOUT)
         .with("hitBox", BlockVoxelShapes.paneShape)
         .register(TypeList.of(HorizontalDirectional.OffsetXYZ.class));
      VanillaProps.cloth()
         .group(ModGroups.CLOTH_AND_FIBERS)
         .name("hanging_bear_skin")
         .manual()
         .blocking(false)
         .render(RenderLayer.CUTOUT)
         .with("hitBox", BlockVoxelShapes.cubePartialShape)
         .register(TypeList.of(ModelBlock.OffsetXYZ.class));
      VanillaProps.cloth()
         .group(ModGroups.CLOTH_AND_FIBERS)
         .name("bear_skin_rug")
         .manual()
         .blocking(false)
         .render(RenderLayer.CUTOUT)
         .with("hitBox", BlockVoxelShapes.coverShape)
         .register(TypeList.of(ModelBlock.OffsetXYZ.class));
      VanillaProps.cloth()
         .group(ModGroups.CLOTH_AND_FIBERS)
         .name("wolf_pelt_rug")
         .manual()
         .blocking(false)
         .render(RenderLayer.CUTOUT)
         .with("hitBox", BlockVoxelShapes.coverShape)
         .register(TypeList.of(ModelBlock.OffsetXYZ.class));
      VanillaProps.cloth()
         .group(ModGroups.CLOTH_AND_FIBERS)
         .name("sheep_skin_rug")
         .manual()
         .blocking(false)
         .render(RenderLayer.CUTOUT)
         .with("hitBox", BlockVoxelShapes.coverShape)
         .register(TypeList.of(ModelBlock.OffsetXYZ.class));
      VanillaProps.planks()
         .render(RenderLayer.CUTOUT)
         .group(ModGroups.APPLIANCES)
         .name("warp_weighted_loom")
         .manual()
         .blocking(false)
         .dynamicBounds(true)
         .with("hitBox", BlockVoxelShapes.cubePartialShape)
         .register(TypeList.of(Loom.class));
      VanillaProps.planks()
         .render(RenderLayer.CUTOUT)
         .group(ModGroups.APPLIANCES)
         .name("simple_warp_weighted_loom")
         .manual()
         .blocking(false)
         .dynamicBounds(true)
         .with("hitBox", BlockVoxelShapes.cubePartialShape)
         .register(TypeList.of(Loom.class));
      VanillaProps.planks()
         .render(RenderLayer.CUTOUT)
         .group(ModGroups.APPLIANCES)
         .name("two_beam_loom")
         .manual()
         .blocking(false)
         .dynamicBounds(true)
         .with("hitBox", BlockVoxelShapes.cubePartialShape)
         .register(TypeList.of(Loom.class));
      VanillaProps.planks()
         .render(RenderLayer.CUTOUT)
         .group(ModGroups.APPLIANCES)
         .name("standing_two_beam_loom")
         .manual()
         .blocking(false)
         .dynamicBounds(true)
         .with("hitBox", BlockVoxelShapes.cubePartialShape)
         .register(TypeList.of(Loom.class));
   }
}
