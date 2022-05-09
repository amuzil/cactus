package com.amuzil.omegasource.cactus.data;

import com.amuzil.omegasource.cactus.Cactus;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class CactusItemModelProvider extends ItemModelProvider {
	public CactusItemModelProvider(DataGenerator generator, ExistingFileHelper existingFileHelper) {
		super(generator, Cactus.MODID, existingFileHelper);
	}

	@Override
	protected void registerModels() {
		generated("cactus_bowl");
		generated("cactus_juice");
	}

	private ItemModelBuilder generated(String name) {
		return singleTexture(name, mcLoc(ITEM_FOLDER + "/generated"), "layer0", new ResourceLocation(Cactus.MODID, ITEM_FOLDER + "/" + name));
	}
}