package com.conquestrefabricated.client.gui.dependency;

import java.io.IOException;
import net.neoforged.fml.ModList;
import net.minecraft.client.Minecraft;
import net.minecraft.server.packs.repository.Pack;

public enum DependencyType {
   UNKNOWN {
      @Override
      public boolean isAvailable(Dependency dependency) {
         return true;
      }
   },
   MOD {
      @Override
      public boolean isAvailable(Dependency dependency) {
         return ModList.get().isLoaded(dependency.getId());
      }
   },
   LIB {
      @Override
      public boolean isAvailable(Dependency dependency) {
         try {
            Class.forName(dependency.getId());
            return true;
         } catch (ClassNotFoundException e) {
            return false;
         }
      }
   },
   RESOURCEPACK {
      @Override
      public boolean isAvailable(Dependency dependency) {
         for (Pack pack : Minecraft.getInstance().getResourcePackRepository().getSelectedPacks()) {
            try {
               Object packId = pack.open().getMetadataSection(PackIdDeserializer.INSTANCE);
               if (dependency.getId().equalsIgnoreCase(packId + "")) {
                  return true;
               }
            } catch (IOException var5) {
            }
         }

         return false;
      }
   };

   DependencyType() {
   }

   public abstract boolean isAvailable(Dependency var1);

   static DependencyType of(String name) {
      for (DependencyType type : values()) {
         if (type.name().equalsIgnoreCase(name)) {
            return type;
         }
      }

      return UNKNOWN;
   }
}
