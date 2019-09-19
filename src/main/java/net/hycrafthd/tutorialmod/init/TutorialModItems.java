package net.hycrafthd.tutorialmod.init;

import net.hycrafthd.tutorialmod.TutorialMod;
import net.hycrafthd.tutorialmod.item.BasicItem;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.registries.IForgeRegistry;

@EventBusSubscriber(modid = TutorialMod.MODID, bus = Bus.MOD)
public class TutorialModItems {
	
	public static final BasicItem BASIC_ITEM = new BasicItem();
	
	@SubscribeEvent
	public static void register(Register<Item> event) {
		final IForgeRegistry<Item> registry = event.getRegistry();
		
		registry.register(BASIC_ITEM.setRegistryName(TutorialMod.MODID, "basic_item"));
	}
	
}
