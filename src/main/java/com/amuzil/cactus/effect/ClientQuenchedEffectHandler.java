package com.amuzil.cactus.effect;

import com.amuzil.cactus.Cactus;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ClientQuenchedEffectHandler {
	public static final ResourceLocation SHADER = new ResourceLocation(Cactus.MODID, "shaders/post/quenched.json");
	private static final String SHADER_NAME = SHADER.toString();

	public static void addShader() {
		var currentShader = Minecraft.getInstance().gameRenderer.currentEffect();
		if (currentShader == null || !currentShader.getName().equals(SHADER_NAME)) {
			Minecraft.getInstance().gameRenderer.loadEffect(SHADER);
		}
	}

	public static void removeShader() {
		var currentShader = Minecraft.getInstance().gameRenderer.currentEffect();
		Cactus.getLogger().debug("Current shader: {}", currentShader == null ? "null" : currentShader.getName());
		if (currentShader != null && currentShader.getName().equals(SHADER_NAME)) {
			Cactus.getLogger().debug("Removing shader");
			Minecraft.getInstance().gameRenderer.shutdownEffect();
		}
	}
}
