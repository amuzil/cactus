package com.amuzil.omegasource.cactus.data;

import com.amuzil.omegasource.cactus.Cactus;
import com.amuzil.omegasource.cactus.item.CactusItems;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.LanguageProvider;

public abstract class CactusLanguageProvider extends LanguageProvider {
	public static class en_us extends CactusLanguageProvider {
		public en_us(DataGenerator gen) {
			super(gen, "en_us");
		}

		@Override
		protected void addTranslations() {
			add(CactusItems.CACTUS_BOWL.get(), "Cactus Bowl");
			add(CactusItems.CACTUS_JUICE.get(), "Cactus Juice");
		}
	}

	public CactusLanguageProvider(DataGenerator dataGenerator, String locale) {
		super(dataGenerator, Cactus.MODID, locale);
	}
}
