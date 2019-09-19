package net.hycrafthd.tutorialmod.init;

import com.mojang.brigadier.CommandDispatcher;

import net.hycrafthd.tutorialmod.TutorialMod;
import net.hycrafthd.tutorialmod.command.TutorialCommand;
import net.minecraft.command.CommandSource;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;

@EventBusSubscriber(modid = TutorialMod.MODID, bus = Bus.FORGE)
public class TutorialModCommands {
	
	@SubscribeEvent
	public static void register(FMLServerStartingEvent event) {
		final CommandDispatcher<CommandSource> dispatcher = event.getCommandDispatcher();
		TutorialCommand.register(dispatcher);
	}
	
}
