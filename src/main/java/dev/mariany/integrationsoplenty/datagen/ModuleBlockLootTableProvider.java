package dev.mariany.integrationsoplenty.datagen;

import dev.mariany.integrationsoplenty.module.Module;
import dev.mariany.integrationsoplenty.resource.conditions.conditions.ModuleResourceCondition;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.fabricmc.fabric.api.resource.conditions.v1.ResourceCondition;
import net.minecraft.loot.LootTable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;

public abstract class ModuleBlockLootTableProvider extends FabricBlockLootTableProvider {
    private final Module module;

    public ModuleBlockLootTableProvider(
            Module module,
            FabricDataOutput output,
            CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture
    ) {
        super(output, registriesFuture);
        this.module = module;
    }

    @Override
    public BiConsumer<RegistryKey<LootTable>, LootTable.Builder> withConditions(
            BiConsumer<RegistryKey<LootTable>, LootTable.Builder> exporter,
            ResourceCondition... conditions
    ) {
        return super.withConditions(exporter, new ModuleResourceCondition(this.module));
    }

    @Override
    public String getName() {
        return this.module.id().getPath() + "/" + super.getName();
    }
}
