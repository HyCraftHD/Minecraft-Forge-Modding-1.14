package net.hycrafthd.tutorialmod.block;

import net.hycrafthd.tutorialmod.init.TutorialModTileEntityTypes;
import net.hycrafthd.tutorialmod.tileentity.InventoryBlockTileEntity;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.*;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer.Builder;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.*;
import net.minecraft.world.*;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.network.NetworkHooks;

public class InventoryBlockBlock extends Block {
	
	public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
	
	public InventoryBlockBlock() {
		super(Properties.create(Material.WOOD).hardnessAndResistance(3).harvestTool(ToolType.AXE).sound(SoundType.WOOD));
		setDefaultState(getDefaultState().with(FACING, Direction.NORTH));
	}
	
	@Override
	public boolean onBlockActivated(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult hit) {
		if (world.isRemote() || !(player instanceof ServerPlayerEntity)) {
			return true;
		}
		final TileEntity tileEntity = world.getTileEntity(pos);
		if (!(tileEntity instanceof InventoryBlockTileEntity)) {
			return false;
		}
		NetworkHooks.openGui((ServerPlayerEntity) player, (InventoryBlockTileEntity) tileEntity, pos);
		return true;
	}
	
	@Override
	public BlockState getStateForPlacement(BlockItemUseContext context) {
		return getDefaultState().with(FACING, context.getPlacementHorizontalFacing().getOpposite());
	}
	
	@Override
	protected void fillStateContainer(Builder<Block, BlockState> builder) {
		builder.add(FACING);
	}
	
	@Override
	public boolean hasTileEntity(BlockState state) {
		return true;
	}
	
	@Override
	public TileEntity createTileEntity(BlockState state, IBlockReader world) {
		return TutorialModTileEntityTypes.INVENTORY_BLOCK.create();
	}
}
