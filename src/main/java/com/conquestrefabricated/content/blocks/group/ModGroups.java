package com.conquestrefabricated.content.blocks.group;

import com.conquestrefabricated.content.blocks.group.forge.ModGroupsImpl;
import com.conquestrefabricated.core.item.group.manager.ItemGroupManager;
import com.conquestrefabricated.core.util.Provider;
import java.util.function.Supplier;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.CreativeModeTab.Row;

public class ModGroups {
   public static final CreativeModeTab WINDOWS_AND_GLASS = createFamilyGroup(10, "ff_windows_and_glass", block("minecraft:glass"));
   public static final CreativeModeTab CLOTH_AND_FIBERS = createFamilyGroup(11, "g_cloth_and_fibers", block("conquest:magenta_carpet"));
   public static final CreativeModeTab FURNITURE = createFamilyGroup(12, "gg_furniture", block("conquest:old_rustic_bed"));
   public static final CreativeModeTab APPLIANCES = createFamilyGroup(13, "h_appliances", block("minecraft:loom"));
   public static final CreativeModeTab STORAGE = createFamilyGroup(14, "hh_storage", block("conquest:rounded_chest"));
   public static final CreativeModeTab DECORATIONS = createFamilyGroup(15, "i_decor", block("conquest:towel_rack"));
   public static final CreativeModeTab POTTERY = createFamilyGroup(15, "ia_pottery", block("conquest:terracotta_urn"));
   public static final CreativeModeTab LIGHTING = createFamilyGroup(16, "ii_lighting", block("conquest:small_lantern"));
   public static final CreativeModeTab TOOL_BLOCKS = createFamilyGroup(17, "j_tool_blocks", block("conquest:rack_of_pitchforks_scythes_and_flails"));
   public static final CreativeModeTab FOOD_BLOCKS = createFamilyGroup(18, "jj_food_blocks", block("conquest:big_bread"));
   public static final CreativeModeTab STONE = createFamilyGroup(19, "k_stone", block("conquest:mudstone"));
   public static final CreativeModeTab GRASS_AND_DIRT = createFamilyGroup(20, "kk_grass_and_dirt", block("minecraft:grass_block"));
   public static final CreativeModeTab SAND_AND_GRAVEL = createFamilyGroup(21, "l_sand_and_gravel", block("conquest:small_stones"));
   public static final CreativeModeTab LOGS = createFamilyGroup(22, "ll_logs", block("minecraft:oak_log"));
   public static final CreativeModeTab LEAVES = createFamilyGroup(23, "m_leaves", block("minecraft:dark_oak_leaves"));
   public static final CreativeModeTab GRASSES_AND_SHRUBS = createFamilyGroup(24, "mm_grasses_and_shrubs", block("minecraft:grass"));
   public static final CreativeModeTab FLOWERS = createFamilyGroup(25, "n_flowers", block("conquest:hanging_dandelions"));
   public static final CreativeModeTab CROPS = createFamilyGroup(26, "nn_crops_and_herbs", block("conquest:wild_wheat"));
   public static final CreativeModeTab WATER_AND_AIR = createFamilyGroup(27, "o_water_and_air", block("conquest:steam"));
   public static final CreativeModeTab ANIMALS = createFamilyGroup(28, "oo_animals", block("conquest:owl"));
   public static final CreativeModeTab ARMOR = createFamilyGroup(29, "p_armor", item("minecraft:chainmail_chestplate"));
   public static final CreativeModeTab WEAPONS_AND_TOOLS = createFamilyGroup(30, "pp_weapons_and_tools", item("minecraft:iron_axe"));
   public static final CreativeModeTab FOOD_AND_CONSUMABLES = createFamilyGroup(31, "q_food_and_consumables", item("minecraft:bread"));
   public static final CreativeModeTab BREWING = createFamilyGroup(32, "qq_brewing", block("minecraft:brewing_stand"));
   public static final CreativeModeTab MISCELLANEOUS = createFamilyGroup(33, "r_miscellaneous", item("minecraft:music_disc_strad"));
   public static final CreativeModeTab UTILITY = createFamilyGroup(34, "rr_utility", block("minecraft:barrier"));
   public static Row rowValue = Row.TOP;

   public ModGroups() {
   }

   public static void initGroups() {
      ItemGroupManager.getInstance().register(WINDOWS_AND_GLASS);
      ItemGroupManager.getInstance().register(CLOTH_AND_FIBERS);
      ItemGroupManager.getInstance().register(FURNITURE);
      ItemGroupManager.getInstance().register(APPLIANCES);
      ItemGroupManager.getInstance().register(STORAGE);
      ItemGroupManager.getInstance().register(DECORATIONS);
      ItemGroupManager.getInstance().register(POTTERY);
      ItemGroupManager.getInstance().register(LIGHTING);
      ItemGroupManager.getInstance().register(TOOL_BLOCKS);
      ItemGroupManager.getInstance().register(FOOD_BLOCKS);
      ItemGroupManager.getInstance().register(STONE);
      ItemGroupManager.getInstance().register(GRASS_AND_DIRT);
      ItemGroupManager.getInstance().register(SAND_AND_GRAVEL);
      ItemGroupManager.getInstance().register(LOGS);
      ItemGroupManager.getInstance().register(LEAVES);
      ItemGroupManager.getInstance().register(GRASSES_AND_SHRUBS);
      ItemGroupManager.getInstance().register(FLOWERS);
      ItemGroupManager.getInstance().register(CROPS);
      ItemGroupManager.getInstance().register(WATER_AND_AIR);
      ItemGroupManager.getInstance().register(ANIMALS);
      ItemGroupManager.getInstance().register(ARMOR);
      ItemGroupManager.getInstance().register(ANIMALS);
      ItemGroupManager.getInstance().register(WEAPONS_AND_TOOLS);
      ItemGroupManager.getInstance().register(FOOD_AND_CONSUMABLES);
      ItemGroupManager.getInstance().register(MISCELLANEOUS);
      ItemGroupManager.getInstance().register(UTILITY);
   }

   public static Supplier<ItemStack> block(String name) {
      return Provider.block(name).toStack();
   }

   public static Supplier<ItemStack> item(String name) {
      return Provider.item(name).toStack();
   }

   public static void toggleRow(int order) {
      if (order % 5 == 0) {
         if (rowValue == Row.TOP) {
            rowValue = Row.BOTTOM;
         } else if (rowValue == Row.BOTTOM) {
            rowValue = Row.TOP;
         }
      }
   }

   public static CreativeModeTab createFamilyGroup(int order, String label, Supplier<ItemStack> icon) {
      return ModGroupsImpl.createFamilyGroup(order, label, icon);
   }
}
