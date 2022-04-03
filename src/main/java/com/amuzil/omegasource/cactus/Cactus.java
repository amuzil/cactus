package com.amuzil.omegasource.cactus;

import lombok.Getter;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
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
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);

		// Register ourselves for server and other game events we are interested in
		MinecraftForge.EVENT_BUS.register(this);
	}

	private void setup(final FMLCommonSetupEvent event) {
		// Do setup stuff
	}
}
