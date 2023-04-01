package com.amuzil.cactus;

import net.minecraftforge.common.ForgeConfigSpec;

public class CactusSettings {
	public static final CactusSettings INSTANCE;
	public static final ForgeConfigSpec SPECIFICATION;

	private static final int TICKS_PER_SECOND = 20;

	static {
		var pair = new ForgeConfigSpec.Builder().configure(CactusSettings::new);
		INSTANCE = pair.getLeft();
		SPECIFICATION = pair.getRight();
	}

	public final ForgeConfigSpec.IntValue duration;

	public CactusSettings(ForgeConfigSpec.Builder builder) {
		builder.push("effects");
		duration = builder
			.comment("Duration of the cactus juice effect in seconds")
			.defineInRange("duration", 30, 1, 60);
		builder.pop();
	}

	public static int getDurationInTicks() {
		return INSTANCE.duration.get() * TICKS_PER_SECOND;
	}
}
