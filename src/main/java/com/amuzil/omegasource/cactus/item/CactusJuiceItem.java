package com.amuzil.omegasource.cactus.item;

import com.amuzil.omegasource.cactus.Cactus;
import com.amuzil.omegasource.cactus.event.TaskScheduler;
import com.amuzil.omegasource.cactus.food.CactusFoods;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.PostChain;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BowlFoodItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.TickEvent.ClientTickEvent;
import net.minecraftforge.event.TickEvent.Phase;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;
import java.util.function.Predicate;

public class CactusJuiceItem extends BowlFoodItem {
	public CactusJuiceItem() {
		super(new Item.Properties().stacksTo(1).tab(CreativeModeTab.TAB_FOOD).food(CactusFoods.CACTUS_JUICE));
	}

	@Override
	public @NotNull ItemStack finishUsingItem(@NotNull ItemStack itemStack, @NotNull Level level, LivingEntity entity) {
		itemStack = entity.eat(level, itemStack);

		int duration = 30 * 20;
		entity.addEffect(new MobEffectInstance(MobEffects.CONFUSION, duration, 4, false, false, false));
		entity.addEffect(new MobEffectInstance(MobEffects.GLOWING, duration, 0, false, false, false));
		entity.addEffect(new MobEffectInstance(MobEffects.LUCK, duration));

		if (level.isClientSide) {
			ResourceLocation shader = new ResourceLocation(Cactus.MODID, "shaders/post/cactus_juice.json");
//			ResourceLocation shader = new ResourceLocation("minecraft", "shaders/post/desaturate.json");
			Predicate<ClientTickEvent> predicate = clientTickEvent -> !shader.toString().equals(Optional.ofNullable(Minecraft.getInstance().gameRenderer.currentEffect()).map(PostChain::getName).orElse(""));
			Runnable update = () -> {
				Minecraft.getInstance().gameRenderer.loadEffect(shader);
			};
			Runnable terminate = () -> Minecraft.getInstance().gameRenderer.shutdownEffect();
			TaskScheduler.repeatClientTask(predicate, update, terminate, duration, Phase.END);
		}

		if (entity instanceof Player player && player.getAbilities().instabuild) {
			return itemStack;
		} else {
			return new ItemStack(CactusItems.CACTUS_BOWL.get());
		}
	}
}
