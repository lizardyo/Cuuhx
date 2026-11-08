package com.conquestrefabricated.core.client.render.forge;

import com.conquestrefabricated.core.client.render.RenderLayerHelper;
import com.conquestrefabricated.core.util.RenderLayer;
import com.conquestrefabricated.core.util.log.Log;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Fluid;

public class RenderLayerHelperImpl {
   public RenderLayerHelperImpl() {
   }

   public static void register(Block block, RenderLayer layer) {
      if (layer != RenderLayer.SOLID && layer != RenderLayer.UNDEFINED) {
         RenderType type = RenderLayerHelper.getType(layer);
         if (type == null) {
            return;
         }

         ItemBlockRenderTypes.setRenderLayer(block, type);
         Log.debug("Registered render type for Block: {}={}", block.getName().toString(), type);
      }
   }

   public static void register(Fluid fluid, RenderLayer layer) {
      if (layer != RenderLayer.SOLID && layer != RenderLayer.UNDEFINED) {
         RenderType type = RenderLayerHelper.getType(layer);
         if (type == null) {
            return;
         }

         ItemBlockRenderTypes.setRenderLayer(fluid, type);
         Log.debug("Registered render type for Fluid: {}={}", BuiltInRegistries.FLUID.getKey(fluid), type);
      }
   }
}
