package com.amuzil.cactus.network;

import com.amuzil.cactus.effect.ClientQuenchedEffectHandler;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class UnquenchedMessage {

	// Suppress warning S1172: Unused method parameter, as we must match the signature
	@SuppressWarnings("java:S1172")
	public static UnquenchedMessage decode(FriendlyByteBuf ignoredBuffer) {
		return new UnquenchedMessage();
	}


	// Suppress warning S1172: Unused method parameter, as we must match the signature
	@SuppressWarnings("java:S1172")
	public static void handle(UnquenchedMessage ignoredMessage, Supplier<NetworkEvent.Context> context) {
		context.get().enqueueWork(() ->
			DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> ClientQuenchedEffectHandler::removeShader)
		);

		context.get().setPacketHandled(true);
	}

	public void encode(FriendlyByteBuf ignoredBuffer) {
		// No data to send
	}
}
