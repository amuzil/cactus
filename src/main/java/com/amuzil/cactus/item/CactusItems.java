package com.amuzil.cactus.item;

import com.amuzil.cactus.Cactus;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(modid = Cactus.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CactusItems {
	private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Cactus.MODID);

	public static final RegistryObject<Item> CACTUS_BOWL = ITEMS.register("cactus_bowl", CactusBowlItem::new);
	public static final RegistryObject<Item> CACTUS_JUICE = ITEMS.register("cactus_juice", CactusJuiceItem::new);

	public static void register() {
		ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
	}

	@SubscribeEvent
	public static void buildContents(CreativeModeTabEvent.BuildContents event) {
		if (event.getTab() == CreativeModeTabs.INGREDIENTS) {
			event.accept(CACTUS_BOWL.get());
		} else if (event.getTab() == CreativeModeTabs.FOOD_AND_DRINKS) {
			event.accept(CACTUS_JUICE.get());
		}
	}
}
