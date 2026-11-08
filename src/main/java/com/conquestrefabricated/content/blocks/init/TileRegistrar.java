package com.conquestrefabricated.content.blocks.init;

import com.conquestrefabricated.content.blocks.tileentity.TileEntityTypes;
import com.conquestrefabricated.core.util.log.Log;

public class TileRegistrar {
   public TileRegistrar() {
   }

   public static void entities() {
      Log.info("Registering TileEntities");
      TileEntityTypes.init();
   }
}
