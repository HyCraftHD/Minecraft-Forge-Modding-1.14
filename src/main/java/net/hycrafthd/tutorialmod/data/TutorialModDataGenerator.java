package net.hycrafthd.tutorialmod.data;

import net.hycrafthd.tutorialmod.TutorialMod;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;

@EventBusSubscriber(modid = TutorialMod.MODID, bus = Bus.MOD)
public class TutorialModDataGenerator {

	@SubscribeEvent
	public static void data(GatherDataEvent event) {
		final DataGenerator generator = event.getGenerator();
		if (event.includeServer()) {
			
		}
	}

}
