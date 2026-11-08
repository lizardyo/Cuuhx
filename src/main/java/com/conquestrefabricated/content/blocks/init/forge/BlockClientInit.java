package com.conquestrefabricated.content.blocks.init.forge;

import com.conquestrefabricated.content.blocks.group.ModGroups;
import com.conquestrefabricated.content.blocks.init.BlockFamilyInit;
import com.conquestrefabricated.content.blocks.init.BlockGroupInit;
import com.conquestrefabricated.content.blocks.tileentity.TileEntityTypes;
import com.conquestrefabricated.content.blocks.tileentity.enchantment.ModdedTileEntityEnchanterRenderer;
import com.conquestrefabricated.core.client.color.BlockColors;
import com.conquestrefabricated.core.group.forge.FamilyGroup;
import com.conquestrefabricated.core.item.family.DeferredFamilyRegistry;
import com.conquestrefabricated.core.util.log.Log;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.EventBusSubscriber.Bus;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent.RegisterRenderers;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent.Block;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent.Item;

@EventBusSubscriber(value = Dist.CLIENT, bus = Bus.MOD)
public class BlockClientInit {
   public BlockClientInit() {
   }

   public static void client() {
      Log.info("Initializing ModItemGroups");
      ModGroups.initGroups();
   }

   @SubscribeEvent
   public static void clientBlockEntities(RegisterRenderers event) {
      event.registerBlockEntityRenderer(TileEntityTypes.ENCHANTING_TABLE, ModdedTileEntityEnchanterRenderer::new);
   }

   @SubscribeEvent
   public static void common(FMLClientSetupEvent event) {
      Log.info("Initializing Block/Item families");
      BlockGroupInit.init();
      BlockFamilyInit.init();
      DeferredFamilyRegistry.BLOCKS.registerAll();
      ItemBlockRenderTypes.setRenderLayer(ScaffoldingInit.METAL_SCAFFOLDING, RenderType.cutout());
      ItemBlockRenderTypes.setRenderLayer(ScaffoldingInit.WOOD_SCAFFOLDING, RenderType.cutout());
      ItemBlockRenderTypes.setRenderLayer(LiliesInit.WHITE_WATER_LILIES, RenderType.cutout());
      ItemBlockRenderTypes.setRenderLayer(LiliesInit.BIG_WATER_LILIES, RenderType.cutout());
      ItemBlockRenderTypes.setRenderLayer(LiliesInit.DUCKWEED, RenderType.cutout());
      ItemBlockRenderTypes.setRenderLayer(LiliesInit.FLOATING_ICE, RenderType.cutout());
      ItemBlockRenderTypes.setRenderLayer(Blocks.INFESTED_MOSSY_STONE_BRICKS, RenderType.cutout());
      ItemBlockRenderTypes.setRenderLayer(Blocks.MOSSY_STONE_BRICKS, RenderType.cutout());
      ItemBlockRenderTypes.setRenderLayer(Blocks.MOSSY_STONE_BRICK_SLAB, RenderType.cutout());
      ItemBlockRenderTypes.setRenderLayer(Blocks.MOSSY_STONE_BRICK_STAIRS, RenderType.cutout());
      ItemBlockRenderTypes.setRenderLayer(Blocks.ANVIL, RenderType.cutout());
      ItemBlockRenderTypes.setRenderLayer(Blocks.CHIPPED_ANVIL, RenderType.cutout());
      ItemBlockRenderTypes.setRenderLayer(Blocks.DAMAGED_ANVIL, RenderType.cutout());
      ItemBlockRenderTypes.setRenderLayer(Blocks.WHITE_BED, RenderType.cutout());
      ItemBlockRenderTypes.setRenderLayer(Blocks.ORANGE_BED, RenderType.cutout());
      ItemBlockRenderTypes.setRenderLayer(Blocks.MAGENTA_BED, RenderType.cutout());
      ItemBlockRenderTypes.setRenderLayer(Blocks.YELLOW_BED, RenderType.cutout());
      ItemBlockRenderTypes.setRenderLayer(Blocks.LIME_BED, RenderType.cutout());
      ItemBlockRenderTypes.setRenderLayer(Blocks.PINK_BED, RenderType.cutout());
      ItemBlockRenderTypes.setRenderLayer(Blocks.LIGHT_GRAY_BED, RenderType.cutout());
      ItemBlockRenderTypes.setRenderLayer(Blocks.PURPLE_BED, RenderType.cutout());
      ItemBlockRenderTypes.setRenderLayer(Blocks.RED_BED, RenderType.cutout());
      ItemBlockRenderTypes.setRenderLayer(Blocks.BLACK_BED, RenderType.cutout());
      ItemBlockRenderTypes.setRenderLayer(Blocks.BLUE_BED, RenderType.cutout());
      ItemBlockRenderTypes.setRenderLayer(Blocks.BROWN_BED, RenderType.cutout());
      ItemBlockRenderTypes.setRenderLayer(Blocks.CYAN_BED, RenderType.cutout());
      ItemBlockRenderTypes.setRenderLayer(Blocks.GRAY_BED, RenderType.cutout());
      ItemBlockRenderTypes.setRenderLayer(Blocks.GREEN_BED, RenderType.cutout());
      ItemBlockRenderTypes.setRenderLayer(Blocks.LIGHT_BLUE_BED, RenderType.cutout());
      ItemBlockRenderTypes.setRenderLayer(Blocks.ENCHANTING_TABLE, RenderType.cutout());
      ItemBlockRenderTypes.setRenderLayer(Blocks.WHITE_CANDLE, RenderType.cutout());
      ItemBlockRenderTypes.setRenderLayer(Blocks.ORANGE_CANDLE, RenderType.cutout());
      ItemBlockRenderTypes.setRenderLayer(Blocks.MAGENTA_CANDLE, RenderType.cutout());
      ItemBlockRenderTypes.setRenderLayer(Blocks.YELLOW_CANDLE, RenderType.cutout());
      ItemBlockRenderTypes.setRenderLayer(Blocks.LIME_CANDLE, RenderType.cutout());
      ItemBlockRenderTypes.setRenderLayer(Blocks.PINK_CANDLE, RenderType.cutout());
      ItemBlockRenderTypes.setRenderLayer(Blocks.LIGHT_GRAY_CANDLE, RenderType.cutout());
      ItemBlockRenderTypes.setRenderLayer(Blocks.PURPLE_CANDLE, RenderType.cutout());
      ItemBlockRenderTypes.setRenderLayer(Blocks.RED_CANDLE, RenderType.cutout());
      ItemBlockRenderTypes.setRenderLayer(Blocks.BLACK_CANDLE, RenderType.cutout());
      ItemBlockRenderTypes.setRenderLayer(Blocks.BLUE_CANDLE, RenderType.cutout());
      ItemBlockRenderTypes.setRenderLayer(Blocks.BROWN_CANDLE, RenderType.cutout());
      ItemBlockRenderTypes.setRenderLayer(Blocks.CYAN_CANDLE, RenderType.cutout());
      ItemBlockRenderTypes.setRenderLayer(Blocks.GRAY_CANDLE, RenderType.cutout());
      ItemBlockRenderTypes.setRenderLayer(Blocks.GREEN_CANDLE, RenderType.cutout());
      ItemBlockRenderTypes.setRenderLayer(Blocks.LIGHT_BLUE_CANDLE, RenderType.cutout());
      ItemBlockRenderTypes.setRenderLayer(Blocks.CANDLE, RenderType.cutout());
   }

   @SubscribeEvent
   public static void complete(FMLLoadCompleteEvent event) {
      Log.info("Setting up GroupManager");
      FamilyGroup.setAddRootItems();
   }

   @SubscribeEvent
   public static void blockColors(Block event) {
      Log.info("Registering additional vanilla block colors");
      event.register(BlockColors.GRASS, new net.minecraft.world.level.block.Block[]{Blocks.INFESTED_MOSSY_STONE_BRICKS, Blocks.MOSSY_STONE_BRICKS, Blocks.MOSSY_STONE_BRICK_SLAB, Blocks.MOSSY_STONE_BRICK_STAIRS});
   }

   @SubscribeEvent
   public static void items(Item event) {
      Log.info("Registering additional vanilla item colors");
      event.register(BlockColors.toItemColor(event.getBlockColors()), new ItemLike[]{Blocks.INFESTED_MOSSY_STONE_BRICKS, Blocks.MOSSY_STONE_BRICKS, Blocks.MOSSY_STONE_BRICK_SLAB, Blocks.MOSSY_STONE_BRICK_STAIRS});
   }
}
