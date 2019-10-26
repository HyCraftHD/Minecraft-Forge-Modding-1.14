package net.hycrafthd.tutorialmod.init;

import net.hycrafthd.tutorialmod.TutorialMod;
import net.hycrafthd.tutorialmod.tileentity.InventoryBlockTileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.registries.IForgeRegistry;

@EventBusSubscriber(modid = TutorialMod.MODID, bus = Bus.MOD)
public class TutorialModTileEntityTypes {
	
	public static final TileEntityType<InventoryBlockTileEntity> INVENTORY_BLOCK = TileEntityType.Builder.create(InventoryBlockTileEntity::new, TutorialModBlocks.INVENTORY_BLOCK).build(null);
	
	@SubscribeEvent
	public static void register(Register<TileEntityType<?>> event) {
		final IForgeRegistry<TileEntityType<?>> registry = event.getRegistry();
		
		registry.register(INVENTORY_BLOCK.setRegistryName(TutorialMod.MODID, "inventory_block"));
	}
	
}
