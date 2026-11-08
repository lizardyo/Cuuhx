package com.conquestrefabricated.core.asset.pack;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;
import net.minecraft.server.packs.PackResources;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.repository.Pack;
import net.minecraft.server.packs.repository.RepositorySource;
import net.minecraft.server.packs.resources.FallbackResourceManager;
import net.minecraft.server.packs.resources.ResourceManager;

public class PackFinder implements RepositorySource {
   private static final Map<PackType, PackFinder> finders = new ConcurrentHashMap<>();
   private final PackType type;
   private final List<VirtualResourcepack> resourcePacks = new LinkedList<>();

   public PackFinder(PackType type) {
      this.type = type;
   }

   public void register(VirtualResourcepack pack) {
      this.resourcePacks.add(pack);
   }

   public void register(ResourceManager resourceManager, Consumer<RepositorySource> packList) {
      Consumer<PackResources> consumer = pack -> {};
      if (resourceManager instanceof FallbackResourceManager) {
         consumer = ((FallbackResourceManager)resourceManager)::push;
      }

      packList.accept(this);
      this.resourcePacks.forEach(consumer);
   }

   public static PackFinder getInstance(PackType type) {
      return finders.computeIfAbsent(type, PackFinder::new);
   }

   public void loadPacks(Consumer<Pack> consumer) {
   }
}
