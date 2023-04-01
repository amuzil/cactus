package com.amuzil.cactus.network;

import com.amuzil.cactus.Cactus;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CactusMessages {
	public static final String CHANNEL_NAME = "cactus";
	private static final String PROTOCOL_VERSION = "1";
	public static final SimpleChannel INSTANCE = NetworkRegistry.newSimpleChannel(
		new ResourceLocation(Cactus.MODID, CHANNEL_NAME),
		() -> PROTOCOL_VERSION,
		PROTOCOL_VERSION::equals,
		PROTOCOL_VERSION::equals
	);

	public static void register() {
		INSTANCE.registerMessage(0, UnquenchedMessage.class, UnquenchedMessage::encode, UnquenchedMessage::decode, UnquenchedMessage::handle);
	}
}
