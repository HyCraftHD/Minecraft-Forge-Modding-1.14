package net.hycrafthd.tutorialmod.data.provider;

import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Stream;

import net.hycrafthd.tutorialmod.TutorialMod;
import net.hycrafthd.tutorialmod.init.TutorialModTags;
import net.minecraft.block.Blocks;
import net.minecraft.data.*;
import net.minecraft.item.*;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.crafting.Ingredient.TagList;
import net.minecraft.tags.Tag;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.Tags;

public class TutorialModRecipeProvider extends RecipeProvider {
	
	public TutorialModRecipeProvider(DataGenerator generator) {
		super(generator);
	}
	
	@Override
	protected void registerRecipes(Consumer<IFinishedRecipe> consumer) {
		ShapedRecipeBuilder.shapedRecipe(Items.DIAMOND, 5) //
				.patternLine("TTT") //
				.patternLine("TXT") //
				.patternLine("TTT") //
				.key('T', Blocks.DIRT) //
				.key('X', TutorialModTags.Items.TUTORIAL) //
				.addCriterion("has_dirt", hasItem(Blocks.DIRT)) //
				.build(consumer, new ResourceLocation(TutorialMod.MODID, "tutorial_diamonds"));
		
		ShapelessRecipeBuilder.shapelessRecipe(Blocks.DIAMOND_BLOCK, 2) //
				.addIngredient(TutorialModTags.Items.TUTORIAL) //
				.addIngredient(Blocks.DIRT) //
				.addIngredient(Blocks.DIRT) //
				.addCriterion("has_dirt", hasItem(Blocks.DIRT)) //
				.build(consumer, new ResourceLocation(TutorialMod.MODID, "tutorial_diamond_blocks"));
		
		CookingRecipeBuilder.smeltingRecipe(Ingredient.fromTag(TutorialModTags.Items.TUTORIAL), Blocks.DIRT, 3, 20) //
				.addCriterion("has_tutorial_stuff", hasItem(TutorialModTags.Items.TUTORIAL)) //
				.build(consumer, new ResourceLocation(TutorialMod.MODID, "tutorial_dirt"));
		
		CookingRecipeBuilder.smeltingRecipe(getFakeIngredient(Tags.Items.STORAGE_BLOCKS), Blocks.DIRT, 3, 20) //
				.addCriterion("has_storage_blocks", hasItem(Tags.Items.STORAGE_BLOCKS)) //
				.build(consumer, new ResourceLocation(TutorialMod.MODID, "tutorial_dirt_fake"));
	}
	
	private Ingredient getFakeIngredient(Tag<Item> tag) {
		return Ingredient.fromItemListStream(Stream.of(new TagList(tag) {
			
			@Override
			public Collection<ItemStack> getStacks() {
				return Arrays.asList(new ItemStack(Items.ACACIA_BOAT));
			}
		}));
	}
	
}
