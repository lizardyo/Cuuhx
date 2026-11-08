package com.conquestrefabricated.client.models;

import com.conquestrefabricated.core.asset.annotation.SpecialOffset;
import com.conquestrefabricated.core.block.builder.SpecialOffsetType;
import java.util.Map;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.Block;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ModelEvent;

/** Replaces TranslatedModelLoaderMixin (no mixin into ModelBakery any more). */
@EventBusSubscriber(modid = "conquest", bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public final class ConquestModelEvents {
   private ConquestModelEvents() {
   }

   @SubscribeEvent
   public static void registerLoaders(ModelEvent.RegisterGeometryLoaders event) {
      event.register(LoomGeometry.LOADER_ID, new LoomGeometry.Loader());
   }

   /** Wraps blocks annotated @SpecialOffset(DUPLICATE_DOWN) whose variant has offset_toggle/extension_toggle = true. */
   @SubscribeEvent
   public static void wrapDuplicateDown(ModelEvent.ModifyBakingResult event) {
      for (Map.Entry<ModelResourceLocation, BakedModel> entry : event.getModels().entrySet()) {
         ModelResourceLocation id = entry.getKey();
         String variant = id.variant();
         if ("inventory".equals(variant)) {
            continue;
         }
         if (!(variant.contains("offset_toggle=true") || variant.contains("extension_toggle=true"))) {
            continue;
         }
         Block block = BuiltInRegistries.BLOCK.getOptional(id.id()).orElse(null);
         if (block == null) {
            continue;
         }
         SpecialOffset specialOffset = block.getClass().getAnnotation(SpecialOffset.class);
         if (specialOffset != null && specialOffset.offsetType() == SpecialOffsetType.DUPLICATE_DOWN) {
            entry.setValue(new DuplicateDownBakedModel(entry.getValue()));
         }
      }
   }
}
