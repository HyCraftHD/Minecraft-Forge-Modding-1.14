package net.hycrafthd.tutorialmod.data.provider;

import net.hycrafthd.tutorialmod.init.*;
import net.minecraft.data.*;
import net.minecraftforge.common.Tags;

public class TutorialModBlockTagsProvider extends BlockTagsProvider {
	
	public TutorialModBlockTagsProvider(DataGenerator generator) {
		super(generator);
	}
	
	@Override
	protected void registerTags() {
		getBuilder(TutorialModTags.Blocks.TUTORIAL).add(TutorialModBlocks.BASIC_BLOCK).add(Tags.Blocks.STORAGE_BLOCKS_DIAMOND);
	}
	
}
