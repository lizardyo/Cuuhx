package com.conquestrefabricated.content.blocks.init.blocks.NoVariantBlocks;

import com.conquestrefabricated.content.blocks.BlockVoxelShapes;
import com.conquestrefabricated.content.blocks.block.decor.Animal;
import com.conquestrefabricated.content.blocks.block.decor.ModelBlock;
import com.conquestrefabricated.content.blocks.group.ModGroups;
import com.conquestrefabricated.core.block.builder.VanillaProps;
import com.conquestrefabricated.core.block.factory.TypeList;
import com.conquestrefabricated.core.util.RenderLayer;

public class AnimalsInit {
   public AnimalsInit() {
   }

   public static void init() {
      VanillaProps.cloth()
         .group(ModGroups.ANIMALS)
         .name("raven")
         .manual()
         .blocking(false)
         .render(RenderLayer.CUTOUT)
         .with("hitBox", BlockVoxelShapes.cubePartialShape)
         .register(TypeList.of(Animal.Bird.class));
      VanillaProps.cloth()
         .group(ModGroups.ANIMALS)
         .name("hawk")
         .manual()
         .blocking(false)
         .render(RenderLayer.CUTOUT)
         .with("hitBox", BlockVoxelShapes.cubePartialShape)
         .register(TypeList.of(Animal.Bird.class));
      VanillaProps.cloth()
         .group(ModGroups.ANIMALS)
         .name("owl")
         .manual()
         .blocking(false)
         .render(RenderLayer.CUTOUT)
         .with("hitBox", BlockVoxelShapes.cubePartialShape)
         .register(TypeList.of(Animal.Bird.class));
      VanillaProps.cloth()
         .group(ModGroups.ANIMALS)
         .name("seagull")
         .manual()
         .blocking(false)
         .render(RenderLayer.CUTOUT)
         .with("hitBox", BlockVoxelShapes.cubePartialShape)
         .register(TypeList.of(Animal.Bird.class));
      VanillaProps.cloth()
         .group(ModGroups.ANIMALS)
         .name("duck")
         .manual()
         .blocking(false)
         .render(RenderLayer.CUTOUT)
         .with("hitBox", BlockVoxelShapes.cubePartialShape)
         .register(TypeList.of(Animal.Bird.class));
      VanillaProps.cloth()
         .group(ModGroups.ANIMALS)
         .name("pigeon")
         .manual()
         .blocking(false)
         .render(RenderLayer.CUTOUT)
         .with("hitBox", BlockVoxelShapes.cubePartialShape)
         .register(TypeList.of(Animal.Bird.class));
      VanillaProps.cloth()
         .group(ModGroups.ANIMALS)
         .name("bluejay")
         .manual()
         .blocking(false)
         .render(RenderLayer.CUTOUT)
         .with("hitBox", BlockVoxelShapes.cubePartialShape)
         .register(TypeList.of(Animal.Bird.class));
      VanillaProps.cloth()
         .group(ModGroups.ANIMALS)
         .name("bat")
         .manual()
         .blocking(false)
         .render(RenderLayer.CUTOUT)
         .with("hitBox", BlockVoxelShapes.cubePartialShape)
         .register(TypeList.of(Animal.Bird.class));
      VanillaProps.cloth()
         .group(ModGroups.ANIMALS)
         .name("rat")
         .manual()
         .blocking(false)
         .render(RenderLayer.CUTOUT)
         .with("hitBox", BlockVoxelShapes.cubePartialShape)
         .register(TypeList.of(Animal.class));
      VanillaProps.cloth()
         .group(ModGroups.ANIMALS)
         .name("toad")
         .manual()
         .blocking(false)
         .render(RenderLayer.CUTOUT)
         .with("hitBox", BlockVoxelShapes.cubePartialShape)
         .register(TypeList.of(Animal.class));
      VanillaProps.cloth()
         .group(ModGroups.ANIMALS)
         .name("puffin")
         .manual()
         .blocking(false)
         .render(RenderLayer.CUTOUT)
         .with("hitBox", BlockVoxelShapes.cubePartialShape)
         .register(TypeList.of(Animal.class));
      VanillaProps.cloth()
         .group(ModGroups.ANIMALS)
         .name("red_butterfly")
         .manual()
         .blocking(false)
         .render(RenderLayer.CUTOUT)
         .with("hitBox", BlockVoxelShapes.cubePartialShape)
         .register(TypeList.of(ModelBlock.class));
      VanillaProps.cloth()
         .group(ModGroups.ANIMALS)
         .name("blue_butterfly")
         .manual()
         .blocking(false)
         .render(RenderLayer.CUTOUT)
         .with("hitBox", BlockVoxelShapes.cubePartialShape)
         .register(TypeList.of(ModelBlock.class));
      VanillaProps.cloth()
         .group(ModGroups.ANIMALS)
         .name("dragon_flies")
         .manual()
         .blocking(false)
         .render(RenderLayer.CUTOUT)
         .with("hitBox", BlockVoxelShapes.cubePartialShape)
         .register(TypeList.of(ModelBlock.class));
      VanillaProps.cloth()
         .group(ModGroups.ANIMALS)
         .name("flies")
         .manual()
         .blocking(false)
         .render(RenderLayer.CUTOUT)
         .with("hitBox", BlockVoxelShapes.cubePartialShape)
         .register(TypeList.of(ModelBlock.class));
      VanillaProps.cloth()
         .group(ModGroups.ANIMALS)
         .name("fire_flies")
         .manual()
         .blocking(false)
         .render(RenderLayer.CUTOUT)
         .with("hitBox", BlockVoxelShapes.cubePartialShape)
         .register(TypeList.of(ModelBlock.class));
   }
}
