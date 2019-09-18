package net.hycrafthd.tutorialmod.data.provider;

import java.io.IOException;
import java.nio.file.Path;
import java.util.function.BiConsumer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import net.hycrafthd.tutorialmod.init.TutorialModBlocks;
import net.minecraft.advancements.criterion.EnchantmentPredicate;
import net.minecraft.advancements.criterion.ItemPredicate;
import net.minecraft.advancements.criterion.MinMaxBounds;
import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DirectoryCache;
import net.minecraft.data.IDataProvider;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Items;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.ConstantRange;
import net.minecraft.world.storage.loot.ItemLootEntry;
import net.minecraft.world.storage.loot.LootParameterSets;
import net.minecraft.world.storage.loot.LootPool;
import net.minecraft.world.storage.loot.LootTable;
import net.minecraft.world.storage.loot.LootTableManager;
import net.minecraft.world.storage.loot.conditions.MatchTool;
import net.minecraft.world.storage.loot.conditions.SurvivesExplosion;
import net.minecraft.world.storage.loot.functions.ApplyBonus;
import net.minecraft.world.storage.loot.functions.ExplosionDecay;

public class TutorialModLootTableProvider implements IDataProvider {

	private static final Logger LOGGER = LogManager.getLogger();
	private static final Gson GSON = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();
	private final DataGenerator generator;

	public TutorialModLootTableProvider(DataGenerator generator) {
		this.generator = generator;
	}

	@Override
	public void act(DirectoryCache cache) throws IOException {
		registerLootTables((id, lootTable) -> {
			final Path path = getPath(id);
			try {
				IDataProvider.save(GSON, cache, LootTableManager.toJson(lootTable), path);
			} catch (IOException ex) {
				LOGGER.error("Couldn't save loot table {}", path, ex);
			}
		});
	}

	private Path getPath(ResourceLocation id) {
		return generator.getOutputFolder()
				.resolve("data/" + id.getNamespace() + "/loot_tables/" + id.getPath() + ".json");
	}

	@Override
	public String getName() {
		return "LootTables";
	}

	protected void registerLootTables(BiConsumer<ResourceLocation, LootTable> consumer) {
		registerBlock(TutorialModBlocks.BASIC_BLOCK,
				addFortuneBlockLootTable(TutorialModBlocks.BASIC_BLOCK, Items.DIAMOND), consumer);
	}

	private static void registerBlock(Block block, LootTable lootTable,
			BiConsumer<ResourceLocation, LootTable> consumer) {
		final ResourceLocation registryName = block.getRegistryName();
		consumer.accept(new ResourceLocation(registryName.getNamespace(), "blocks/" + registryName.getPath()),
				lootTable);
	}

	private static LootTable addBasicBlockLootTable(IItemProvider itemProvider) {
		return LootTable.builder() //
				.setParameterSet(LootParameterSets.BLOCK) //
				.addLootPool(LootPool.builder() //
						.rolls(ConstantRange.of(1)) //
						.addEntry(ItemLootEntry.builder(itemProvider)) //
						.acceptCondition(SurvivesExplosion.builder())) //
				.build();
	}

	private static LootTable addFortuneBlockLootTable(Block block, IItemProvider itemProvider) {
		return LootTable.builder() //
				.setParameterSet(LootParameterSets.BLOCK) //
				.addLootPool(LootPool.builder() //
						.rolls(ConstantRange.of(1)) //
						.addEntry(ItemLootEntry.builder(block) //
								.acceptCondition(MatchTool.builder(ItemPredicate.Builder.create() //
										.enchantment(new EnchantmentPredicate(Enchantments.SILK_TOUCH,
												MinMaxBounds.IntBound.atLeast(1)))) //
								).func_216080_a(ItemLootEntry.builder(itemProvider) //
										.acceptFunction(ApplyBonus.func_215869_a(Enchantments.FORTUNE)) //
										.acceptFunction(ExplosionDecay.func_215863_b()))))
				.build();
	}

}
