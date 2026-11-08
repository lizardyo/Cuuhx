package com.conquestrefabricated.client.gui.search;

import com.conquestrefabricated.client.utils.CreativeUtils;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.BufferBuilder;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.events.GuiEventListener;
import net.minecraft.client.gui.narration.NarratableEntry;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.client.gui.narration.NarratableEntry.NarrationPriority;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.world.item.ItemStack;

public class SearchList implements GuiEventListener, NarratableEntry {
   private static final int slotSize = 20;
   private static final int stackSize = 16;
   private static final int stackPad = 2;
   public float x;
   public float y;
   public float scale = 1.0F;
   private int count = 0;
   private int maxRow = 0;
   private final int rows;
   private final int columns;
   private final SearchList.Slot[] slots;
   private SearchList.Slot hovered = null;

   public SearchList(int columns, int rows) {
      this.columns = columns;
      this.rows = rows;
      this.slots = new SearchList.Slot[columns * rows];

      for (int r = 0; r < rows; r++) {
         for (int c = 0; c < columns; c++) {
            int i = c + r * columns;
            this.slots[i] = new SearchList.Slot(c, r);
         }
      }
   }

   public int getHeight() {
      return this.count == 0 ? 0 : Math.round(this.rows * 20 * this.scale);
   }

   public int getWidth() {
      return Math.round(this.columns * 20 * this.scale);
   }

   public int size() {
      return this.count;
   }

   public int maxSize() {
      return this.rows * this.columns;
   }

   public synchronized void add(ItemStack stack) {
      SearchList.Slot slot = this.slots[this.count++];
      slot.stack = stack;
      this.maxRow = slot.row;
   }

   public synchronized void clear() {
      this.count = 0;
      this.maxRow = 0;

      for (SearchList.Slot slot : this.slots) {
         slot.stack = ItemStack.EMPTY;
      }
   }

   public void render(GuiGraphics drawContext, int mx, int my, int width, int height, float tick) {
      this.hovered = null;

      for (SearchList.Slot slot : this.slots) {
         if (slot.col == 0 && slot.stack.isEmpty() || !slot.fitsOnScreen(width, height)) {
            break;
         }

         slot.render(drawContext.pose(), mx, my);
      }

      if (this.hovered != null) {
         String name = this.hovered.stack.getDisplayName().getString();
         int nameWidth = Minecraft.getInstance().font.width(name);
         int left = width / 2 - nameWidth / 2;
         drawContext.drawString(Minecraft.getInstance().font, name, left, height - 32, 16777215, true);
      }
   }

   public boolean mouseClicked(double mx, double my, int button) {
      for (SearchList.Slot slot : this.slots) {
         if (slot.click(mx, my)) {
            return true;
         }
      }

      return false;
   }

   public void setFocused(boolean bl) {
   }

   public boolean isFocused() {
      return false;
   }

   public void updateNarration(NarrationElementOutput p_169152_) {
   }

   public NarrationPriority narrationPriority() {
      return NarrationPriority.NONE;
   }

   public boolean isActive() {
      return super.isActive();
   }

   private class Slot extends GuiGraphics {
      private final int col;
      private final int row;
      private ItemStack stack = ItemStack.EMPTY;

      private Slot(int col, int row) {
         super(Minecraft.getInstance(), MultiBufferSource.immediate(new BufferBuilder(256)));
         this.row = row;
         this.col = col;
      }

      private float left() {
         return SearchList.this.x + this.col * 20 * SearchList.this.scale;
      }

      private float top() {
         return SearchList.this.y + this.row * 20 * SearchList.this.scale;
      }

      private boolean mouseOver(double mx, double my) {
         float l = this.left();
         float t = this.top();
         float size = 20.0F * SearchList.this.scale;
         return mx >= l && mx <= l + size && my >= t && my <= t + size;
      }

      private boolean fitsOnScreen(int width, int height) {
         float right = this.left() + 20.0F * SearchList.this.scale;
         float bottom = this.top() + 20.0F * SearchList.this.scale;
         return right < width && bottom < height - 32;
      }

      private void render(PoseStack matrixStack, double mx, double my) {
         float top = this.top();
         float left = this.left();
         PoseStack posestack = RenderSystem.getModelViewStack();
         posestack.pushPose();
         posestack.translate(left, top, 0.0F);
         posestack.scale(SearchList.this.scale, SearchList.this.scale, SearchList.this.scale);
         RenderSystem.applyModelViewMatrix();
         if (!this.stack.isEmpty()) {
            this.renderItem(this.stack, 2, 2);
         }

         posestack.popPose();
         RenderSystem.applyModelViewMatrix();
         matrixStack.pushPose();
         matrixStack.translate(left, top, 0.0F);
         matrixStack.scale(SearchList.this.scale, SearchList.this.scale, SearchList.this.scale);
         this.fill(0, 0, 20, 20, 1426063360);
         if (this.mouseOver(mx, my)) {
            SearchList.this.hovered = this;
            this.fill(1, 1, 19, 19, 1436129689);
         }

         matrixStack.popPose();
         RenderSystem.enableDepthTest();
      }

      private boolean click(double mx, double my) {
         if (this.mouseOver(mx, my)) {
            LocalPlayer player = Minecraft.getInstance().player;
            if (player == null) {
               return false;
            }

            if (CreativeUtils.addItemStack(this.stack.copy(), true)) {
               Minecraft.getInstance().setScreen(null);
               return true;
            }
         }

         return false;
      }
   }
}
