package com.conquestrefabricated;

import java.nio.file.Path;
import java.util.Optional;
import net.minecraft.network.chat.Component;
import net.minecraft.server.packs.PackLocationInfo;
import net.minecraft.server.packs.PackResources;
import net.minecraft.server.packs.PackSelectionConfig;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.PathPackResources;
import net.minecraft.server.packs.repository.Pack;
import net.minecraft.server.packs.repository.PackSource;
import net.minecraft.server.packs.repository.Pack.Position;
import net.minecraft.server.packs.repository.Pack.ResourcesSupplier;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModList;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.EventBusSubscriber.Bus;
import net.neoforged.neoforge.event.AddPackFindersEvent;

@EventBusSubscriber(modid = "conquest", value = Dist.CLIENT, bus = Bus.MOD)
public class ModBuiltinRP {
   public ModBuiltinRP() {
   }

   @SubscribeEvent
   public static void onGatherData(AddPackFindersEvent event) {
      if (event.getPackType() == PackType.CLIENT_RESOURCES) {
         ModList.get()
            .getModContainerById("conquest")
            .ifPresent(
               modContainer -> event.addRepositorySource(
                  packConsumer -> {
                        packConsumer.accept(createPack("rp_crrp", "CRRP"));
                  }
               )
            );
         ModList.get()
            .getModContainerById("jei")
            .ifPresent(
               modContainer -> event.addRepositorySource(
                  packConsumer -> {
                        packConsumer.accept(createPack("conquest_jei", "Conquest JEI Compat"));
                  }
               )
            );
         ModList.get()
            .getModContainerById("roughlyenoughitems")
            .ifPresent(
               modContainer -> event.addRepositorySource(
                  packConsumer -> {
                        packConsumer.accept(createPack("conquest_rei", "Conquest REI Compat"));
                  }
               )
            );
      }
   }

   private static Pack createPack(String packId, String title) {
      String fullPackId = "conquest:" + packId;
      Path resourcePath = ModList.get().getModFileById("conquest").getFile().findResource(new String[]{"resourcepacks/" + packId});
      PackLocationInfo location = new PackLocationInfo(fullPackId, Component.literal(title), PackSource.BUILT_IN, Optional.empty());
      ResourcesSupplier resources = new ResourcesSupplier() {
         @Override
         public PackResources openPrimary(PackLocationInfo info) {
            return new PathPackResources(info, resourcePath);
         }

         @Override
         public PackResources openFull(PackLocationInfo info, Pack.Metadata metadata) {
            return new PathPackResources(info, resourcePath);
         }
      };
      return Pack.readMetaAndCreate(location, resources, PackType.CLIENT_RESOURCES, new PackSelectionConfig(true, Position.TOP, false));
   }
}
