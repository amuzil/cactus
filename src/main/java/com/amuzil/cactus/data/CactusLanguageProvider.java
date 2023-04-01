package com.amuzil.cactus.data;

import com.amuzil.cactus.Cactus;
import com.amuzil.cactus.effect.CactusEffects;
import com.amuzil.cactus.item.CactusItems;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.LanguageProvider;

public abstract class CactusLanguageProvider extends LanguageProvider {
	protected CactusLanguageProvider(PackOutput output, String locale) {
		super(output, Cactus.MODID, locale);
	}

	public static class EnglishUS extends CactusLanguageProvider {
		public EnglishUS(PackOutput output) {
			super(output, "en_us");
		}

		@Override
		protected void addTranslations() {
			add(CactusItems.CACTUS_BOWL.get(), "Cactus Bowl");
			add(CactusItems.CACTUS_JUICE.get(), "Cactus Juice");
			add(CactusEffects.QUENCHED.get(), "Quenched");
		}
	}
}
