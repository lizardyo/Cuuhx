package com.conquestrefabricated.client.gui.render;

import com.mojang.blaze3d.platform.Lighting;
import com.mojang.blaze3d.platform.GlStateManager.DestFactor;
import com.mojang.blaze3d.platform.GlStateManager.SourceFactor;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.blaze3d.vertex.PoseStack.Pose;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.MultiBufferSource.BufferSource;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.core.Direction;
import net.minecraft.core.Vec3i;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.level.block.state.BlockState;
import org.joml.Matrix3f;
import org.joml.Matrix4f;
import org.joml.Quaternionf;
import org.joml.Vector3f;
import org.joml.Vector4f;
import org.lwjgl.system.MemoryStack;

public class ModelRender {
   private static final int[] lightmap = new int[]{15728880, 15728880, 15728880, 15728880};
   private static final Matrix4f flipX = new Matrix4f().scale(-1.0F, 1.0F, 1.0F);
   private static final Matrix3f flipXNormal = new Matrix3f(flipX);

   public ModelRender() {
   }

   public static void renderModel(PoseStack poseStack, BakedModel model, int x, int y, int color) {
      renderModel(poseStack, ItemDisplayContext.GUI, model, x, y, color);
   }

   public static void renderModel(PoseStack poseStack, ItemDisplayContext transform, BakedModel model, int x, int y, int color) {
      Minecraft.getInstance().getTextureManager().getTexture(TextureAtlas.LOCATION_BLOCKS).setFilter(false, false);
      RenderSystem.setShaderTexture(0, TextureAtlas.LOCATION_BLOCKS);
      RenderSystem.enableBlend();
      RenderSystem.blendFunc(SourceFactor.SRC_ALPHA, DestFactor.ONE_MINUS_SRC_ALPHA);
      RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
      PoseStack posestack = RenderSystem.getModelViewStack();
      posestack.pushPose();
      posestack.translate(x, y, 100.0F);
      posestack.translate(8.0F, 8.0F, 0.0F);
      posestack.scale(1.0F, -1.0F, 1.0F);
      posestack.scale(16.0F, 16.0F, 16.0F);
      RenderSystem.applyModelViewMatrix();
      PoseStack matrixstack = new PoseStack();
      BufferSource buffer = Minecraft.getInstance().renderBuffers().bufferSource();
      model = handleCameraTransforms(matrixstack, model, transform, false);
      boolean flag = !model.usesBlockLight();
      if (flag) {
         Lighting.setupForFlatItems();
      }

      renderModel(matrixstack, RenderType.cutout(), buffer, model, color);
      buffer.endBatch();
      RenderSystem.enableDepthTest();
      if (flag) {
         Lighting.setupFor3DItems();
      }

      posestack.popPose();
      RenderSystem.applyModelViewMatrix();
   }

   public static void renderModel(PoseStack poseStack, BlockState state, BakedModel model, int x, int y, int color) {
      Minecraft.getInstance().getTextureManager().getTexture(TextureAtlas.LOCATION_BLOCKS).setFilter(false, false);
      RenderSystem.enableBlend();
      RenderSystem.blendFunc(SourceFactor.SRC_ALPHA, DestFactor.ONE_MINUS_SRC_ALPHA);
      RenderSystem.setShaderTexture(0, TextureAtlas.LOCATION_BLOCKS);
      RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
      poseStack.pushPose();
      PoseStack posestack = RenderSystem.getModelViewStack();
      posestack.translate(x, y, 100.0F);
      posestack.translate(8.0F, 8.0F, 0.0F);
      posestack.scale(1.0F, -1.0F, 1.0F);
      posestack.scale(16.0F, 16.0F, 16.0F);
      PoseStack matrix = new PoseStack();
      matrix.pushPose();
      matrix.translate(-0.75, 0.0, 0.0);
      float f = (float)Math.toRadians(30.0);
      float g = (float)Math.toRadians(0.0);
      float i = org.joml.Math.sin(0.5F * f);
      float j = org.joml.Math.cos(0.5F * f);
      float k = org.joml.Math.sin(0.5F * g);
      float l = org.joml.Math.cos(0.5F * g);
      float m = org.joml.Math.sin(0.5F * g);
      float n = org.joml.Math.cos(0.5F * g);
      matrix.mulPose(new Quaternionf(30.0F, 30.0F, 0.0F, j * l * n - i * k * m));
      BufferSource buffer = Minecraft.getInstance().renderBuffers().bufferSource();
      Minecraft.getInstance().getBlockRenderer().renderSingleBlock(state, matrix, buffer, 15728880, OverlayTexture.NO_OVERLAY);
      matrix.popPose();
      buffer.endBatch();
      RenderSystem.enableDepthTest();
      poseStack.popPose();
      RenderSystem.applyModelViewMatrix();
   }

   private static void renderModel(PoseStack matrix, RenderType rendertype, BufferSource buffer, BakedModel model, int color) {
      matrix.pushPose();
      matrix.translate(-0.5, -0.5, -0.5);
      VertexConsumer builder = getBuffer(buffer, rendertype, true, false);
      renderModel(model, matrix, builder, color);
      matrix.popPose();
   }

   private static void renderModel(BakedModel modelIn, PoseStack matrix, VertexConsumer buffer, int color) {
      RandomSource random = RandomSource.create();
      long i = 42L;

      for (Direction direction : Direction.values()) {
         random.setSeed(42L);
         renderQuads(matrix, buffer, modelIn.getQuads(null, direction, random), color);
      }

      random.setSeed(42L);
      renderQuads(matrix, buffer, modelIn.getQuads(null, null, random), color);
   }

   private static void renderQuads(PoseStack matrix, VertexConsumer buffer, List<BakedQuad> quads, int color) {
      float r = (color >> 16 & 0xFF) / 255.0F;
      float g = (color >> 8 & 0xFF) / 255.0F;
      float b = (color & 0xFF) / 255.0F;
      Pose entry = matrix.last();

      for (BakedQuad bakedquad : quads) {
         render(buffer, bakedquad, entry, r, g, b, 1.0F);
      }
   }

   private static void render(VertexConsumer bufferIn, BakedQuad quadIn, Pose entry, float red, float green, float blue, float alpha) {
      int[] aint = quadIn.getVertices();
      Vec3i vec3i = quadIn.getDirection().getNormal();
      Vector3f vector3f = new Vector3f(vec3i.getX(), vec3i.getY(), vec3i.getZ());
      Matrix4f matrix4f = entry.pose();
      vector3f.mul(entry.normal());
      int i = 8;
      int j = aint.length / 8;
      MemoryStack memorystack = MemoryStack.stackPush();

      try {
         ByteBuffer bytebuffer = memorystack.malloc(DefaultVertexFormat.BLOCK.getVertexSize());
         IntBuffer intbuffer = bytebuffer.asIntBuffer();

         for (int k = 0; k < j; k++) {
            intbuffer.clear();
            intbuffer.put(aint, k * 8, 8);
            float f = bytebuffer.getFloat(0);
            float f1 = bytebuffer.getFloat(4);
            float f2 = bytebuffer.getFloat(8);
            int l = Math.min(k, lightmap.length - 1);
            int light = applyBakedLighting(lightmap[l], bytebuffer);
            float u = bytebuffer.getFloat(16);
            float v = bytebuffer.getFloat(20);
            Vector4f vector4f = new Vector4f(f, f1, f2, 1.0F);
            vector4f.mul(matrix4f);
            applyBakedNormals(vector3f, bytebuffer, entry.normal());
            bufferIn.vertex(
               vector4f.x, vector4f.y, vector4f.z, red, green, blue, alpha, u, v, OverlayTexture.NO_OVERLAY, light, vector3f.x, vector3f.y, vector3f.z
            );
         }
      } catch (Throwable var26) {
         if (memorystack != null) {
            try {
               memorystack.close();
            } catch (Throwable var25) {
               var26.addSuppressed(var25);
            }
         }

         throw var26;
      }

      if (memorystack != null) {
         memorystack.close();
      }
   }

   private static int applyBakedLighting(int lightmapCoord, ByteBuffer data) {
      int bl = LightTexture.block(lightmapCoord);
      int sl = LightTexture.sky(lightmapCoord);
      int offset = 24;
      int blBaked = Short.toUnsignedInt(data.getShort(offset)) >> 4;
      int slBaked = Short.toUnsignedInt(data.getShort(offset + 2)) >> 4;
      bl = Math.max(bl, blBaked);
      sl = Math.max(sl, slBaked);
      return LightTexture.pack(bl, sl);
   }

   private static void applyBakedNormals(Vector3f generated, ByteBuffer data, Matrix3f normalTransform) {
      byte nx = data.get(28);
      byte ny = data.get(29);
      byte nz = data.get(30);
      if (nx != 0 || ny != 0 || nz != 0) {
         generated.set(nx / 127.0F, ny / 127.0F, nz / 127.0F);
         generated.mul(normalTransform);
      }
   }

   public static VertexConsumer getBuffer(MultiBufferSource buffer, RenderType type, boolean isItemIn, boolean dummy) {
      return buffer.getBuffer(type);
   }

   public static BakedModel handleCameraTransforms(PoseStack poseStack, BakedModel model, ItemDisplayContext cameraTransformType, boolean leftHandHackery) {
      PoseStack stack = new PoseStack();
      model.getTransforms().getTransform(cameraTransformType).apply(true, stack);
      if (!stack.clear()) {
         Matrix4f tMat = stack.last().pose();
         Matrix3f nMat = stack.last().normal();
         if (leftHandHackery) {
            flipX.mul(tMat);
            tMat.mul(flipX);
            flipXNormal.mul(nMat);
            nMat.mul(flipXNormal);
         }

         poseStack.last().pose().mul(tMat);
         poseStack.last().normal().mul(nMat);
      }

      return model;
   }
}
