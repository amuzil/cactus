package com.amuzil.cactus.event;

import com.amuzil.cactus.Cactus;
import com.amuzil.cactus.effect.CactusEffects;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraftforge.client.event.ComputeFovModifierEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Cactus.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class QuenchedEffectsListener {
	@SubscribeEvent
	public static void fovModifier(ComputeFovModifierEvent event) {
		MobEffectInstance effect = event.getPlayer().getEffect(CactusEffects.QUENCHED.get());
		if (effect != null) {
			double growthFactor = 0.7;
			double cycleLength = 5.0 * 20;

			double duration = effect.getDuration();
			double part = Math.pow(Math.sin(duration / cycleLength * Math.PI), 2);

			double modifier = Math.pow(event.getFovModifier() + part, growthFactor)
				- (event.getFovModifier() / 2);

			event.setNewFovModifier((float) modifier);
		}
	}
}
