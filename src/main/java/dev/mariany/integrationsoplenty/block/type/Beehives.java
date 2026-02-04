package dev.mariany.integrationsoplenty.block.type;

import dev.mariany.integrationsoplenty.IntegrationsOPlenty;
import dev.mariany.integrationsoplenty.block.BOPBlockSet;
import dev.mariany.integrationsoplenty.block.IOPBlocks;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BeehiveBlock;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.block.enums.NoteBlockInstrument;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.world.poi.PointOfInterestTypes;

public class Beehives {
    public static final BOPBlockSet BLOCK_SET = BOPBlockSet.create(
            "beehive",
            BeehiveBlock::new,
            AbstractBlock.Settings
                    .create()
                    .instrument(NoteBlockInstrument.BASS)
                    .strength(0.6F)
                    .sounds(BlockSoundGroup.WOOD)
    );

    private Beehives() {
    }

    public static void bootstrap() {
        IntegrationsOPlenty.bootstrapLog("Beehives");

        IOPBlocks.registerItemGroup("beehives", BLOCK_SET);
        IOPBlocks.registerFlammable(BLOCK_SET, 5, 20);
        IOPBlocks.registerBlockEntities(BlockEntityType.BEEHIVE, BLOCK_SET);
        IOPBlocks.registerPointOfInterestStates(PointOfInterestTypes.BEEHIVE, BLOCK_SET);
    }
}
