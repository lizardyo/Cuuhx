package com.conquestrefabricated.core.block;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;

public class BlockStats {
   public final int vanillaBlocks;
   public final int vanillaStates;
   public final int conquestBlocks;
   public final int conquestStates;
   public final int totalBlocks;
   public final int totalStates;

   public BlockStats() {
      int vb = 0;
      int vs = 0;
      int cb = 0;
      int cs = 0;
      int tb = 0;
      int ts = 0;

      for (Block block : BuiltInRegistries.BLOCK) {
         tb++;
         ts += block.getStateDefinition().getPossibleStates().size();
         ResourceLocation name = BuiltInRegistries.BLOCK.getKey(block);
         if (name != null) {
            if (name.getNamespace().equals("minecraft")) {
               vb++;
               vs += block.getStateDefinition().getPossibleStates().size();
            } else if (name.getNamespace().equals("conquest")) {
               cb++;
               cs += block.getStateDefinition().getPossibleStates().size();
            }
         }
      }

      this.vanillaBlocks = vb;
      this.vanillaStates = vs;
      this.conquestBlocks = cb;
      this.conquestStates = cs;
      this.totalBlocks = tb;
      this.totalStates = ts;
   }
}
