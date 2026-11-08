package com.conquestrefabricated.content.blocks.init.blocks.NoVariantBlocks;

import com.conquestrefabricated.content.blocks.block.trees.Sapling;
import com.conquestrefabricated.content.blocks.group.ModGroups;
import com.conquestrefabricated.core.block.builder.VanillaProps;
import com.conquestrefabricated.core.block.factory.TypeList;
import net.minecraft.world.level.block.grower.TreeGrower;







public class SaplingsInit {
   public SaplingsInit() {
   }

   public static void init() {
      VanillaProps.saplings()
         .group(ModGroups.LEAVES)
         .name("alder_tree_sapling")
         .manual()
         .with("tree", TreeGrower.OAK)
         .register(TypeList.of(Sapling.class));
      VanillaProps.saplings()
         .group(ModGroups.LEAVES)
         .name("apple_tree_sapling")
         .manual()
         .with("tree", TreeGrower.OAK)
         .register(TypeList.of(Sapling.class));
      VanillaProps.saplings()
         .group(ModGroups.LEAVES)
         .name("aspen_tree_sapling")
         .manual()
         .with("tree", TreeGrower.OAK)
         .register(TypeList.of(Sapling.class));
      VanillaProps.saplings()
         .group(ModGroups.LEAVES)
         .name("cherry_tree_sapling")
         .manual()
         .with("tree", TreeGrower.OAK)
         .register(TypeList.of(Sapling.class));
      VanillaProps.saplings()
         .group(ModGroups.LEAVES)
         .name("downy_willow_tree_sapling")
         .manual()
         .with("tree", TreeGrower.OAK)
         .register(TypeList.of(Sapling.class));
      VanillaProps.saplings()
         .group(ModGroups.LEAVES)
         .name("gorse_tree_sapling")
         .manual()
         .with("tree", TreeGrower.OAK)
         .register(TypeList.of(Sapling.class));
      VanillaProps.saplings()
         .group(ModGroups.LEAVES)
         .name("grape_vine_sapling")
         .manual()
         .with("tree", TreeGrower.OAK)
         .register(TypeList.of(Sapling.class));
      VanillaProps.saplings()
         .group(ModGroups.LEAVES)
         .name("horse_chestnut_tree_sapling")
         .manual()
         .with("tree", TreeGrower.OAK)
         .register(TypeList.of(Sapling.class));
      VanillaProps.saplings()
         .group(ModGroups.LEAVES)
         .name("larch_tree_sapling")
         .manual()
         .with("tree", TreeGrower.OAK)
         .register(TypeList.of(Sapling.class));
      VanillaProps.saplings()
         .group(ModGroups.LEAVES)
         .name("mallorn_tree_sapling")
         .manual()
         .with("tree", TreeGrower.OAK)
         .register(TypeList.of(Sapling.class));
      VanillaProps.saplings()
         .group(ModGroups.LEAVES)
         .name("orange_tree_sapling")
         .manual()
         .with("tree", TreeGrower.OAK)
         .register(TypeList.of(Sapling.class));
      VanillaProps.saplings().group(ModGroups.LEAVES).name("pear_tree_sapling").manual().with("tree", TreeGrower.OAK).register(TypeList.of(Sapling.class));
      VanillaProps.saplings().group(ModGroups.LEAVES).name("pine_tree_sapling").manual().with("tree", TreeGrower.OAK).register(TypeList.of(Sapling.class));
      VanillaProps.saplings().group(ModGroups.LEAVES).name("plum_tree_sapling").manual().with("tree", TreeGrower.OAK).register(TypeList.of(Sapling.class));
      VanillaProps.saplings()
         .group(ModGroups.LEAVES)
         .name("rowan_tree_sapling")
         .manual()
         .with("tree", TreeGrower.OAK)
         .register(TypeList.of(Sapling.class));
      VanillaProps.saplings()
         .group(ModGroups.LEAVES)
         .name("willow_tree_sapling")
         .manual()
         .with("tree", TreeGrower.OAK)
         .register(TypeList.of(Sapling.class));
      VanillaProps.saplings().group(ModGroups.LEAVES).name("oak_sapling").manual().with("tree", TreeGrower.OAK).register(TypeList.of(Sapling.class));
      VanillaProps.saplings().group(ModGroups.LEAVES).name("birch_sapling").manual().with("tree", TreeGrower.BIRCH).register(TypeList.of(Sapling.class));
      VanillaProps.saplings().group(ModGroups.LEAVES).name("spruce_sapling").manual().with("tree", TreeGrower.SPRUCE).register(TypeList.of(Sapling.class));
      VanillaProps.saplings().group(ModGroups.LEAVES).name("jungle_sapling").manual().with("tree", TreeGrower.JUNGLE).register(TypeList.of(Sapling.class));
      VanillaProps.saplings().group(ModGroups.LEAVES).name("acacia_sapling").manual().with("tree", TreeGrower.ACACIA).register(TypeList.of(Sapling.class));
      VanillaProps.saplings()
         .group(ModGroups.LEAVES)
         .name("dark_oak_sapling")
         .manual()
         .with("tree", TreeGrower.DARK_OAK)
         .register(TypeList.of(Sapling.class));
   }
}
