package dev.mariany.integrationsoplenty.datagen.farmersdelight;

import dev.mariany.integrationsoplenty.block.type.farmersdelight.Cabinets;
import dev.mariany.integrationsoplenty.datagen.ModuleBlockLootTableProvider;
import dev.mariany.integrationsoplenty.module.Modules;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class FarmersDelightBlockLootTableProvider extends ModuleBlockLootTableProvider {
    public FarmersDelightBlockLootTableProvider(
            FabricDataOutput output,
            CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture
    ) {
        super(Modules.FARMERS_DELIGHT, output, registriesFuture);
    }

    @Override
    public void generate() {
        Cabinets.BLOCK_SET.forEach(block -> addDrop(block));
    }
}
