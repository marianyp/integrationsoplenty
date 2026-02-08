package dev.mariany.integrationsoplenty.datagen;

import dev.mariany.integrationsoplenty.module.Module;
import dev.mariany.integrationsoplenty.resource.conditions.conditions.ModuleResourceCondition;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.recipe.RecipeExporter;
import net.minecraft.data.recipe.RecipeGenerator;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public abstract class ModuleRecipeProvider extends FabricRecipeProvider {
    private final Module module;

    public ModuleRecipeProvider(
            Module module,
            FabricDataOutput output,
            CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture
    ) {
        super(output, registriesFuture);
        this.module = module;
    }

    protected abstract RecipeGenerator createRecipeGenerator(
            RegistryWrapper.WrapperLookup registryLookup,
            RecipeExporter exporter
    );

    @Override
    protected final RecipeGenerator getRecipeGenerator(
            RegistryWrapper.WrapperLookup registryLookup,
            RecipeExporter exporter
    ) {
        return this.createRecipeGenerator(
                registryLookup,
                this.withConditions(exporter, new ModuleResourceCondition(this.module))
        );
    }

    @Override
    public String getName() {
        return this.module.id().getPath() + "/" + "Recipes";
    }
}
