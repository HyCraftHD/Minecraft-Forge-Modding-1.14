package net.hycrafthd.tutorialmod.util;

import net.minecraft.inventory.container.*;
import net.minecraftforge.fml.network.IContainerFactory;

public class ModdedContainerType<T extends Container> extends ContainerType<T> {
	
	public ModdedContainerType(IContainerFactory<T> factory) {
		super(factory);
	}
	
}
