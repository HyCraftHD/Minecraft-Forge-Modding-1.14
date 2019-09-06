package net.hycrafthd.tutorialmod.init;

import net.hycrafthd.tutorialmod.TutorialMod;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@EventBusSubscriber(modid = TutorialMod.MODID, bus = Bus.MOD)
public class TutorialModBlocks {

	@SubscribeEvent
	public static void register(Register<Block> event) {
	}

	@SubscribeEvent
	public static void registerItemBlock(Register<Item> event) {
	}

}
