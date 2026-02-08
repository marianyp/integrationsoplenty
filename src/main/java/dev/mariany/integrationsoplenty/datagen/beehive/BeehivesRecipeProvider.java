package dev.mariany.integrationsoplenty.datagen.beehive;

import dev.mariany.integrationsoplenty.block.BOPWood;
import dev.mariany.integrationsoplenty.block.type.Beehives;
import dev.mariany.integrationsoplenty.datagen.ModuleRecipeProvider;
import dev.mariany.integrationsoplenty.module.Modules;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.block.Block;
import net.minecraft.data.recipe.RecipeExporter;
import net.minecraft.data.recipe.RecipeGenerator;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class BeehivesRecipeProvider extends ModuleRecipeProvider {
    public BeehivesRecipeProvider(
            FabricDataOutput output,
            CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture
    ) {
        super(Modules.BEEHIVES, output, registriesFuture);
    }

    @Override
    protected RecipeGenerator createRecipeGenerator(
            RegistryWrapper.WrapperLookup registryLookup,
            RecipeExporter exporter
    ) {
        return new RecipeGenerator(registryLookup, exporter) {
            @Override
            public void generate() {
                this.createBeehiveRecipes();
            }

            private void createBeehiveRecipes() {
                Beehives.BLOCK_SET.forEach(this::createBeehiveRecipe);
            }

            private void createBeehiveRecipe(Block block, BOPWood wood) {
                this.createShaped(RecipeCategory.DECORATIONS, block)
                    .input('P', wood.getPlank())
                    .input('H', Items.HONEYCOMB)
                    .pattern("PPP")
                    .pattern("HHH")
                    .pattern("PPP")
                    .criterion(hasItem(Items.HONEYCOMB), this.conditionsFromItem(Items.HONEYCOMB))
                    .offerTo(this.exporter);
            }
        };
    }
}
