package net.hycrafthd.tutorialmod.block;

import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Hand;
import net.minecraft.util.math.*;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;

public class BasicBlock extends Block {
	
	public BasicBlock() {
		super(Properties.create(Material.ROCK).lightValue(10).slipperiness(1.2F).hardnessAndResistance(5).harvestLevel(2).harvestTool(ToolType.PICKAXE).sound(SoundType.GLASS));
	}
	
	@Override
	public boolean onBlockActivated(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
		player.sendMessage(new StringTextComponent("Test"));
		return true;
	}
	
}
