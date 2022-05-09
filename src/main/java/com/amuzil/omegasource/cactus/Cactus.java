package com.amuzil.omegasource.cactus;

import com.amuzil.omegasource.cactus.capability.CapabilityCactusJuice;
import com.amuzil.omegasource.cactus.data.CactusItemModelProvider;
import com.amuzil.omegasource.cactus.data.CactusLanguageProvider;
import com.amuzil.omegasource.cactus.item.CactusItems;
import lombok.Getter;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(Cactus.MODID)
public class Cactus {
	public static final String MODID = "cactus";

	@Getter
	private static Cactus instance;

	// Directly reference a log4j logger.
	@Getter
	private static Logger logger = LogManager.getLogger();

	public Cactus() {
		instance = this;

		// Register the setup method for modloading
//		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
//		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::gatherData);

		// Register ourselves for server and other game events we are interested in
		MinecraftForge.EVENT_BUS.register(this);
		FMLJavaModLoadingContext.get().getModEventBus().register(this);

		// Register our deferred registrars
		CactusItems.register();
		FMLJavaModLoadingContext.get().getModEventBus().addListener(CapabilityCactusJuice::register);
	}

	@SubscribeEvent
	public void setup(final FMLCommonSetupEvent event) {
		// Do setup stuff
	}

	@SubscribeEvent
	public void gatherData(final GatherDataEvent event) {
		DataGenerator generator = event.getGenerator();
		generator.addProvider(new CactusLanguageProvider.en_us(generator));
		generator.addProvider(new CactusItemModelProvider(generator, event.getExistingFileHelper()));
	}
}
