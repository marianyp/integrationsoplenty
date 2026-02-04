package dev.mariany.integrationsoplenty.block;

import biomesoplenty.api.block.BOPBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.MapColor;

public enum BOPWood {
    DEAD(
            "dead",
            MapColor.GRAY,
            true,
            BOPBlocks.DEAD_PLANKS,
            BOPBlocks.DEAD_SLAB,
            BOPBlocks.DEAD_TRAPDOOR
    ),

    EMPYREAL(
            "empyreal",
            MapColor.OFF_WHITE,
            true,
            BOPBlocks.EMPYREAL_PLANKS,
            BOPBlocks.EMPYREAL_SLAB,
            BOPBlocks.EMPYREAL_TRAPDOOR
    ),

    FIR(
            "fir",
            MapColor.TERRACOTTA_LIGHT_GRAY,
            true,
            BOPBlocks.FIR_PLANKS,
            BOPBlocks.FIR_SLAB,
            BOPBlocks.FIR_TRAPDOOR
    ),

    HELLBARK(
            "hellbark",
            MapColor.LIGHT_GRAY,
            false,
            BOPBlocks.HELLBARK_PLANKS,
            BOPBlocks.HELLBARK_SLAB,
            BOPBlocks.HELLBARK_TRAPDOOR
    ),

    JACARANDA(
            "jacaranda",
            MapColor.TERRACOTTA_LIGHT_GRAY,
            true,
            BOPBlocks.JACARANDA_PLANKS,
            BOPBlocks.JACARANDA_SLAB,
            BOPBlocks.JACARANDA_TRAPDOOR
    ),

    MAGIC(
            "magic",
            MapColor.TERRACOTTA_LIGHT_BLUE,
            true,
            BOPBlocks.MAGIC_PLANKS,
            BOPBlocks.MAGIC_SLAB,
            BOPBlocks.MAGIC_TRAPDOOR
    ),

    MAHOGANY(
            "mahogany",
            MapColor.DIRT_BROWN,
            true,
            BOPBlocks.MAHOGANY_PLANKS,
            BOPBlocks.MAHOGANY_SLAB,
            BOPBlocks.MAHOGANY_TRAPDOOR
    ),

    MAPLE(
            "maple",
            MapColor.TERRACOTTA_BROWN,
            true,
            BOPBlocks.MAPLE_PLANKS,
            BOPBlocks.MAPLE_SLAB,
            BOPBlocks.MAPLE_TRAPDOOR
    ),

    PALM(
            "palm",
            MapColor.SPRUCE_BROWN,
            true,
            BOPBlocks.PALM_PLANKS,
            BOPBlocks.PALM_SLAB,
            BOPBlocks.PALM_TRAPDOOR
    ),

    PINE(
            "pine",
            MapColor.DIRT_BROWN,
            true,
            BOPBlocks.PINE_PLANKS,
            BOPBlocks.PINE_SLAB,
            BOPBlocks.PINE_TRAPDOOR
    ),

    REDWOOD(
            "redwood",
            MapColor.TERRACOTTA_ORANGE,
            true,
            BOPBlocks.REDWOOD_PLANKS,
            BOPBlocks.REDWOOD_SLAB,
            BOPBlocks.REDWOOD_TRAPDOOR
    ),

    UMBRAN(
            "umbran",
            MapColor.TERRACOTTA_BLUE,
            true,
            BOPBlocks.UMBRAN_PLANKS,
            BOPBlocks.UMBRAN_SLAB,
            BOPBlocks.UMBRAN_TRAPDOOR
    ),

    WILLOW(
            "willow",
            MapColor.TERRACOTTA_LIME,
            true,
            BOPBlocks.WILLOW_PLANKS,
            BOPBlocks.WILLOW_SLAB,
            BOPBlocks.WILLOW_TRAPDOOR
    );

    private final String prefix;
    private final MapColor mapColor;
    private final boolean burnable;
    private final Block plank;
    private final Block slab;
    private final Block trapdoor;

    BOPWood(String prefix, MapColor mapColor, boolean burnable, Block plank, Block slab, Block trapdoor) {
        this.prefix = prefix;
        this.mapColor = mapColor;
        this.burnable = burnable;
        this.plank = plank;
        this.slab = slab;
        this.trapdoor = trapdoor;
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
}