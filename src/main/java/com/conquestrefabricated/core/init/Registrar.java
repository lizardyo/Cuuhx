package com.conquestrefabricated.core.init;

import com.conquestrefabricated.core.block.data.BlockData;

public class Registrar {
   public Registrar() {
   }

   public static void register(BlockData data, Object value, Object registry) {
      // NeoForge registry wiring is migrated in the event-driven registration phase.
      // Keeping this helper as a no-op avoids Forge-only APIs while the port is being completed.
   }
}
