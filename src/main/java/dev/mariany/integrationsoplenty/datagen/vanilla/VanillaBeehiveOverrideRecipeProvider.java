package dev.mariany.integrationsoplenty.datagen.vanilla;

import dev.mariany.integrationsoplenty.datagen.ModuleRecipeProvider;
import dev.mariany.integrationsoplenty.module.Modules;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.block.Blocks;
import net.minecraft.data.recipe.RecipeExporter;
import net.minecraft.data.recipe.RecipeGenerator;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.Identifier;

import java.util.concurrent.CompletableFuture;

public class VanillaBeehiveOverrideRecipeProvider extends ModuleRecipeProvider {
    public VanillaBeehiveOverrideRecipeProvider(
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
                Item honeyComb = Items.HONEYCOMB;

                this.createShaped(RecipeCategory.DECORATIONS, Blocks.BEEHIVE)
                    .input('P', Blocks.OAK_PLANKS)
                    .input('H', honeyComb)
                    .pattern("PPP")
                    .pattern("HHH")
                    .pattern("PPP")
                    .criterion(hasItem(honeyComb), this.conditionsFromItem(honeyComb))
                    .offerTo(this.exporter);
            }
        };
    }

    @Override
    protected Identifier getRecipeIdentifier(Identifier identifier) {
        return Identifier.ofVanilla(identifier.getPath());
    }

    @Override
    public String getName() {
        return "Integrations O' Plenty Beehive Recipe Override";
    }
}
