package com.amuzil.omegasource.cactus.mixin.common;

import com.amuzil.omegasource.cactus.Cactus;
import net.minecraftforge.common.MinecraftForge;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftForge.class)
public class MinecraftForgeMixin {
	@Inject(method = "initialize", at = @At("HEAD"), remap = false)
	private static void initialize$testInjection(CallbackInfo ci) {
		Cactus.getLogger().info("Injected cactus juice into the veins of your game!");
	}
}
