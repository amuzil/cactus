package com.amuzil.cactus.event;

import com.amuzil.cactus.Cactus;
import com.amuzil.cactus.item.CactusItems;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Cactus.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CactusSliceListener {
	@SubscribeEvent
	public static void playerLeftClickBlock(PlayerInteractEvent.LeftClickBlock event) {
		Level level = event.getLevel();
		Player player = event.getEntity();
		BlockPos blockPos = event.getPos();

		if (level.getBlockState(blockPos).getMaterial() == Material.CACTUS && player.mayInteract(level, blockPos)) {
			Item item = player.getItemInHand(event.getHand()).getItem();
			if (item instanceof AxeItem || item instanceof SwordItem) {
				level.setBlock(blockPos, Blocks.AIR.defaultBlockState(), Block.UPDATE_ALL);

				ItemStack result = new ItemStack(CactusItems.CACTUS_JUICE.get());
				ItemEntity droppedItem = new ItemEntity(level, blockPos.getX(), blockPos.getY(), blockPos.getZ(), result);
				droppedItem.setPickUpDelay(40);
				level.addFreshEntity(droppedItem);

				event.setCanceled(true);
			}
		}
	}
}
