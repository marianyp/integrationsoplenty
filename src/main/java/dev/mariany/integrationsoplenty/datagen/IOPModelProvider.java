package dev.mariany.integrationsoplenty.datagen;

import dev.mariany.integrationsoplenty.block.type.Beehives;
import dev.mariany.integrationsoplenty.block.type.farmersdelight.Cabinets;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.block.Block;
import net.minecraft.client.data.*;
import net.minecraft.client.render.model.json.WeightedVariant;
import net.minecraft.state.property.Properties;
import net.minecraft.util.Identifier;

public class IOPModelProvider extends FabricModelProvider {
    public IOPModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        registerBeehives(blockStateModelGenerator);
        registerCabinets(blockStateModelGenerator);
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
    }

    private static void registerBeehives(BlockStateModelGenerator blockStateModelGenerator) {
        Beehives.BLOCK_SET.forEach(block -> registerBeehive(blockStateModelGenerator, block));
    }

    private static void registerBeehive(BlockStateModelGenerator blockStateModelGenerator, Block block) {
        blockStateModelGenerator.registerBeehive(block, TextureMap::sideFrontEnd);
    }

    private static void registerCabinets(BlockStateModelGenerator blockStateModelGenerator) {
        Cabinets.BLOCK_SET.forEach(block -> registerCabinet(blockStateModelGenerator, block));
    }

    private static void registerCabinet(BlockStateModelGenerator blockStateModelGenerator, Block cabinet) {
        WeightedVariant defaultVariant = BlockStateModelGenerator.createWeightedVariant(
                TexturedModel.ORIENTABLE.upload(cabinet, blockStateModelGenerator.modelCollector)
        );

        Identifier openTexture = TextureMap.getSubId(cabinet, "_front_open");

        WeightedVariant openVariant = BlockStateModelGenerator.createWeightedVariant(
                TexturedModel.ORIENTABLE.get(cabinet)
                                        .textures(textures -> textures.put(TextureKey.FRONT, openTexture))
                                        .upload(cabinet, "_open", blockStateModelGenerator.modelCollector)
        );

        blockStateModelGenerator.blockStateCollector
                .accept(
                        VariantsBlockModelDefinitionCreator
                                .of(cabinet)
                                .with(
                                        BlockStateModelGenerator.createBooleanModelMap(
                                                Properties.OPEN,
                                                openVariant,
                                                defaultVariant
                                        )
                                )
                                .apply(BlockStateModelGenerator.NORTH_DEFAULT_HORIZONTAL_ROTATION_OPERATIONS)
                );
    }
}
