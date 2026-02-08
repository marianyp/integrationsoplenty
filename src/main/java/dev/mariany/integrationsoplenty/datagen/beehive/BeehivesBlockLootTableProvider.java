package dev.mariany.integrationsoplenty.datagen.beehive;

import dev.mariany.integrationsoplenty.block.type.Beehives;
import dev.mariany.integrationsoplenty.datagen.ModuleBlockLootTableProvider;
import dev.mariany.integrationsoplenty.module.Modules;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class BeehivesBlockLootTableProvider extends ModuleBlockLootTableProvider {
    public BeehivesBlockLootTableProvider(
            FabricDataOutput output,
            CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture
    ) {
        super(Modules.BEEHIVES, output, registriesFuture);
    }

    @Override
    public void generate() {
        Beehives.BLOCK_SET.forEach(block -> addDrop(block, this.beehiveDrops(block)));
    }
}