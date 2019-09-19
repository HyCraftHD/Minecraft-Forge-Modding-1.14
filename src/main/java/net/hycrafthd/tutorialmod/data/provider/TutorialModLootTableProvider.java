package net.hycrafthd.tutorialmod.data.provider;

import java.io.IOException;
import java.nio.file.Path;
import java.util.function.BiConsumer;

import org.apache.logging.log4j.*;

import com.google.gson.*;

import net.hycrafthd.tutorialmod.init.TutorialModBlocks;
import net.minecraft.advancements.criterion.*;
import net.minecraft.block.Block;
import net.minecraft.data.*;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Items;
import net.minecraft.util.*;
import net.minecraft.world.storage.loot.*;
import net.minecraft.world.storage.loot.conditions.*;
import net.minecraft.world.storage.loot.functions.*;

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
		return generator.getOutputFolder().resolve("data/" + id.getNamespace() + "/loot_tables/" + id.getPath() + ".json");
	}
	
	@Override
	public String getName() {
		return "LootTables";
	}
	
	protected void registerLootTables(BiConsumer<ResourceLocation, LootTable> consumer) {
		registerBlock(TutorialModBlocks.BASIC_BLOCK, addFortuneBlockLootTable(TutorialModBlocks.BASIC_BLOCK, Items.DIAMOND), consumer);
	}
	
	private static void registerBlock(Block block, LootTable lootTable, BiConsumer<ResourceLocation, LootTable> consumer) {
		final ResourceLocation registryName = block.getRegistryName();
		consumer.accept(new ResourceLocation(registryName.getNamespace(), "blocks/" + registryName.getPath()), lootTable);
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
										.enchantment(new EnchantmentPredicate(Enchantments.SILK_TOUCH, MinMaxBounds.IntBound.atLeast(1)))) //
								).func_216080_a(ItemLootEntry.builder(itemProvider) //
										.acceptFunction(ApplyBonus.func_215869_a(Enchantments.FORTUNE)) //
										.acceptFunction(ExplosionDecay.func_215863_b()))))
				.build();
	}
	
}
