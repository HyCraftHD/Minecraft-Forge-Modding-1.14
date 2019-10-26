package net.hycrafthd.tutorialmod.container;

import net.hycrafthd.tutorialmod.init.TutorialModContainerTypes;
import net.hycrafthd.tutorialmod.tileentity.InventoryBlockTileEntity;
import net.minecraft.entity.player.*;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;

public class InventoryBlockContainer extends TileEntityContainer<InventoryBlockTileEntity> {
	
	// Client
	public InventoryBlockContainer(int id, PlayerInventory playerInventory, PacketBuffer buffer) {
		super(TutorialModContainerTypes.INVENTORY_BLOCK, id, playerInventory, buffer);
	}
	
	// Server
	public InventoryBlockContainer(int id, PlayerInventory playerInventory, InventoryBlockTileEntity tileEntity) {
		super(TutorialModContainerTypes.INVENTORY_BLOCK, id, playerInventory, tileEntity);
	}
	
	@Override
	public void init() {
		tileEntity.getInventory().ifPresent(handler -> addSlots(handler, 0, 8, 18, 9, 18, 2, 18));
		addPlayerInventory(8, 68);
	}
	
	@Override
	public ItemStack transferStackInSlot(PlayerEntity player, int index) {
		ItemStack stack = ItemStack.EMPTY;
		final Slot slot = inventorySlots.get(index);
		if (slot != null && slot.getHasStack()) {
			final ItemStack slotStack = slot.getStack();
			stack = slotStack.copy();
			if (index < 18) {
				if (!mergeItemStack(slotStack, 18, inventorySlots.size(), true)) {
					return ItemStack.EMPTY;
				}
			} else if (!mergeItemStack(slotStack, 0, 18, false)) {
				return ItemStack.EMPTY;
			}
			
			if (slotStack.isEmpty()) {
				slot.putStack(ItemStack.EMPTY);
			} else {
				slot.onSlotChanged();
			}
		}
		
		return stack;
	}
}
