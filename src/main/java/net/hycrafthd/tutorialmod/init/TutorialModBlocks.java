package net.hycrafthd.tutorialmod.init;

import net.hycrafthd.tutorialmod.TutorialMod;
import net.hycrafthd.tutorialmod.block.BasicBlock;
import net.hycrafthd.tutorialmod.item.BasicBlockItem;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.registries.IForgeRegistry;

@EventBusSubscriber(modid = TutorialMod.MODID, bus = Bus.MOD)
public class TutorialModBlocks {
	
	public static final BasicBlock BASIC_BLOCK = new BasicBlock();
	
	@SubscribeEvent
	public static void register(Register<Block> event) {
		final IForgeRegistry<Block> registry = event.getRegistry();
		
		registry.register(BASIC_BLOCK.setRegistryName(TutorialMod.MODID, "basic_block"));
	}
	
	@SubscribeEvent
	public static void registerBlockItem(Register<Item> event) {
		final IForgeRegistry<Item> registry = event.getRegistry();
		
		registry.register(new BasicBlockItem(BASIC_BLOCK));
	}
	
}
