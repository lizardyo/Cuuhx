package com.conquestrefabricated.content.entities.painting;

import com.conquestrefabricated.api.painting.art.Art;
import com.conquestrefabricated.api.painting.vanilla.VanillaArt;
import com.conquestrefabricated.content.entities.painting.art.ArtType;
import com.conquestrefabricated.content.entities.painting.art.ModArt;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.decoration.HangingEntity;
import net.minecraft.world.entity.decoration.Painting;
import net.minecraft.world.entity.decoration.PaintingVariant;
import net.minecraft.world.level.Level;

public interface PaintingFactory<T extends HangingEntity> {
   PaintingFactory<EntityPainting> MOD = (world, pos, side, typeName, artName) -> {
      ModPainting type = ModPainting.fromName(typeName);
      Art<ArtType> art = ModArt.fromName(artName);
      return type != null && art != null ? new EntityPainting(world, pos, side, type, art.getReference()) : null;
   };
   PaintingFactory<Painting> VANILLA = (world, pos, side, typeName, artName) -> {
      Art<PaintingVariant> art = VanillaArt.fromName(artName);
      if (art == null) {
         return new Painting(world, pos, side, BuiltInRegistries.PAINTING_VARIANT.createIntrusiveHolder(art.getReference()));
      }

      Painting painting = new Painting(
         world, pos, side, (Holder)BuiltInRegistries.PAINTING_VARIANT.getHolder(BuiltInRegistries.PAINTING_VARIANT.getId(art.getReference())).get()
      );
      painting.setPos(pos.getX(), pos.getY(), pos.getZ());
      return painting;
   };

   T create(Level var1, BlockPos var2, Direction var3, String var4, String var5);
}
