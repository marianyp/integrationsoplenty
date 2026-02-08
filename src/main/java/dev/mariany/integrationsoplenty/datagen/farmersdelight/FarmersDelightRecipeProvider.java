package dev.mariany.integrationsoplenty.datagen.farmersdelight;

import dev.mariany.integrationsoplenty.block.BOPWood;
import dev.mariany.integrationsoplenty.block.type.farmersdelight.Cabinets;
import dev.mariany.integrationsoplenty.datagen.ModuleRecipeProvider;
import dev.mariany.integrationsoplenty.module.Modules;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.block.Block;
import net.minecraft.data.recipe.RecipeExporter;
import net.minecraft.data.recipe.RecipeGenerator;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class FarmersDelightRecipeProvider extends ModuleRecipeProvider {
    public FarmersDelightRecipeProvider(
            FabricDataOutput output,
            CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture
    ) {
        super(Modules.FARMERS_DELIGHT, output, registriesFuture);
    }

    @Override
    protected RecipeGenerator createRecipeGenerator(
            RegistryWrapper.WrapperLookup registryLookup,
            RecipeExporter exporter
    ) {
        return new RecipeGenerator(registryLookup, exporter) {
            @Override
            public void generate() {
                this.createCabinetRecipes();
            }

            private void createCabinetRecipes() {
                Cabinets.BLOCK_SET.forEach(this::createCabinetRecipe);
            }

            private void createCabinetRecipe(Block block, BOPWood wood) {
                Block trapdoor = wood.getTrapdoor();

                this.createShaped(RecipeCategory.MISC, block)
                    .group("fd_cabinet")
                    .input('S', wood.getSlab())
                    .input('T', trapdoor)
                    .pattern("SSS")
                    .pattern("T T")
                    .pattern("SSS")
                    .criterion(hasItem(trapdoor), this.conditionsFromItem(trapdoor))
                    .offerTo(this.exporter);
            }
        };
    }
}
