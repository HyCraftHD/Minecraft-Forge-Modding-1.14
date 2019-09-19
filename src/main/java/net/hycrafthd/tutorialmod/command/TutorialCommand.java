package net.hycrafthd.tutorialmod.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;

import net.minecraft.command.*;
import net.minecraft.command.arguments.*;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.math.*;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.server.*;

public class TutorialCommand {
	
	public static void register(CommandDispatcher<CommandSource> dispatcher) {
		dispatcher.register(Commands.literal("tutorial") //
				.then(registerTeleportCommand()));
	}
	
	private static LiteralArgumentBuilder<CommandSource> registerTeleportCommand() {
		return Commands.literal("teleport") //
				.requires(source -> source.hasPermissionLevel(2)) //
				.then(Commands.argument("dimension", DimensionArgument.getDimension()) //
						.executes(context -> {
							return teleportPlayer(context.getSource().asPlayer(), DimensionArgument.func_212592_a(context, "dimension"), context.getSource().getPos());
						}) //
						.then(Commands.argument("location", Vec3Argument.vec3()) //
								.executes(context -> {
									return teleportPlayer(context.getSource().asPlayer(), DimensionArgument.func_212592_a(context, "dimension"), new Vec3d(BlockPosArgument.getBlockPos(context, "location")));
								})));
		
	}
	
	private static int teleportPlayer(ServerPlayerEntity player, DimensionType dimensionType, Vec3d pos) {
		final ServerWorld world = player.getServer().getWorld(dimensionType);
		world.getChunkProvider().func_217228_a(TicketType.POST_TELEPORT, new ChunkPos(new BlockPos(pos)), 1, player.getEntityId());
		
		player.detach();
		if (player.isSleeping()) {
			player.wakeUpPlayer(true, true, false);
		}
		
		if (world == player.world) {
			player.connection.setPlayerLocation(pos.getX(), pos.getY(), pos.getZ(), player.rotationYaw, player.rotationPitch);
		} else {
			player.teleport(world, pos.getX(), pos.getY(), pos.getZ(), player.rotationYaw, player.rotationPitch);
		}
		player.setRotationYawHead(player.rotationYaw);
		return 0;
	}
	
}
