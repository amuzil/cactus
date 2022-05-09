package com.amuzil.omegasource.cactus.event;

import com.amuzil.omegasource.cactus.Cactus;
import net.minecraftforge.event.TickEvent.ClientTickEvent;
import net.minecraftforge.event.TickEvent.Phase;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;

@Mod.EventBusSubscriber(modid = Cactus.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class TaskScheduler {
	private record RepeatingClientTask(
		Predicate<ClientTickEvent> execute,
		Runnable update,
		Runnable terminate,
		long lastExecutionTime,
		Phase phase
	) {
		public boolean process(ClientTickEvent event) {
			long millis = System.currentTimeMillis();
			if (event.phase == phase) {
				if (millis < lastExecutionTime) {
					if (execute.test(event)) {
						update.run();
					}
					return false;
				} else {
					terminate.run();
					return true;
				}
			}
			return false;
		}
	}

	private static final List<RepeatingClientTask> repeatingClientTasks = new ArrayList<>();

	public static void scheduleClientTask(Runnable runnable, int delayInTicks, Phase phase) {
		repeatClientTask(event -> false, () -> {}, runnable, delayInTicks, phase);
	}

	public static void repeatClientTask(Predicate<ClientTickEvent> execute, Runnable update, Runnable terminate, int delayInTicks, Phase phase) {
		repeatingClientTasks.add(new RepeatingClientTask(execute, update, terminate, System.currentTimeMillis() + delayInTicks * 50L, phase));
	}

	@SubscribeEvent
	public static void clientTick(ClientTickEvent event) {
		if (!repeatingClientTasks.isEmpty()) {
			Iterator<RepeatingClientTask> iterator = repeatingClientTasks.iterator();
			while (iterator.hasNext()) {
				RepeatingClientTask task = iterator.next();
				if (task.process(event)) {
					iterator.remove();
				} else {
				}
			}
		}
	}

	/*@SubscribeEvent
	public static void registerShaders(RegisterShadersEvent event) throws IOException
	{
		event.registerShader(new ShaderInstance(event.getResourceManager(), new ResourceLocation("forge","rendertype_entity_unlit_translucent"), DefaultVertexFormat.NEW_ENTITY), (p_172645_) -> {
			rendertypeEntityTranslucentUnlitShader = p_172645_;
		});
	}*/
}
