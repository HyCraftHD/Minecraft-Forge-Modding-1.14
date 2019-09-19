package net.hycrafthd.tutorialmod.data.provider;

import net.hycrafthd.tutorialmod.init.*;
import net.minecraft.data.*;

public class TutorialModItemTagsProvider extends ItemTagsProvider {
	
	public TutorialModItemTagsProvider(DataGenerator generator) {
		super(generator);
	}
	
	@Override
	protected void registerTags() {
		getBuilder(TutorialModTags.Items.TUTORIAL).add(TutorialModItems.BASIC_ITEM).add(TutorialModBlocks.BASIC_BLOCK.asItem());
	}
	
}
