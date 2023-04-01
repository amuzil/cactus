package com.amuzil.cactus.data;

import com.amuzil.cactus.Cactus;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class CactusItemModelProvider extends ItemModelProvider {
	public CactusItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
		super(output, Cactus.MODID, existingFileHelper);
	}

	@Override
	protected void registerModels() {
		generated("cactus_bowl");
		generated("cactus_juice");
	}

	private void generated(String name) {
		singleTexture(name,
			mcLoc(ITEM_FOLDER + "/generated"),
			"layer0",
			new ResourceLocation(Cactus.MODID, ITEM_FOLDER + "/" + name)
		);
	}
}
