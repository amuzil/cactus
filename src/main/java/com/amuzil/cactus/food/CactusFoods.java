package com.amuzil.cactus.food;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.minecraft.world.food.FoodProperties;

import java.util.function.UnaryOperator;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CactusFoods {
	public static final FoodProperties CACTUS_JUICE = build(
		builder -> builder.nutrition(6).saturationMod(0.6F).alwaysEat()
	);

	private static FoodProperties build(UnaryOperator<FoodProperties.Builder> builder) {
		return builder.apply(new FoodProperties.Builder()).build();
	}
}
