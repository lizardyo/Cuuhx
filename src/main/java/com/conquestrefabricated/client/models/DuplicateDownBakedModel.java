package com.conquestrefabricated.client.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.client.model.BakedModelWrapper;
import net.neoforged.neoforge.client.model.data.ModelData;
import org.jetbrains.annotations.Nullable;

/**
 * NeoForge replacement for the old Fabric-Renderer-API version.
 * Renders the wrapped model, plus a second copy (every face except DOWN) moved down one block with a
 * slight horizontal squeeze. Same maths as the original translateDown().
 */
public class DuplicateDownBakedModel extends BakedModelWrapper<BakedModel> {
   /** ints per vertex in DefaultVertexFormat.BLOCK: x,y,z,color,u,v,light,normal */
   private static final int STRIDE = 8;
   private final Map<BakedQuad, BakedQuad> cache = new ConcurrentHashMap<>();

   public DuplicateDownBakedModel(BakedModel baseModel) {
      super(baseModel);
   }

   @Override
   public List<BakedQuad> getQuads(@Nullable BlockState state, @Nullable Direction side, RandomSource rand, ModelData data, @Nullable RenderType renderType) {
      List<BakedQuad> base = this.originalModel.getQuads(state, side, rand, data, renderType);
      if (base.isEmpty()) {
         return base;
      }
      List<BakedQuad> out = new ArrayList<>(base.size() * 2);
      out.addAll(base);
      for (BakedQuad quad : base) {
         if (quad.getDirection() != Direction.DOWN) {
            out.add(this.cache.computeIfAbsent(quad, DuplicateDownBakedModel::translateDown));
         }
      }
      return out;
   }

   static BakedQuad translateDown(BakedQuad quad) {
      int[] v = quad.getVertices().clone();
      float[] xs = new float[4];
      float[] ys = new float[4];
      float[] zs = new float[4];
      for (int i = 0; i < 4; i++) {
         xs[i] = Float.intBitsToFloat(v[i * STRIDE]);
         ys[i] = Float.intBitsToFloat(v[i * STRIDE + 1]);
         zs[i] = Float.intBitsToFloat(v[i * STRIDE + 2]);
      }
      switch (quad.getDirection()) {
         case NORTH:
            xs[1] += 0.01F; zs[1] -= 0.01F;
            xs[2] -= 0.011F; zs[2] -= 0.011F;
            break;
         case SOUTH:
            xs[1] -= 0.01F; zs[1] += 0.01F;
            xs[2] += 0.011F; zs[2] += 0.011F;
            break;
         case EAST:
            xs[1] += 0.011F; zs[1] += 0.011F;
            xs[2] += 0.01F; zs[2] -= 0.01F;
            break;
         case WEST:
            xs[1] -= 0.011F; zs[1] -= 0.011F;
            xs[2] -= 0.01F; zs[2] += 0.01F;
            break;
         default:
            break;
      }
      for (int i = 0; i < 4; i++) {
         v[i * STRIDE] = Float.floatToRawIntBits(xs[i]);
         v[i * STRIDE + 1] = Float.floatToRawIntBits(ys[i] - 1.0F);
         v[i * STRIDE + 2] = Float.floatToRawIntBits(zs[i]);
      }
      return new BakedQuad(v, quad.getTintIndex(), quad.getDirection(), quad.getSprite(), quad.isShade(), quad.hasAmbientOcclusion());
   }
}
