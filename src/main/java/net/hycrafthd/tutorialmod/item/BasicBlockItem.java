package net.hycrafthd.tutorialmod.item;

import net.hycrafthd.tutorialmod.init.TutorialModItemGroups;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;

public class BasicBlockItem extends BlockItem {
	
	public BasicBlockItem(Block block) {
		super(block, new Properties().group(TutorialModItemGroups.GROUP));
		setRegistryName(block.getRegistryName());
	}
	
}
