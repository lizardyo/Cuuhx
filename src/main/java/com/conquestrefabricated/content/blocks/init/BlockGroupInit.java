package com.conquestrefabricated.content.blocks.init;

import com.conquestrefabricated.content.blocks.group.ModGroups;
import com.conquestrefabricated.core.block.factory.TypeList;
import com.conquestrefabricated.core.item.family.FamilyRegistry;
import com.conquestrefabricated.core.item.family.block.BlockFamily;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

public class BlockGroupInit {
   public BlockGroupInit() {
   }

   public static void init() {
      register(ModGroups.DECORATIONS, Blocks.FLOWER_POT);
      register(ModGroups.LIGHTING, Blocks.REDSTONE_LAMP);
      register(ModGroups.LIGHTING, Blocks.LANTERN);
      register(ModGroups.LIGHTING, Blocks.CAMPFIRE);
      register(ModGroups.LIGHTING, Blocks.TORCH);
      register(ModGroups.TOOL_BLOCKS, Blocks.DAYLIGHT_DETECTOR);
      register(ModGroups.TOOL_BLOCKS, Blocks.TRIPWIRE_HOOK);
      register(ModGroups.TOOL_BLOCKS, Blocks.ANVIL);
      register(ModGroups.TOOL_BLOCKS, Blocks.CONDUIT);
      register(ModGroups.TOOL_BLOCKS, Blocks.COMPARATOR);
      register(ModGroups.TOOL_BLOCKS, Blocks.REPEATER);
      register(ModGroups.TOOL_BLOCKS, Blocks.REDSTONE_TORCH);
      register(ModGroups.TOOL_BLOCKS, Blocks.REDSTONE_WIRE);
      register(ModGroups.TOOL_BLOCKS, Blocks.HOPPER);
      register(ModGroups.TOOL_BLOCKS, Blocks.LADDER);
      register(ModGroups.TOOL_BLOCKS, Blocks.OBSERVER);
      register(ModGroups.FOOD_BLOCKS, Blocks.CAKE);
      register(ModGroups.FOOD_BLOCKS, Blocks.MELON);
      register(ModGroups.FOOD_BLOCKS, Blocks.PUMPKIN);
      register(ModGroups.STONE, Blocks.GOLD_ORE);
      register(ModGroups.STONE, Blocks.IRON_ORE);
      register(ModGroups.STONE, Blocks.COAL_ORE);
      register(ModGroups.STONE, Blocks.LAPIS_ORE);
      register(ModGroups.STONE, Blocks.DIAMOND_ORE);
      register(ModGroups.STONE, Blocks.REDSTONE_ORE);
      register(ModGroups.STONE, Blocks.EMERALD_ORE);
      register(ModGroups.STONE, Blocks.NETHER_QUARTZ_ORE);
      register(ModGroups.FURNITURE, Blocks.RED_BED);
      register(ModGroups.FURNITURE, Blocks.JUKEBOX);
      register(ModGroups.APPLIANCES, Blocks.CRAFTING_TABLE);
      register(ModGroups.APPLIANCES, Blocks.FURNACE);
      register(ModGroups.APPLIANCES, Blocks.BLAST_FURNACE);
      register(ModGroups.APPLIANCES, Blocks.LOOM);
      register(ModGroups.APPLIANCES, Blocks.SMOKER);
      register(ModGroups.APPLIANCES, Blocks.CARTOGRAPHY_TABLE);
      register(ModGroups.APPLIANCES, Blocks.FLETCHING_TABLE);
      register(ModGroups.APPLIANCES, Blocks.GRINDSTONE);
      register(ModGroups.APPLIANCES, Blocks.LECTERN);
      register(ModGroups.APPLIANCES, Blocks.SMITHING_TABLE);
      register(ModGroups.APPLIANCES, Blocks.STONECUTTER);
      register(ModGroups.APPLIANCES, Blocks.BEEHIVE);
      register(ModGroups.APPLIANCES, Blocks.CAULDRON);
      register(ModGroups.STORAGE, Blocks.CHEST);
      register(ModGroups.STORAGE, Blocks.TRAPPED_CHEST);
      register(ModGroups.STORAGE, Blocks.ENDER_CHEST);
      register(ModGroups.STORAGE, Blocks.BARREL);
      register(ModGroups.LEAVES, Blocks.OAK_LEAVES);
      register(ModGroups.LEAVES, Blocks.DARK_OAK_LEAVES);
      register(ModGroups.LEAVES, Blocks.BIRCH_LEAVES);
      register(ModGroups.LEAVES, Blocks.SPRUCE_LEAVES);
      register(ModGroups.LEAVES, Blocks.JUNGLE_LEAVES);
      register(ModGroups.LEAVES, Blocks.ACACIA_LEAVES);
      register(ModGroups.LEAVES, Blocks.OAK_SAPLING);
      register(ModGroups.LEAVES, Blocks.DARK_OAK_SAPLING);
      register(ModGroups.LEAVES, Blocks.BIRCH_SAPLING);
      register(ModGroups.LEAVES, Blocks.SPRUCE_SAPLING);
      register(ModGroups.LEAVES, Blocks.JUNGLE_SAPLING);
      register(ModGroups.LEAVES, Blocks.ACACIA_SAPLING);
      register(ModGroups.GRASSES_AND_SHRUBS, Blocks.VINE);
      register(ModGroups.GRASSES_AND_SHRUBS, Blocks.BROWN_MUSHROOM_BLOCK);
      register(ModGroups.GRASSES_AND_SHRUBS, Blocks.RED_MUSHROOM_BLOCK);
      register(ModGroups.GRASSES_AND_SHRUBS, Blocks.MUSHROOM_STEM);
      register(ModGroups.GRASSES_AND_SHRUBS, (Block)BuiltInRegistries.BLOCK.get(ResourceLocation.parse("conquest:duckweed")));
      register(ModGroups.GRASSES_AND_SHRUBS, Blocks.LILY_PAD);
      register(ModGroups.GRASSES_AND_SHRUBS, (Block)BuiltInRegistries.BLOCK.get(ResourceLocation.parse("conquest:big_water_lilies")));
      register(ModGroups.GRASSES_AND_SHRUBS, Blocks.BAMBOO);
      register(ModGroups.GRASSES_AND_SHRUBS, Blocks.SWEET_BERRY_BUSH);
      register(ModGroups.GRASSES_AND_SHRUBS, Blocks.KELP);
      register(ModGroups.CROPS, Blocks.SUGAR_CANE);
      register(ModGroups.CROPS, Blocks.COCOA);
      register(ModGroups.WATER_AND_AIR, Blocks.LIGHT_BLUE_GLAZED_TERRACOTTA);
      register(ModGroups.WATER_AND_AIR, (Block)BuiltInRegistries.BLOCK.get(ResourceLocation.parse("conquest:floating_ice")));
      register(ModGroups.ANIMALS, Blocks.BEE_NEST);
      register(ModGroups.ANIMALS, Blocks.HONEY_BLOCK);
      register(ModGroups.ANIMALS, Blocks.HONEYCOMB_BLOCK);
      register(ModGroups.ANIMALS, Blocks.DEAD_TUBE_CORAL);
      register(ModGroups.ANIMALS, Blocks.DEAD_BRAIN_CORAL);
      register(ModGroups.ANIMALS, Blocks.DEAD_BUBBLE_CORAL);
      register(ModGroups.ANIMALS, Blocks.DEAD_FIRE_CORAL);
      register(ModGroups.ANIMALS, Blocks.DEAD_HORN_CORAL);
      register(ModGroups.ANIMALS, Blocks.DEAD_TUBE_CORAL_BLOCK);
      register(ModGroups.ANIMALS, Blocks.DEAD_BRAIN_CORAL_BLOCK);
      register(ModGroups.ANIMALS, Blocks.DEAD_BUBBLE_CORAL_BLOCK);
      register(ModGroups.ANIMALS, Blocks.DEAD_FIRE_CORAL_BLOCK);
      register(ModGroups.ANIMALS, Blocks.DEAD_HORN_CORAL_BLOCK);
      register(ModGroups.ANIMALS, Blocks.DEAD_TUBE_CORAL_FAN);
      register(ModGroups.ANIMALS, Blocks.DEAD_BRAIN_CORAL_FAN);
      register(ModGroups.ANIMALS, Blocks.DEAD_BUBBLE_CORAL_FAN);
      register(ModGroups.ANIMALS, Blocks.DEAD_FIRE_CORAL_FAN);
      register(ModGroups.ANIMALS, Blocks.DEAD_HORN_CORAL_FAN);
      register(ModGroups.ANIMALS, Blocks.SPONGE);
      register(ModGroups.ANIMALS, Blocks.WET_SPONGE);
      register(ModGroups.ANIMALS, Blocks.TURTLE_EGG);
      register(ModGroups.BREWING, Blocks.BREWING_STAND);
      register(ModGroups.UTILITY, Blocks.BARRIER);
      register(ModGroups.GRASSES_AND_SHRUBS, Blocks.SHROOMLIGHT);
   }

   private static void register(CreativeModeTab group, Block... blocks) {
      BlockFamily family = new BlockFamily(group, TypeList.EMPTY);

      for (Block block : blocks) {
         family.add(block);
         FamilyRegistry.BLOCKS.register(block, family);
      }
   }
}
