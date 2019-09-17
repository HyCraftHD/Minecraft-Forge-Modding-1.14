package net.hycrafthd.tutorialmod.data.provider;

import net.hycrafthd.tutorialmod.init.TutorialModBlocks;
import net.hycrafthd.tutorialmod.init.TutorialModItems;
import net.hycrafthd.tutorialmod.init.TutorialModTags;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.ItemTagsProvider;

public class TutorialModItemTagsProvider extends ItemTagsProvider {

	public TutorialModItemTagsProvider(DataGenerator generator) {
		super(generator);
	}

	@Override
	protected void registerTags() {
		getBuilder(TutorialModTags.Items.TUTORIAL).add(TutorialModItems.BASIC_ITEM).add(TutorialModBlocks.BASIC_BLOCK.asItem());
	}

}
