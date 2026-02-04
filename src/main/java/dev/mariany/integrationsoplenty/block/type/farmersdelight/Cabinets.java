package dev.mariany.integrationsoplenty.block.type.farmersdelight;

import dev.mariany.integrationsoplenty.IntegrationsOPlenty;
import dev.mariany.integrationsoplenty.block.BOPBlockSet;
import dev.mariany.integrationsoplenty.block.IOPBlocks;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Blocks;
import vectorwing.farmersdelight.common.block.CabinetBlock;
import vectorwing.farmersdelight.common.registry.ModBlockEntityTypes;

public class Cabinets {
    public static final BOPBlockSet BLOCK_SET = BOPBlockSet.create(
            "cabinet",
            CabinetBlock::new,
            AbstractBlock.Settings.copy(Blocks.BARREL)
    );

    private Cabinets() {
    }

    public static void bootstrap() {
        IntegrationsOPlenty.bootstrapLog("Cabinets");

        IOPBlocks.registerItemGroup("cabinets", BLOCK_SET);
        IOPBlocks.registerBlockEntities(ModBlockEntityTypes.CABINET.get(), BLOCK_SET);
    }
}
