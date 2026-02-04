package dev.mariany.integrationsoplenty;

import dev.mariany.integrationsoplenty.datagen.*;
import dev.mariany.integrationsoplenty.datagen.vanilla.VanillaBeehiveFallbackRecipeProvider;
import dev.mariany.integrationsoplenty.datagen.vanilla.VanillaBeehiveOverrideRecipeProvider;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

/**
 * FIXME: Occasionally fails due to a race condition with data generation and the Biomes O' Plenty mod initialization.
 */
public class IntegrationsOPlentyDataGenerator implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
        pack.addProvider(BeehivesRecipeProvider::new);
        pack.addProvider(FarmersDelightRecipeProvider::new);
        pack.addProvider(IOPBlockTagProvider::new);
        pack.addProvider(IOPModelProvider::new);
        pack.addProvider(VanillaBeehiveFallbackRecipeProvider::new);
        pack.addProvider(VanillaBeehiveOverrideRecipeProvider::new);
    }
}
