package com.amuzil.cactus.item;

import com.amuzil.cactus.CactusSettings;
import com.amuzil.cactus.effect.CactusEffects;
import com.amuzil.cactus.food.CactusFoods;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BowlFoodItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class CactusJuiceItem extends BowlFoodItem {
	public CactusJuiceItem() {
		super(new Item.Properties().stacksTo(1).food(CactusFoods.CACTUS_JUICE));
	}

	@Override
	public @NotNull ItemStack finishUsingItem(@NotNull ItemStack itemStack, @NotNull Level level, LivingEntity entity) {
		itemStack = entity.eat(level, itemStack);

		int duration = CactusSettings.getDurationInTicks();
		entity.addEffect(new MobEffectInstance(CactusEffects.QUENCHED.get(), duration));
		entity.addEffect(new MobEffectInstance(MobEffects.CONFUSION, duration, 4, false, false, false));
		entity.addEffect(new MobEffectInstance(MobEffects.GLOWING, duration, 0, false, false, false));
		entity.addEffect(new MobEffectInstance(MobEffects.LUCK, duration, 0, false, false, false));

		if (entity instanceof Player player && player.getAbilities().instabuild) {
			return itemStack;
		} else {
			return new ItemStack(CactusItems.CACTUS_BOWL.get());
		}
	}
}
