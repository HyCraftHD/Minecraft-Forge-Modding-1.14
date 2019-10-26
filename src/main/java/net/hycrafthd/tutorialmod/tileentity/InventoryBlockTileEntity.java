package net.hycrafthd.tutorialmod.tileentity;

import net.hycrafthd.tutorialmod.init.TutorialModTileEntityTypes;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.*;

public class InventoryBlockTileEntity extends TileEntity {
	
	private final LazyOptional<ItemStackHandler> inventory = LazyOptional.of(() -> new ItemStackHandler(18));
	
	public InventoryBlockTileEntity() {
		super(TutorialModTileEntityTypes.INVENTORY_BLOCK);
	}
	
	@Override
	public CompoundNBT write(CompoundNBT compound) {
		super.write(compound);
		inventory.ifPresent(handler -> compound.put("inventory", handler.serializeNBT()));
		return compound;
	}
	
	@Override
	public void read(CompoundNBT compound) {
		super.read(compound);
		inventory.ifPresent(handler -> handler.deserializeNBT(compound.getCompound("inventory")));
	}
	
	@Override
	public <T> LazyOptional<T> getCapability(Capability<T> capability, Direction side) {
		if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
			return inventory.cast();
		}
		return super.getCapability(capability, side);
	}
	
	public LazyOptional<ItemStackHandler> getInventory() {
		return inventory;
	}
	
}
