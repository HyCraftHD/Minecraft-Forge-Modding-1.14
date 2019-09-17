package net.hycrafthd.tutorialmod.data.provider;

import net.hycrafthd.tutorialmod.init.TutorialModBlocks;
import net.hycrafthd.tutorialmod.init.TutorialModTags;
import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
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
