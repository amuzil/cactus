package com.amuzil.omegasource.cactus.item;

import com.amuzil.omegasource.cactus.Cactus;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class CactusItems {
	private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Cactus.MODID);

	public static final RegistryObject<Item> CACTUS_BOWL = ITEMS.register("cactus_bowl", () -> new CactusBowlItem());
	public static final RegistryObject<Item> CACTUS_JUICE = ITEMS.register("cactus_juice", CactusJuiceItem::new);

	public static void register() {
		ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
	}
}
