package com.amuzil.cactus;

import com.amuzil.cactus.data.CactusItemModelProvider;
import com.amuzil.cactus.data.CactusLanguageProvider;
import com.amuzil.cactus.effect.CactusEffects;
import com.amuzil.cactus.item.CactusItems;
import com.amuzil.cactus.network.CactusMessages;
import lombok.Getter;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DataProvider;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(Cactus.MODID)
public class Cactus {
	public static final String MODID = "cactus";

	@Getter
	private static Cactus instance;

	@Getter
	private static Logger logger = LogManager.getLogger();

	// We suppress Sonarlint rule S3010,
	// because we can not assign the instance variable to a specific value,
	// as it is initialised by Forge itself.
	@SuppressWarnings("java:S3010")
	public Cactus() {
		instance = this;

		ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, CactusSettings.SPECIFICATION);

		CactusMessages.register();

		MinecraftForge.EVENT_BUS.register(this);
		FMLJavaModLoadingContext.get().getModEventBus().register(this);

		// Register our deferred registrars
		CactusItems.register();
		CactusEffects.register();
	}

	@SubscribeEvent
	public void gatherData(final GatherDataEvent event) {
		DataGenerator generator = event.getGenerator();
		generator.addProvider(
			event.includeClient(),
			(DataProvider.Factory<DataProvider>) CactusLanguageProvider.EnglishUS::new
		);
		generator.addProvider(
			event.includeClient(),
			(DataProvider.Factory<DataProvider>) output -> new CactusItemModelProvider(output, event.getExistingFileHelper())
		);
	}
}
