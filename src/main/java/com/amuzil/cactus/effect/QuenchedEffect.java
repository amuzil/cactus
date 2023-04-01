package com.amuzil.cactus.effect;

import com.amuzil.cactus.Cactus;
import com.amuzil.cactus.network.CactusMessages;
import com.amuzil.cactus.network.UnquenchedMessage;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.event.entity.living.MobEffectEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.network.PacketDistributor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Mod.EventBusSubscriber(modid = Cactus.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class QuenchedEffect extends MobEffect {
	protected QuenchedEffect() {
		super(MobEffectCategory.HARMFUL, 4521796);
	}

	@SubscribeEvent
	public static void onEffectAdded(MobEffectEvent.Added event) {
		removeShader(event.getEntity(), event.getEffectInstance());
	}

	@SubscribeEvent(priority = EventPriority.LOWEST)
	public static void onEffectRemoved(MobEffectEvent.Remove event) {
		Cactus.getLogger().debug("Effect removed");
		removeShader(event.getEntity(), event.getEffectInstance());
	}

	@SubscribeEvent
	public static void onEffectExpired(MobEffectEvent.Expired event) {
		Cactus.getLogger().debug("Effect expired");
		removeShader(event.getEntity(), event.getEffectInstance());
	}

	private static void removeShader(Entity target, @Nullable MobEffectInstance effectInstance) {
		if (effectInstance != null && effectInstance.getEffect() instanceof QuenchedEffect
			&& (target instanceof ServerPlayer player))
			CactusMessages.INSTANCE.send(
				PacketDistributor.PLAYER.with(() -> player),
				new UnquenchedMessage()
			);
	}

	@Override
	public boolean isDurationEffectTick(int pDuration, int pAmplifier) {
		return true;
	}

	@Override
	public void applyEffectTick(@NotNull LivingEntity entity, int amplifier) {
		if (entity.getLevel().isClientSide)
			ClientQuenchedEffectHandler.addShader();
	}
}
