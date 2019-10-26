package net.hycrafthd.tutorialmod.container;

import net.hycrafthd.tutorialmod.init.TutorialModContainerTypes;
import net.hycrafthd.tutorialmod.tileentity.InventoryBlockTileEntity;
import net.minecraft.entity.player.PlayerInventory;
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
	
}
