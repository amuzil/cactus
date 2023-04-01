package com.amuzil.cactus.item;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class CactusBowlItem extends Item {
	public CactusBowlItem() {
		super(new Properties());
	}

	@Override
	public int getBurnTime(ItemStack itemStack, @Nullable RecipeType<?> recipeType) {
		return 100;
	}

	@Override
	public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level level, @NotNull Player player, @NotNull InteractionHand hand) {
		ItemStack itemStack = player.getItemInHand(hand);
		BlockHitResult hitResult = getPlayerPOVHitResult(level, player, ClipContext.Fluid.NONE);

		if (hitResult.getType() == HitResult.Type.MISS) {
			return InteractionResultHolder.pass(itemStack);
		} else if (hitResult.getType() != HitResult.Type.BLOCK) {
			return InteractionResultHolder.pass(itemStack);
		} else {
			BlockPos blockPos = hitResult.getBlockPos();
			if (player.mayInteract(level, blockPos) && level.getBlockState(blockPos).getMaterial() == Material.CACTUS) {
				level.setBlock(blockPos, Blocks.AIR.defaultBlockState(), Block.UPDATE_ALL);
				player.awardStat(Stats.ITEM_USED.get(this));
				player.playSound(SoundEvents.BUCKET_FILL, 1.0F, 1.0F);

				ItemStack filled = new ItemStack(CactusItems.CACTUS_JUICE.get());
				ItemStack result = ItemUtils.createFilledResult(itemStack, player, filled);
				return InteractionResultHolder.success(result);
			}
		}

		return InteractionResultHolder.fail(itemStack);
	}
}
