package com.conquestrefabricated.client.gui.palette;

import com.conquestrefabricated.client.gui.AbstractContainer;
import com.conquestrefabricated.client.gui.Hotbar;
import com.conquestrefabricated.client.gui.palette.component.PaletteSettings;
import com.conquestrefabricated.client.gui.palette.component.PaletteSlot;
import com.conquestrefabricated.client.gui.palette.component.Style;
import com.conquestrefabricated.client.gui.palette.shape.Bounds;
import com.conquestrefabricated.client.gui.palette.shape.FloatMath;
import com.conquestrefabricated.client.gui.palette.shape.Point;
import com.conquestrefabricated.client.gui.palette.shape.Polygon;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;

public class PaletteContainer extends AbstractContainer {
   public static final MenuType<PaletteContainer> TYPE = new MenuType(PaletteContainer::new, FeatureFlagSet.of());
   private static final ResourceLocation BACKGROUND = ResourceLocation.parse("conquest:textures/gui/picker/slot.png");
   public static final int RADIUS = 65;
   private static final int EDGES = 6;
   private static final int PADDING = 40;
   private static final int ANGLE_OFFSET = -90;
   private final int radialCount;
   private final Style centerStyle;
   private final Style radialStyle;
   private final Style draggedStyle;
   private final Container paletteInventory;
   private final Hotbar hotbar;

   private PaletteContainer(int id, Container inventory) {
      super(TYPE, id);
      this.radialCount = 0;
      this.hotbar = new Hotbar(inventory);
      this.paletteInventory = inventory;
      this.centerStyle = Style.center(BACKGROUND);
      this.draggedStyle = Style.center(BACKGROUND);
      this.radialStyle = Style.radial(0, BACKGROUND);
   }

   public PaletteContainer(Container inventory, Container palette) {
      super(TYPE, 0);
      this.radialCount = palette.getContainerSize() - 1;
      this.hotbar = new Hotbar(inventory);
      this.paletteInventory = palette;
      this.centerStyle = Style.center(BACKGROUND);
      this.draggedStyle = Style.center(BACKGROUND);
      this.radialStyle = Style.radial(this.radialCount, BACKGROUND);
   }

   public void updateStyle(PaletteSettings settings) {
      this.draggedStyle.highlightScale = settings.highlightScale;
      this.radialStyle.highlightScale = settings.highlightScale;
      this.centerStyle.highlightScale = settings.highlightScale;
   }

   public Style getDraggedStyle() {
      return this.draggedStyle;
   }

   public void init(AbstractContainerScreen<?> screen) {
      int centerX = 109;
      int centerY = 109;
      this.slots.clear();
      this.addSlot(new PaletteSlot(this.paletteInventory, this.centerStyle, Bounds.NONE, 0, centerX, centerY));
      Polygon polygon = new Polygon(6, 65, 40, 40);
      polygon.init(centerX, centerY);
      float spacing = 360.0F / this.radialCount;
      float halfSpacing = spacing / 2.0F;

      for (int slotIndex = 1; slotIndex < this.paletteInventory.getContainerSize(); slotIndex++) {
         int posIndex = slotIndex - 1;
         float angle = FloatMath.clampAngle(posIndex * spacing + -90.0F);
         Point pos = polygon.getPosition(angle);
         Bounds bounds = polygon.getBounds(angle, halfSpacing);
         this.addSlot(new PaletteSlot(this.paletteInventory, this.radialStyle, bounds, slotIndex, pos.x, pos.y));
         if (slotIndex >= this.paletteInventory.getContainerSize()) {
            throw new UnsupportedOperationException();
         }
      }

      int screenBottom = screen.height - screen.topPos;
      int top = screenBottom - this.hotbar.getHeight();
      this.hotbar.addTo(this, centerX, top);
   }

   public ItemStack quickMoveStack(Player player, int index) {
      if (index >= this.slots.size() - 9 && index < this.slots.size()) {
         Slot slot = (Slot)this.slots.get(index);
         if (slot != null && slot.hasItem()) {
            slot.setByPlayer(ItemStack.EMPTY);
         }
      }

      return ItemStack.EMPTY;
   }

   public boolean canDragTo(Slot slot) {
      return slot.container != this.paletteInventory;
   }

   public boolean canTakeItemForPickAll(ItemStack stack, Slot slot) {
      return slot.container != this.paletteInventory;
   }

   public boolean stillValid(Player player) {
      return player.isCreative();
   }

   public Hotbar getHotbar() {
      return this.hotbar;
   }

   public Container getPaletteInventory() {
      return this.paletteInventory;
   }

   public void visitHotbar(Consumer<Slot> consumer) {
      for (int i = this.slots.size() - 9; i < this.slots.size(); i++) {
         consumer.accept(this.getSlot(i));
      }
   }

   public void visitCenter(Consumer<PaletteSlot> consumer) {
      PaletteSlot slot = (PaletteSlot)this.getSlot(0);
      consumer.accept(slot);
   }

   public void visitRadius(int mouseX, int mouseY, BiConsumer<PaletteSlot, Float> consumer) {
      Slot closest = this.getClosestSlot(mouseX, mouseY, false);
      if (closest != null) {
         int index = closest.index - 1;
         this.visitRadialSlot(index, 0, consumer);
         int i = 1;

         for (int visited = 0; visited < this.radialCount && visited + 1 < this.radialCount; visited += 2) {
            this.visitRadialSlot(index - i, i, consumer);
            if (visited + 2 >= this.radialCount) {
               break;
            }

            this.visitRadialSlot(index + i, i, consumer);
            i++;
         }
      }
   }

   private void visitRadialSlot(int index, int relativeIndex, BiConsumer<PaletteSlot, Float> consumer) {
      int slotIndex = this.wrapSlotIndex(index);
      float depth = this.getRelativeDepth(relativeIndex);
      PaletteSlot slot = (PaletteSlot)this.getSlot(slotIndex + 1);
      consumer.accept(slot, depth);
   }

   private int wrapSlotIndex(int index) {
      if (index < 0) {
         return this.radialCount + index;
      } else {
         return index >= this.radialCount ? index - this.radialCount : index;
      }
   }

   private float getRelativeDepth(float i) {
      return i == 0.0F ? 1.0F : 0.0F;
   }

   @Nullable
   public Slot getClosestSlot(int mouseX, int mouseY, boolean all) {
      Slot nearest = null;
      int dist2 = Integer.MAX_VALUE;

      for (Slot slot : this.slots) {
         if (all || slot.getContainerSlot() != 0 && slot.container == this.paletteInventory) {
            int dx = slot.x + 8 - mouseX;
            int dy = slot.y + 8 - mouseY;
            int d2 = dx * dx + dy * dy;
            if (nearest == null || d2 < dist2) {
               nearest = slot;
               dist2 = d2;
            }
         }
      }

      return nearest;
   }
}
