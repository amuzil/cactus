package com.amuzil.omegasource.cactus.capability;

import com.amuzil.omegasource.cactus.Cactus;
import net.minecraft.core.Direction;
import net.minecraft.nbt.IntTag;
import net.minecraft.nbt.Tag;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.capabilities.*;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.text.html.parser.Entity;

@EventBusSubscriber(modid = Cactus.MODID, bus = Bus.FORGE)
public class CapabilityCactusJuice {
	public interface ICactusJuice {
		boolean isJuiced();

		int durationLeft();

		void juice(int ticks);

		void unjuice();
	}

	public static class CactusJuice implements ICactusJuice, INBTSerializable<Tag> {
		protected int duration;

		public CactusJuice() {
			this.duration = 0;
		}

		public CactusJuice(int duration) {
			this.duration = duration;
		}

		@Override
		public boolean isJuiced() {
			return duration > 0;
		}

		@Override
		public int durationLeft() {
			return duration;
		}

		@Override
		public void juice(int ticks) {
			this.duration += ticks;
		}

		@Override
		public void unjuice() {
			this.duration = 0;
		}

		@Override
		public Tag serializeNBT() {
			return IntTag.valueOf(duration);
		}

		@Override
		public void deserializeNBT(Tag nbt) {
			if (!(nbt instanceof IntTag intNbt)) {
				throw new IllegalArgumentException("Can not deserialize to an instance that isn't the default implementation");
			}
			this.duration = intNbt.getAsInt();
		}
	}

	public static class Provider implements ICapabilitySerializable<Tag> {
		protected CactusJuice cactusJuice = new CactusJuice();

		@Override
		public Tag serializeNBT() {
			return cactusJuice.serializeNBT();
		}

		@Override
		public void deserializeNBT(Tag nbt) {
			cactusJuice.deserializeNBT(nbt);
		}

		private final LazyOptional<CactusJuice> cactusJuiceHandler = LazyOptional.of(() -> cactusJuice);

		@NotNull
		@Override
		public <T> LazyOptional<T> getCapability(@NotNull Capability<T> capability, @Nullable Direction side) {
			if (capability == CACTUS_JUICE && cactusJuice != null)
				return cactusJuiceHandler.cast();
			return LazyOptional.empty();
		}
	}

	// TODO this mess

	public static final Capability<ICactusJuice> CACTUS_JUICE = CapabilityManager.get(new CapabilityToken<>() {});
	private static final ResourceLocation resourceLocation = new ResourceLocation(Cactus.MODID, "cactus_juice");

	@SubscribeEvent
	public static void register(RegisterCapabilitiesEvent event) {
		event.register(ICactusJuice.class);
	}

	@SubscribeEvent
	public static void attachEntityCapabilities(AttachCapabilitiesEvent<Entity> event) {
		event.addCapability(resourceLocation, new Provider());
	}
}
