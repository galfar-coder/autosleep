package me.galfar.autosleep.procedures;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.common.MinecraftForge;

import net.minecraft.entity.Entity;

import me.galfar.autosleep.AutoSleepModVariables;
import me.galfar.autosleep.AutoSleepModElements;
import me.galfar.autosleep.AutoSleepMod;

import java.util.Map;
import java.util.HashMap;

@AutoSleepModElements.ModElement.Tag
public class OnSpawnProcedure extends AutoSleepModElements.ModElement {
	public OnSpawnProcedure(AutoSleepModElements instance) {
		super(instance, 2);
		MinecraftForge.EVENT_BUS.register(this);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				AutoSleepMod.LOGGER.warn("Failed to load dependency entity for procedure OnSpawn!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		if ((((entity.getCapability(AutoSleepModVariables.PLAYER_VARIABLES_CAPABILITY, null)
				.orElse(new AutoSleepModVariables.PlayerVariables())).SpawnTriggered) == (false))) {
			{
				boolean _setval = (boolean) (true);
				entity.getCapability(AutoSleepModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.SpawnTriggered = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
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
					capability.SpawnX = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
		}
	}

	@SubscribeEvent
	public void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event) {
		Entity entity = event.getPlayer();
		Map<String, Object> dependencies = new HashMap<>();
		dependencies.put("x", entity.getPosX());
		dependencies.put("y", entity.getPosY());
		dependencies.put("z", entity.getPosZ());
		dependencies.put("world", entity.world);
		dependencies.put("entity", entity);
		dependencies.put("event", event);
		this.executeProcedure(dependencies);
	}
}
