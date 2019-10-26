package net.hycrafthd.tutorialmod.init;

import net.hycrafthd.tutorialmod.TutorialMod;
import net.hycrafthd.tutorialmod.container.InventoryBlockContainer;
import net.hycrafthd.tutorialmod.util.ModdedContainerType;
import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.registries.IForgeRegistry;

@EventBusSubscriber(modid = TutorialMod.MODID, bus = Bus.MOD)
public class TutorialModContainerTypes {
	
	public static final ContainerType<InventoryBlockContainer> INVENTORY_BLOCK = new ModdedContainerType<>(InventoryBlockContainer::new);
	
	@SubscribeEvent
	public static void register(Register<ContainerType<?>> event) {
		final IForgeRegistry<ContainerType<?>> registry = event.getRegistry();
		
		registry.register(INVENTORY_BLOCK.setRegistryName(TutorialMod.MODID, "inventory_block"));
	}
	
}
