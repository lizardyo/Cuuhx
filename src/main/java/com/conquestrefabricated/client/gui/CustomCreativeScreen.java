package com.conquestrefabricated.client.gui;

import net.minecraft.client.gui.screens.inventory.CreativeInventoryListener;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ClickType;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;

public abstract class CustomCreativeScreen<T extends AbstractContainerMenu> extends CustomContainerScreen<T> {
   private boolean clickedOutside = false;
   private CreativeInventoryListener listener;

   public CustomCreativeScreen(T screenContainer, Inventory inv, Component titleIn) {
      super(screenContainer, inv, titleIn);
   }

   protected abstract boolean isContainerSlot(Slot var1);

   protected void sendChanges() {
      if (this.minecraft != null && this.minecraft.player != null) {
         this.minecraft.player.inventoryMenu.broadcastChanges();
      }
   }

   protected void init() {
      super.init();
      if (this.minecraft != null && this.minecraft.player != null) {
         this.minecraft.player.inventoryMenu.removeSlotListener(this.listener);
         this.listener = new CreativeInventoryListener(this.minecraft);
         this.minecraft.player.inventoryMenu.addSlotListener(this.listener);
      }
   }

   public void removed() {
      super.removed();
      if (this.minecraft != null && this.minecraft.player != null && this.minecraft.player.getInventory() != null) {
         this.minecraft.player.inventoryMenu.removeSlotListener(this.listener);
      }
   }

   public boolean mouseClicked(double mx, double my, int button) {
      this.clickedOutside = super.hasClickedOutside(mx, my, this.leftPos, this.topPos, button);
      return super.mouseClicked(mx, my, button);
   }

   @Override
   protected void slotClicked(@Nullable Slot slot, int index, int button, ClickType type) {
      if (this.minecraft != null && this.minecraft.player != null && this.minecraft.gameMode != null) {
         this.onSlotClick(slot, index, button, type);
         if (slot == null && type != ClickType.QUICK_CRAFT) {
            if (!this.menu.getCarried().isEmpty()) {
               if (!this.clickedOutside) {
                  this.menu.setCarried(ItemStack.EMPTY);
                  this.minecraft.player.inventoryMenu.broadcastChanges();
                  return;
               }

               if (button == 0) {
                  this.minecraft.player.drop(this.menu.getCarried(), true);
                  this.minecraft.gameMode.handleCreativeModeItemDrop(this.menu.getCarried());
                  this.menu.setCarried(ItemStack.EMPTY);
               }

               if (button == 1) {
                  ItemStack stack = this.menu.getCarried().split(1);
                  this.minecraft.player.drop(stack, true);
                  this.minecraft.gameMode.handleCreativeModeItemDrop(stack);
               }
            }
         } else {
            boolean quickMove = type == ClickType.QUICK_MOVE;
            type = index == -999 && type == ClickType.PICKUP ? ClickType.THROW : type;
            if (type != ClickType.QUICK_CRAFT && this.isContainerSlot(slot)) {
               ItemStack heldStack = this.menu.getCarried();
               ItemStack slotStack = slot.getItem();
               if (type == ClickType.SWAP) {
                  if (!slotStack.isEmpty() && button >= 0 && button < 9) {
                     ItemStack stack = slotStack.copy();
                     stack.setCount(stack.getMaxStackSize());
                     this.minecraft.player.getInventory().setItem(button, stack);
                     this.minecraft.player.inventoryMenu.broadcastChanges();
                  }

                  return;
               }

               if (type == ClickType.CLONE) {
                  if (this.menu.getCarried().isEmpty() && slot.hasItem()) {
                     ItemStack stack = slot.getItem().copy();
                     stack.setCount(stack.getMaxStackSize());
                     this.menu.setCarried(stack);
                  }

                  return;
               }

               if (type == ClickType.THROW) {
                  if (!slotStack.isEmpty()) {
                     ItemStack stack = slotStack.copy();
                     stack.setCount(button == 0 ? 1 : stack.getMaxStackSize());
                     this.minecraft.player.drop(stack, true);
                     this.minecraft.gameMode.handleCreativeModeItemDrop(stack);
                  }

                  return;
               }

               if (!heldStack.isEmpty() && !slotStack.isEmpty() && heldStack.is(slotStack.getItemHolder()) && ItemStack.isSameItem(heldStack, slotStack)) {
                  if (button == 0) {
                     if (quickMove) {
                        heldStack.setCount(heldStack.getMaxStackSize());
                     } else if (heldStack.getCount() < heldStack.getMaxStackSize()) {
                        heldStack.grow(1);
                     }
                  } else {
                     heldStack.shrink(1);
                  }
               } else if (!slotStack.isEmpty() && heldStack.isEmpty()) {
                  this.menu.setCarried(slotStack.copy());
                  heldStack = this.menu.getCarried();
                  if (quickMove) {
                     heldStack.setCount(heldStack.getMaxStackSize());
                  }
               } else if (button == 0) {
                  this.menu.setCarried(ItemStack.EMPTY);
               } else {
                  this.menu.getCarried().shrink(1);
               }
            } else if (this.menu != null) {
               ItemStack slotStack = slot == null ? ItemStack.EMPTY : this.menu.getSlot(slot.index).getItem();
               this.menu.clicked(slot == null ? index : slot.index, button, type, this.minecraft.player);
               if (AbstractContainerMenu.getQuickcraftHeader(button) == 2) {
                  int start = this.menu.slots.size() - 9;

                  for (int k = 0; k < 9; k++) {
                     this.minecraft.gameMode.handleCreativeModeItemAdd(this.menu.getSlot(start + k).getItem(), 36 + k);
                  }
               } else if (slot != null) {
                  ItemStack itemstack4 = this.menu.getSlot(slot.index).getItem();
                  this.minecraft.gameMode.handleCreativeModeItemAdd(itemstack4, slot.index - this.menu.slots.size() + 9 + 36);
                  int i = 45 + button;
                  if (type == ClickType.SWAP) {
                     this.minecraft.gameMode.handleCreativeModeItemAdd(slotStack, i - this.menu.slots.size() + 9 + 36);
                  } else if (type == ClickType.THROW && !slotStack.isEmpty()) {
                     ItemStack stack = slotStack.copy();
                     stack.setCount(button == 0 ? 1 : stack.getMaxStackSize());
                     this.minecraft.player.drop(stack, true);
                     this.minecraft.gameMode.handleCreativeModeItemDrop(stack);
                  }

                  this.minecraft.player.inventoryMenu.broadcastChanges();
               }
            }
         }
      }
   }
}
