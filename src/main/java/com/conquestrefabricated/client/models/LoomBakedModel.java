package com.conquestrefabricated.client.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.client.model.BakedModelWrapper;
import net.neoforged.neoforge.client.model.data.ModelData;
import net.neoforged.neoforge.client.model.data.ModelProperty;
import org.jetbrains.annotations.Nullable;

/**
 * NeoForge replacement for the old Fabric-Renderer-API version.
 * The wrapped (base) model is the "weave": its texture is swapped for the sprite matching the loom's current
 * product. The extra model is the "threaded" overlay and is added unchanged.
 * The product string reaches this class through ModelData (see LoomBlockEntity#getModelData).
 */
public class LoomBakedModel extends BakedModelWrapper<BakedModel> {
   public static final ModelProperty<String> PRODUCT = new ModelProperty<>();
   private static final int STRIDE = 8;

   private final BakedModel extraModel;
   private final TextureAtlasSprite[] sprites;
   private final Map<BakedQuad, BakedQuad>[] remapCache;

   @SuppressWarnings("unchecked")
   public LoomBakedModel(BakedModel baseModel, BakedModel extraModel, TextureAtlasSprite[] sprites) {
      super(baseModel);
      this.extraModel = extraModel;
      this.sprites = sprites;
      this.remapCache = new Map[sprites.length];
      for (int i = 0; i < sprites.length; i++) {
         this.remapCache[i] = new ConcurrentHashMap<>();
      }
   }

   @Override
   public List<BakedQuad> getQuads(@Nullable BlockState state, @Nullable Direction side, RandomSource rand, ModelData data, @Nullable RenderType renderType) {
      String product = data.get(PRODUCT);
      int index = product == null ? 0 : this.getTextureVariant(product);
      if (index < 0 || index >= this.sprites.length || this.sprites[index] == null) {
         index = 0;
      }
      List<BakedQuad> out = new ArrayList<>();
      for (BakedQuad quad : this.originalModel.getQuads(state, side, rand, data, renderType)) {
         if (this.sprites[index] == null) {
            out.add(quad);
         } else {
            final int idx = index;
            out.add(this.remapCache[idx].computeIfAbsent(quad, q -> remap(q, this.sprites[idx])));
         }
      }
      out.addAll(this.extraModel.getQuads(state, side, rand, data, renderType));
      return out;
   }

   /** Re-maps the quad's UVs from its original sprite onto the target sprite (same relative position). */
   static BakedQuad remap(BakedQuad quad, TextureAtlasSprite target) {
      TextureAtlasSprite src = quad.getSprite();
      float sw = src.getU1() - src.getU0();
      float sh = src.getV1() - src.getV0();
      if (sw == 0.0F || sh == 0.0F) {
         return quad;
      }
      float tw = target.getU1() - target.getU0();
      float th = target.getV1() - target.getV0();
      int[] v = quad.getVertices().clone();
      for (int i = 0; i < 4; i++) {
         float u = Float.intBitsToFloat(v[i * STRIDE + 4]);
         float vv = Float.intBitsToFloat(v[i * STRIDE + 5]);
         float nu = (u - src.getU0()) / sw;
         float nv = (vv - src.getV0()) / sh;
         v[i * STRIDE + 4] = Float.floatToRawIntBits(target.getU0() + nu * tw);
         v[i * STRIDE + 5] = Float.floatToRawIntBits(target.getV0() + nv * th);
      }
      return new BakedQuad(v, quad.getTintIndex(), quad.getDirection(), target, quad.isShade(), quad.hasAmbientOcclusion());
   }

   private int getTextureVariant(String productData) {
      return switch (productData) {
         case "conquest:red_canvas" -> 1;
         case "conquest:black_canvas" -> 2;
         case "conquest:gray_canvas" -> 3;
         case "conquest:light_gray_canvas" -> 4;
         case "conquest:white_canvas" -> 5;
         case "conquest:brown_canvas" -> 6;
         case "conquest:yellow_canvas" -> 7;
         case "conquest:orange_canvas" -> 8;
         case "conquest:pink_canvas" -> 9;
         case "conquest:magenta_canvas" -> 10;
         case "conquest:purple_canvas" -> 11;
         case "conquest:blue_canvas" -> 12;
         case "conquest:light_blue_canvas" -> 13;
         case "conquest:cyan_canvas" -> 14;
         case "conquest:green_canvas" -> 15;
         case "conquest:lime_canvas" -> 16;
         case "conquest:baotuo_rug" -> 17;
         case "conquest:berber_rug" -> 18;
         case "conquest:black_persian_rug" -> 19;
         case "conquest:blue_nain_rug" -> 20;
         case "conquest:brown_oriental_carpet" -> 21;
         case "conquest:celtic_knot_rug" -> 22;
         case "conquest:kashmiri_carpet" -> 23;
         case "conquest:kazakh_rug" -> 24;
         case "conquest:kilim_rug" -> 25;
         case "conquest:nahavand_rug" -> 26;
         case "conquest:red_and_blue_sarouk_rug" -> 27;
         case "conquest:red_oriental_carpet" -> 28;
         case "conquest:red_pazyryk_rug" -> 29;
         case "conquest:shirishabad_rug" -> 30;
         case "conquest:william_morris_rug" -> 31;
         case "conquest:yellow_red_persian_rug" -> 32;
         default -> 0;
      };
   }
}
