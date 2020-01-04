package net.hycrafthd.tutorialmod.container;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.*;
import net.minecraft.inventory.container.*;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.api.distmarker.*;
import net.minecraftforge.items.*;
import net.minecraftforge.items.wrapper.PlayerMainInvWrapper;

public abstract class TileEntityContainer<T extends TileEntity> extends Container {
	
	protected final PlayerInventory playerInventory;
	protected final T tileEntity;
	
	// Client
	public TileEntityContainer(ContainerType<?> type, int id, PlayerInventory playerInventory, PacketBuffer buffer) {
		this(type, id, playerInventory, getClientTileEntity(buffer));
	}
	
	// Server
	public TileEntityContainer(ContainerType<?> type, int id, PlayerInventory playerInventory, T tileEntity) {
		super(type, id);
		this.playerInventory = playerInventory;
		this.tileEntity = tileEntity;
		init();
	}
	
	public abstract void init();
	
	public T getTileEntity() {
		return tileEntity;
	}
	
	@OnlyIn(Dist.CLIENT)
	@SuppressWarnings("unchecked")
	private static <X extends TileEntity> X getClientTileEntity(PacketBuffer buffer) {
		final TileEntity tileEntity = Minecraft.getInstance().world.getTileEntity(buffer.readBlockPos());
		if (tileEntity == null) {
			throw new IllegalStateException("The client tile entity muste be present");
		}
		return (X) tileEntity;
	}
	
	@Override
	public boolean canInteractWith(PlayerEntity playerIn) {
		return true;
	}
	
	protected int addHorizontalSlots(IItemHandler handler, int index, int x, int y, int amount, int slotDistance) {
		for (int i = 0; i < amount; i++) {
			addSlot(new SlotItemHandler(handler, index, x, y));
			x += slotDistance;
			index++;
		}
		return index;
	}
	
	protected int addSlots(IItemHandler handler, int index, int x, int y, int horizontalAmount, int horizonalSlotDistance, int verticalAmount, int verticalSlotDistance) {
		for (int i = 0; i < verticalAmount; i++) {
			index = addHorizontalSlots(handler, index, x, y, horizontalAmount, horizonalSlotDistance);
			y += verticalSlotDistance;
		}
		return index;
	}
	
	protected void addPlayerInventory(int x, int y) {
		final PlayerMainInvWrapper wrapper = new PlayerMainInvWrapper(playerInventory);
		addSlots(wrapper, 9, x, y, 9, 18, 3, 18);
		y += 58;
		addHorizontalSlots(wrapper, 0, x, y, 9, 18);
	}
}
