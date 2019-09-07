package net.hycrafthd.tutorialmod.item;

import net.hycrafthd.tutorialmod.init.TutorialModItemGroups;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Rarity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class BasicItem extends Item {

	public BasicItem() {
		super(new Properties().defaultMaxDamage(20).group(TutorialModItemGroups.GROUP).rarity(Rarity.EPIC));
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand) {
		final ItemStack stack = player.getHeldItem(hand);
		if (!world.isRemote) {
			world.setBlockState(player.getPosition().down(), Blocks.DIAMOND_BLOCK.getDefaultState());
			player.addExperienceLevel(5);
		}
		stack.damageItem(1, player, entity -> {
		});
		player.jump();
		return super.onItemRightClick(world, player, hand);
	}

}
