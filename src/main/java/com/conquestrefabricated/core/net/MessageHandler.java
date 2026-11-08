package com.conquestrefabricated.core.net;

import net.minecraft.network.FriendlyByteBuf;

public interface MessageHandler<T> {
   T decode(FriendlyByteBuf var1);

   void encode(T var1, FriendlyByteBuf var2);
}
