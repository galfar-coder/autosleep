package me.galfar.autosleep.procedures;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.common.MinecraftForge;

import net.minecraft.world.IWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.Entity;

import me.galfar.autosleep.AutoSleepModVariables;
import me.galfar.autosleep.AutoSleepModElements;
import me.galfar.autosleep.AutoSleepMod;

import java.util.Map;
import java.util.HashMap;

@AutoSleepModElements.ModElement.Tag
public class OnBedClickProcedure extends AutoSleepModElements.ModElement {
	public OnBedClickProcedure(AutoSleepModElements instance) {
		super(instance, 3);
		MinecraftForge.EVENT_BUS.register(this);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				AutoSleepMod.LOGGER.warn("Failed to load dependency entity for procedure OnBedClick!");
			return;
		}
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				AutoSleepMod.LOGGER.warn("Failed to load dependency x for procedure OnBedClick!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				AutoSleepMod.LOGGER.warn("Failed to load dependency y for procedure OnBedClick!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				AutoSleepMod.LOGGER.warn("Failed to load dependency z for procedure OnBedClick!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				AutoSleepMod.LOGGER.warn("Failed to load dependency world for procedure OnBedClick!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");
		if ((BlockTags.getCollection().getTagByID(new ResourceLocation(("mod:beds").toLowerCase(java.util.Locale.ENGLISH)))
				.contains((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock()))) {
			{
				double _setval = (double) (entity.getPosX());
				entity.getCapability(AutoSleepModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.SpawnX = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
			{
				double _setval = (double) (entity.getPosY());
				entity.getCapability(AutoSleepModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.SpawnY = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
			{
				double _setval = (double) (entity.getPosZ());
				entity.getCapability(AutoSleepModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.SpawnZ = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
			if (entity instanceof ServerPlayerEntity)
				((ServerPlayerEntity) entity).func_242111_a(((ServerPlayerEntity) entity).world.getDimensionKey(),
						new BlockPos(
								(int) ((entity.getCapability(AutoSleepModVariables.PLAYER_VARIABLES_CAPABILITY, null)
										.orElse(new AutoSleepModVariables.PlayerVariables())).SpawnX),
								(int) ((entity.getCapability(AutoSleepModVariables.PLAYER_VARIABLES_CAPABILITY, null)
										.orElse(new AutoSleepModVariables.PlayerVariables())).SpawnY),
								(int) ((entity.getCapability(AutoSleepModVariables.PLAYER_VARIABLES_CAPABILITY, null)
										.orElse(new AutoSleepModVariables.PlayerVariables())).SpawnZ)),
						0, true, false);
		}
	}

	@SubscribeEvent
	public void onRightClickBlock(PlayerInteractEvent.RightClickBlock event) {
		PlayerEntity entity = event.getPlayer();
		if (event.getHand() != entity.getActiveHand()) {
			return;
		}
		double i = event.getPos().getX();
		double j = event.getPos().getY();
		double k = event.getPos().getZ();
		IWorld world = event.getWorld();
		Map<String, Object> dependencies = new HashMap<>();
		dependencies.put("x", i);
		dependencies.put("y", j);
		dependencies.put("z", k);
		dependencies.put("world", world);
		dependencies.put("entity", entity);
		dependencies.put("event", event);
		this.executeProcedure(dependencies);
	}
}
