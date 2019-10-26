package net.hycrafthd.tutorialmod.container;

import net.hycrafthd.tutorialmod.tileentity.InventoryBlockTileEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.network.PacketBuffer;

public class InventoryBlockContainer extends TileEntityContainer<InventoryBlockTileEntity> {
	
	// Client
	public InventoryBlockContainer(ContainerType<?> type, int id, PlayerInventory playerInventory, PacketBuffer buffer) {
		super(type, id, playerInventory, buffer);
	}
	
	// Server
	public InventoryBlockContainer(ContainerType<?> type, int id, PlayerInventory playerInventory, InventoryBlockTileEntity tileEntity) {
		super(type, id, playerInventory, tileEntity);
	}
	
	@Override
	public void init() {
	}
	
}
