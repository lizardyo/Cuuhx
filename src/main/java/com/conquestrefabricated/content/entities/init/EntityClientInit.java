package com.conquestrefabricated.content.entities.init;

import com.conquestrefabricated.content.entities.EntityTypes;
import com.conquestrefabricated.content.entities.painting.PaintingEntityType;
import com.conquestrefabricated.content.entities.painting.render.PaintingRenderer;
import com.conquestrefabricated.content.entities.painting.render.VanillaPaintingRenderer;
import com.conquestrefabricated.content.entities.seat.SeatRenderer;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.world.entity.EntityType;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.EventBusSubscriber.Bus;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;

@EventBusSubscriber(value = Dist.CLIENT, bus = Bus.MOD)
public class EntityClientInit {
   public EntityClientInit() {
   }

   @SubscribeEvent
   public static void setup(FMLClientSetupEvent event) {
      EntityRenderers.register(EntityType.PAINTING, VanillaPaintingRenderer::new);
      EntityRenderers.register(PaintingEntityType.PAINTING, PaintingRenderer::new);
      EntityRenderers.register(EntityTypes.SEAT, SeatRenderer::new);
   }
}
