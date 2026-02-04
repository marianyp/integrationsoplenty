package dev.mariany.integrationsoplenty.datagen;

import dev.mariany.integrationsoplenty.block.BOPBlockSet;
import dev.mariany.integrationsoplenty.block.type.Beehives;
import dev.mariany.integrationsoplenty.block.type.farmersdelight.Cabinets;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.block.Block;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.TagBuilder;
import net.minecraft.registry.tag.TagKey;

import java.util.concurrent.CompletableFuture;

public class IOPBlockTagProvider extends FabricTagProvider.BlockTagProvider {
    public IOPBlockTagProvider(
            FabricDataOutput output,
            CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture
    ) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        addOptionalAll(BlockTags.AXE_MINEABLE, Cabinets.BLOCK_SET);
        addOptionalAll(BlockTags.AXE_MINEABLE, Beehives.BLOCK_SET);
        addOptionalAll(BlockTags.BEEHIVES, Beehives.BLOCK_SET);
    }

    private void addOptionalAll(TagKey<Block> tag, BOPBlockSet blockSet) {
        TagBuilder builder = getTagBuilder(tag);
        blockSet.forEach(block -> builder.addOptional(Registries.BLOCK.getId(block)));
    }
}
