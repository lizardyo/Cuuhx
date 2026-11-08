package com.conquestrefabricated.content.entities.init;

import com.conquestrefabricated.content.entities.EntityTypes;
import com.conquestrefabricated.content.entities.painting.ModPainting;
import com.conquestrefabricated.content.entities.painting.PaintingEntityType;
import java.util.stream.IntStream;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.EventBusSubscriber.Bus;
import net.neoforged.neoforge.registries.RegisterEvent;

@EventBusSubscriber(bus = Bus.MOD)
public class EntityCommonInit {
   public EntityCommonInit() {
   }

   @SubscribeEvent
   public static void entities(RegisterEvent event) {
      event.register(Registries.ENTITY_TYPE, registry -> registerPaintings(registry));
   }

   private static void registerPaintings(net.neoforged.neoforge.registries.RegisterEvent.RegisterHelper<EntityType<?>> registry) {
      IntStream.range(0, 10).forEach(i -> ModPainting.register("painting" + i));
      EntityTypes.entityTypesInit();
      PaintingEntityType.paintingEntityTypesInit();
      registry.register(ResourceLocation.fromNamespaceAndPath("conquest", "painting"), PaintingEntityType.PAINTING);
      registry.register(ResourceLocation.fromNamespaceAndPath("conquest", "seat"), EntityTypes.SEAT);
   }
}
