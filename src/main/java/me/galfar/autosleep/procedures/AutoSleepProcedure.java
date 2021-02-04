package me.galfar.autosleep.procedures;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.common.MinecraftForge;

import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.gen.feature.template.Template;
import net.minecraft.world.gen.feature.template.PlacementSettings;
import net.minecraft.world.World;
import net.minecraft.world.IWorld;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.Rotation;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Mirror;
import net.minecraft.util.Hand;
import net.minecraft.util.Direction;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.Entity;

import me.galfar.autosleep.AutoSleepModVariables;
import me.galfar.autosleep.AutoSleepModElements;
import me.galfar.autosleep.AutoSleepMod;

import java.util.Map;
import java.util.HashMap;

@AutoSleepModElements.ModElement.Tag
public class AutoSleepProcedure extends AutoSleepModElements.ModElement {
	public AutoSleepProcedure(AutoSleepModElements instance) {
		super(instance, 1);
		MinecraftForge.EVENT_BUS.register(this);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				AutoSleepMod.LOGGER.warn("Failed to load dependency entity for procedure AutoSleep!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				AutoSleepMod.LOGGER.warn("Failed to load dependency world for procedure AutoSleep!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		IWorld world = (IWorld) dependencies.get("world");
		if (((!((world instanceof World) ? ((World) world).isDaytime() : false))
				&& (((entity.getCapability(AutoSleepModVariables.PLAYER_VARIABLES_CAPABILITY, null)
						.orElse(new AutoSleepModVariables.PlayerVariables())).SleepTrigerred) == (false)))) {
			{
				boolean _setval = (boolean) (true);
				entity.getCapability(AutoSleepModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.SleepTrigerred = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
			{
				double _setval = (double) (entity.getPosX());
				entity.getCapability(AutoSleepModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.BlockX = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
			{
				double _setval = (double) (entity.getPosY());
				entity.getCapability(AutoSleepModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.BlockY = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
			{
				double _setval = (double) (entity.getPosZ());
				entity.getCapability(AutoSleepModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.BlockZ = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
			if (world instanceof World && !world.isRemote()) {
				Template template = ((ServerWorld) world).getStructureTemplateManager()
						.getTemplateDefaulted(new ResourceLocation("auto_sleep", "bedd"));
				if (template != null) {
					template.func_237144_a_((ServerWorld) world,
							new BlockPos(
									(int) ((entity.getCapability(AutoSleepModVariables.PLAYER_VARIABLES_CAPABILITY, null)
											.orElse(new AutoSleepModVariables.PlayerVariables())).BlockX),
									(int) ((entity.getCapability(AutoSleepModVariables.PLAYER_VARIABLES_CAPABILITY, null)
											.orElse(new AutoSleepModVariables.PlayerVariables())).BlockY),
									(int) ((entity.getCapability(AutoSleepModVariables.PLAYER_VARIABLES_CAPABILITY, null)
											.orElse(new AutoSleepModVariables.PlayerVariables())).BlockZ)),
							new PlacementSettings().setRotation(Rotation.NONE).setMirror(Mirror.NONE).setChunk(null).setIgnoreEntities(false),
							((World) world).rand);
				}
			}
			if (entity instanceof PlayerEntity) {
				Entity _ent = entity;
				BlockPos _bp = new BlockPos(
						(int) ((entity.getCapability(AutoSleepModVariables.PLAYER_VARIABLES_CAPABILITY, null)
								.orElse(new AutoSleepModVariables.PlayerVariables())).BlockX),
						(int) ((entity.getCapability(AutoSleepModVariables.PLAYER_VARIABLES_CAPABILITY, null)
								.orElse(new AutoSleepModVariables.PlayerVariables())).BlockY),
						(int) ((entity.getCapability(AutoSleepModVariables.PLAYER_VARIABLES_CAPABILITY, null)
								.orElse(new AutoSleepModVariables.PlayerVariables())).BlockZ));
				_ent.world.getBlockState(_bp).getBlock().onBlockActivated(_ent.world.getBlockState(_bp), _ent.world, _bp, (PlayerEntity) entity,
						Hand.MAIN_HAND, BlockRayTraceResult.createMiss(new Vector3d(_bp.getX(), _bp.getY(), _bp.getZ()), Direction.UP, _bp));
			}
			if (world instanceof World && !world.isRemote()) {
				Template template = ((ServerWorld) world).getStructureTemplateManager()
						.getTemplateDefaulted(new ResourceLocation("auto_sleep", "airr"));
				if (template != null) {
					template.func_237144_a_((ServerWorld) world,
							new BlockPos(
									(int) ((entity.getCapability(AutoSleepModVariables.PLAYER_VARIABLES_CAPABILITY, null)
											.orElse(new AutoSleepModVariables.PlayerVariables())).BlockX),
									(int) ((entity.getCapability(AutoSleepModVariables.PLAYER_VARIABLES_CAPABILITY, null)
											.orElse(new AutoSleepModVariables.PlayerVariables())).BlockY),
									(int) ((entity.getCapability(AutoSleepModVariables.PLAYER_VARIABLES_CAPABILITY, null)
											.orElse(new AutoSleepModVariables.PlayerVariables())).BlockZ)),
							new PlacementSettings().setRotation(Rotation.NONE).setMirror(Mirror.NONE).setChunk(null).setIgnoreEntities(false),
							((World) world).rand);
				}
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
	public void onPlayerTick(TickEvent.PlayerTickEvent event) {
		if (event.phase == TickEvent.Phase.END) {
			Entity entity = event.player;
			World world = entity.world;
			double i = entity.getPosX();
			double j = entity.getPosY();
			double k = entity.getPosZ();
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
}
