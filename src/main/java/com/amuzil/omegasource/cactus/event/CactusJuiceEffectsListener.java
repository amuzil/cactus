package com.amuzil.omegasource.cactus.event;

import com.amuzil.omegasource.cactus.Cactus;
import com.amuzil.omegasource.cactus.CactusSettings;
import com.amuzil.omegasource.cactus.capability.CapabilityCactusJuice;
import com.amuzil.omegasource.cactus.capability.CapabilityCactusJuice.ICactusJuice;
import net.minecraftforge.client.event.FOVModifierEvent;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@EventBusSubscriber(modid = Cactus.MODID, bus = Bus.FORGE)
public class CactusJuiceEffectsListener {
	@SubscribeEvent
	public static void fovModifier(FOVModifierEvent event) {
		Cactus.getLogger().info("FOVModifierEvent");
		LazyOptional<ICactusJuice> optional = event.getEntity().getCapability(CapabilityCactusJuice.CACTUS_JUICE);
		Cactus.getLogger().info("optional: " + optional);
		if (optional.isPresent()) {
			Cactus.getLogger().info("present");
			ICactusJuice juice = optional.orElseThrow(IllegalStateException::new);
			if (juice.isJuiced()) {
				// TODO settings
				double maxMultiplier = 3;
				double growthFactor = 1.4;
				double maxDuration = CactusSettings.getDuration();
				double duration = juice.durationLeft();

				double multiplier = Math.pow(1 - (duration / maxDuration), growthFactor) * (maxMultiplier - 1) + 1;

				event.setNewfov((float) multiplier);
			}
		}
	}
}
