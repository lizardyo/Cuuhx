package com.conquestrefabricated.content.items.init.forge;

import com.conquestrefabricated.api.painting.vanilla.VanillaArt;
import com.conquestrefabricated.api.painting.vanilla.VanillaPainting;
import com.conquestrefabricated.content.entities.painting.ModPainting;
import com.conquestrefabricated.content.entities.painting.PaintingFactory;
import com.conquestrefabricated.content.entities.painting.PaintingItem;
import com.conquestrefabricated.content.entities.painting.art.ModArt;
import com.conquestrefabricated.content.items.init.ModItems;
import com.conquestrefabricated.core.util.log.Log;
import java.util.List;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Item.Properties;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.EventBusSubscriber.Bus;
import net.neoforged.neoforge.registries.RegisterEvent;
import org.jetbrains.annotations.Nullable;

@EventBusSubscriber(bus = Bus.MOD)
public class ItemCommonInit {
   public ItemCommonInit() {
   }

   @SubscribeEvent
   public static void init(RegisterEvent event) {
      event.register(Registries.ITEM, registry -> {
         Log.info("Registering items");
         ModItems.malletItem = new Item(new Properties().stacksTo(1)) {
            public void appendHoverText(ItemStack stack, @Nullable Level world, List<Component> tooltip, TooltipFlag context) {
               tooltip.add(Component.translatable("tooltip.conquest.mallet_item"));
            }
         };
         PaintingItem painting1 = new PaintingItem("painting", ModPainting::fromName, ModArt::fromName, PaintingFactory.MOD);
         registry.register(ResourceLocation.fromNamespaceAndPath("conquest", "painting"), painting1);
         PaintingItem painting2 = new PaintingItem("vanilla_painting", VanillaPainting::fromName, VanillaArt::fromName, PaintingFactory.VANILLA);
         registry.register(ResourceLocation.fromNamespaceAndPath("conquest", "vanilla_painting"), painting2);
         registry.register(ResourceLocation.fromNamespaceAndPath("conquest", "mallet_item"), ModItems.malletItem);
      });
   }
}
