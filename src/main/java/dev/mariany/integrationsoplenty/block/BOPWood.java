package dev.mariany.integrationsoplenty.block;

import biomesoplenty.api.block.BOPBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.MapColor;
import net.minecraft.sound.BlockSoundGroup;

public enum BOPWood {
    DEAD(
            "dead",
            MapColor.GRAY,
            true,
            BOPBlocks.DEAD_PLANKS,
            BOPBlocks.DEAD_SLAB,
            BOPBlocks.DEAD_TRAPDOOR,
            BlockSoundGroup.WOOD
    ),

    EMPYREAL(
            "empyreal",
            MapColor.OFF_WHITE,
            true,
            BOPBlocks.EMPYREAL_PLANKS,
            BOPBlocks.EMPYREAL_SLAB,
            BOPBlocks.EMPYREAL_TRAPDOOR,
            BlockSoundGroup.NETHER_WOOD
    ),

    FIR(
            "fir",
            MapColor.TERRACOTTA_LIGHT_GRAY,
            true,
            BOPBlocks.FIR_PLANKS,
            BOPBlocks.FIR_SLAB,
            BOPBlocks.FIR_TRAPDOOR,
            BlockSoundGroup.WOOD
    ),

    HELLBARK(
            "hellbark",
            MapColor.LIGHT_GRAY,
            false,
            BOPBlocks.HELLBARK_PLANKS,
            BOPBlocks.HELLBARK_SLAB,
            BOPBlocks.HELLBARK_TRAPDOOR,
            BlockSoundGroup.NETHER_WOOD
    ),

    JACARANDA(
            "jacaranda",
            MapColor.TERRACOTTA_LIGHT_GRAY,
            true,
            BOPBlocks.JACARANDA_PLANKS,
            BOPBlocks.JACARANDA_SLAB,
            BOPBlocks.JACARANDA_TRAPDOOR,
            BlockSoundGroup.CHERRY_WOOD
    ),

    MAGIC(
            "magic",
            MapColor.TERRACOTTA_LIGHT_BLUE,
            true,
            BOPBlocks.MAGIC_PLANKS,
            BOPBlocks.MAGIC_SLAB,
            BOPBlocks.MAGIC_TRAPDOOR,
            BlockSoundGroup.CHERRY_WOOD
    ),

    MAHOGANY(
            "mahogany",
            MapColor.DIRT_BROWN,
            true,
            BOPBlocks.MAHOGANY_PLANKS,
            BOPBlocks.MAHOGANY_SLAB,
            BOPBlocks.MAHOGANY_TRAPDOOR,
            BlockSoundGroup.WOOD
    ),

    MAPLE(
            "maple",
            MapColor.TERRACOTTA_BROWN,
            true,
            BOPBlocks.MAPLE_PLANKS,
            BOPBlocks.MAPLE_SLAB,
            BOPBlocks.MAPLE_TRAPDOOR,
            BlockSoundGroup.CHERRY_WOOD
    ),

    PALM(
            "palm",
            MapColor.SPRUCE_BROWN,
            true,
            BOPBlocks.PALM_PLANKS,
            BOPBlocks.PALM_SLAB,
            BOPBlocks.PALM_TRAPDOOR,
            BlockSoundGroup.WOOD
    ),

    PINE(
            "pine",
            MapColor.DIRT_BROWN,
            true,
            BOPBlocks.PINE_PLANKS,
            BOPBlocks.PINE_SLAB,
            BOPBlocks.PINE_TRAPDOOR,
            BlockSoundGroup.WOOD
    ),

    REDWOOD(
            "redwood",
            MapColor.TERRACOTTA_ORANGE,
            true,
            BOPBlocks.REDWOOD_PLANKS,
            BOPBlocks.REDWOOD_SLAB,
            BOPBlocks.REDWOOD_TRAPDOOR,
            BlockSoundGroup.WOOD
    ),

    UMBRAN(
            "umbran",
            MapColor.TERRACOTTA_BLUE,
            true,
            BOPBlocks.UMBRAN_PLANKS,
            BOPBlocks.UMBRAN_SLAB,
            BOPBlocks.UMBRAN_TRAPDOOR,
            BlockSoundGroup.NETHER_WOOD
    ),

    WILLOW(
            "willow",
            MapColor.TERRACOTTA_LIME,
            true,
            BOPBlocks.WILLOW_PLANKS,
            BOPBlocks.WILLOW_SLAB,
            BOPBlocks.WILLOW_TRAPDOOR,
            BlockSoundGroup.WOOD
    );

    private final String prefix;
    private final MapColor mapColor;
    private final boolean burnable;
    private final Block plank;
    private final Block slab;
    private final Block trapdoor;
    private final BlockSoundGroup blockSoundGroup;

    BOPWood(
            String prefix,
            MapColor mapColor,
            boolean burnable,
            Block plank,
            Block slab,
            Block trapdoor,
            BlockSoundGroup blockSoundGroup
    ) {
        this.prefix = prefix;
        this.mapColor = mapColor;
        this.burnable = burnable;
        this.plank = plank;
        this.slab = slab;
        this.trapdoor = trapdoor;
        this.blockSoundGroup = blockSoundGroup;
    }

    public String id(String baseName) {
        return this.prefix + "_" + baseName;
    }

    public MapColor getMapColor() {
        return this.mapColor;
    }

    public boolean isBurnable() {
        return this.burnable;
    }

    public Block getPlank() {
        return this.plank;
    }

    public Block getSlab() {
        return this.slab;
    }

    public Block getTrapdoor() {
        return this.trapdoor;
    }

    public BlockSoundGroup getBlockSoundGroup() {
        return this.blockSoundGroup;
    }
}