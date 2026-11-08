package com.conquestrefabricated.client.gui.config.forge;

import com.conquestrefabricated.RefabricatedMod;
import com.conquestrefabricated.client.gui.config.ConquestConfig;
import java.util.function.Supplier;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent.Context;

public class ConfigSyncPacket {
   private final boolean plantSlowness;
   private final boolean plantBreaking;
   private final boolean passThroughLeaves;

   public ConfigSyncPacket(boolean plantSlowness, boolean plantBreaking, boolean passThroughLeaves) {
      this.plantSlowness = plantSlowness;
      this.plantBreaking = plantBreaking;
      this.passThroughLeaves = passThroughLeaves;
   }

   public static void encode(ConfigSyncPacket packet, FriendlyByteBuf buf) {
      buf.writeBoolean(packet.plantSlowness);
      buf.writeBoolean(packet.plantBreaking);
      buf.writeBoolean(packet.passThroughLeaves);
   }

   public static ConfigSyncPacket decode(FriendlyByteBuf buf) {
      return new ConfigSyncPacket(buf.readBoolean(), buf.readBoolean(), buf.readBoolean());
   }

   public static void handle(ConfigSyncPacket packet, Supplier<Context> supplier) {
      Context ctx = supplier.get();
      ctx.enqueueWork(() -> {
         if (!Minecraft.getInstance().isLocalServer()) {
            RefabricatedMod.localPlantSlowness = (Boolean)ConquestConfig.INSTANCE.plantSlowness.get();
            RefabricatedMod.localPlantBreaking = (Boolean)ConquestConfig.INSTANCE.plantBreaking.get();
            RefabricatedMod.localPassThroughLeaves = (Boolean)ConquestConfig.INSTANCE.passThroughLeaves.get();
            ConquestConfig.INSTANCE.plantSlowness.set(packet.plantSlowness);
            ConquestConfig.INSTANCE.plantBreaking.set(packet.plantBreaking);
            ConquestConfig.INSTANCE.passThroughLeaves.set(packet.passThroughLeaves);
         }
      });
      ctx.setPacketHandled(true);
   }
}
