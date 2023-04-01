package com.amuzil.cactus.effect;

import com.amuzil.cactus.Cactus;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.minecraft.world.effect.MobEffect;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(modid = Cactus.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CactusEffects {
	private static final DeferredRegister<MobEffect> EFFECTS = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, Cactus.MODID);

	public static final RegistryObject<MobEffect> QUENCHED = EFFECTS.register("quenched", QuenchedEffect::new);

	public static void register() {
		EFFECTS.register(FMLJavaModLoadingContext.get().getModEventBus());
	}
}
