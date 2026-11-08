package com.conquestrefabricated.content.blocks.tileentity;

import com.conquestrefabricated.content.blocks.tileentity.campfire.ModdedCampfireTileEntity;
import com.conquestrefabricated.content.blocks.tileentity.enchantment.ModdedEnchantingTableTileEntity;
import com.conquestrefabricated.content.blocks.tileentity.forge.TileEntityTypesImpl;
import com.conquestrefabricated.content.blocks.tileentity.furnace.ModdedFurnaceTileEntity;
import com.conquestrefabricated.content.blocks.tileentity.loom.LoomBlockEntity;
import com.conquestrefabricated.content.blocks.tileentity.seat.SeatTileEntity;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.BlockEntityType.BlockEntitySupplier;

public class TileEntityTypes {
   public static final BlockEntityType<KilnTileEntity> KILN = create(KilnTileEntity::new, "conquest:kiln", "conquest:small_kiln", "conquest:tall_kiln");
   public static final BlockEntityType<LoomBlockEntity> LOOM = create(
      LoomBlockEntity::new,
      "conquest:loom",
      "conquest:simple_warp_weighted_loom",
      "conquest:warp_weighted_loom",
      "conquest:two_beam_loom",
      "conquest:standing_two_beam_loom"
   );
   public static final BlockEntityType<ModdedEnchantingTableTileEntity> ENCHANTING_TABLE = create(
      ModdedEnchantingTableTileEntity::new, "conquest:enchanter", "conquest:enchantment_table", "conquest:floating_book"
   );
   public static final BlockEntityType<ModdedCampfireTileEntity> CAMPFIRE = create(ModdedCampfireTileEntity::new, "conquest:campfire", "conquest:campfire");
   public static final BlockEntityType<ModdedFurnaceTileEntity> FURNACE = create(
      ModdedFurnaceTileEntity::new, "conquest:oven", "conquest:sandstone_oven", "conquest:iron_oven"
   );
   public static final BlockEntityType<AnimalTileEntity> ANIMAL = create(
      AnimalTileEntity::new,
      "conquest:animal",
      "conquest:raven",
      "conquest:hawk",
      "conquest:bluejay",
      "conquest:pigeon",
      "conquest:duck",
      "conquest:puffin",
      "conquest:seagull",
      "conquest:owl",
      "conquest:bat",
      "conquest:rat",
      "conquest:toad"
   );
   public static final BlockEntityType<SeatTileEntity> SEAT = create(
      SeatTileEntity::new,
      "conquest:seat",
      "conquest:spruce_chair",
      "conquest:fancy_chair",
      "conquest:oak_chair",
      "conquest:spruce_wood_stool",
      "conquest:round_wooden_stool",
      "conquest:wicker_stool",
      "conquest:small_wicker_stool",
      "conquest:red_cushion_stool",
      "conquest:small_red_cushion_stool",
      "conquest:small_old_leather_stool",
      "conquest:small_oak_wood_stool",
      "conquest:small_green_cushion_stool",
      "conquest:small_fancy_stool_base",
      "conquest:small_fancy_leather_stool",
      "conquest:small_burlap_stool",
      "conquest:small_blue_cushion_stool",
      "conquest:old_leather_stool",
      "conquest:oak_wood_stool",
      "conquest:green_cushion_stool",
      "conquest:fancy_stool_base",
      "conquest:burlap_stool",
      "conquest:fancy_leather_stool",
      "conquest:blue_cushion_stool"
   );

   public TileEntityTypes() {
   }

   public static void init() {
   }

   public static <T extends BlockEntity> BlockEntityType<T> create(BlockEntitySupplier<T> factory, String name, String... blockNames) {
      return TileEntityTypesImpl.create(factory, name, blockNames);
   }
}
