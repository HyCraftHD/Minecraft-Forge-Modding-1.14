package net.hycrafthd.tutorialmod.itemgroup;

import java.util.function.Supplier;

import net.hycrafthd.tutorialmod.TutorialMod;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IItemProvider;

public class BasicItemGroup extends ItemGroup {

	private final Supplier<IItemProvider> supplier;

	public BasicItemGroup(String label, Supplier<IItemProvider> supplier) {
		super(TutorialMod.MODID + "." + label);
		this.supplier = supplier;
	}

	@Override
	public ItemStack createIcon() {
		return new ItemStack(supplier.get());
	}

}
