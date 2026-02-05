package dev.mariany.integrationsoplenty.block;

import com.google.common.collect.ImmutableList;
import dev.mariany.integrationsoplenty.IntegrationsOPlenty;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;

import java.util.EnumMap;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;

public record BOPBlockSet(
        Block dead,
        Block empyreal,
        Block fir,
        Block hellbark,
        Block jacaranda,
        Block magic,
        Block mahogany,
        Block maple,
        Block palm,
        Block pine,
        Block redwood,
        Block umbran,
        Block willow
) {
    public BOPBlockSet(EnumMap<BOPWood, Block> woodMap) {
        this(
                woodMap.get(BOPWood.DEAD),
                woodMap.get(BOPWood.EMPYREAL),
                woodMap.get(BOPWood.FIR),
                woodMap.get(BOPWood.HELLBARK),
                woodMap.get(BOPWood.JACARANDA),
                woodMap.get(BOPWood.MAGIC),
                woodMap.get(BOPWood.MAHOGANY),
                woodMap.get(BOPWood.MAPLE),
                woodMap.get(BOPWood.PALM),
                woodMap.get(BOPWood.PINE),
                woodMap.get(BOPWood.REDWOOD),
                woodMap.get(BOPWood.UMBRAN),
                woodMap.get(BOPWood.WILLOW)
        );
    }

    public ImmutableList<Block> getAll() {
        return ImmutableList.of(
                this.dead,
                this.empyreal,
                this.fir,
                this.hellbark,
                this.jacaranda,
                this.magic,
                this.mahogany,
                this.maple,
                this.palm,
                this.pine,
                this.redwood,
                this.umbran,
                this.willow
        );
    }

    public Block getRandom() {
        List<Block> blocks = this.getAll();
        return blocks.get(ThreadLocalRandom.current().nextInt(blocks.size()));
    }

    public void forEach(Consumer<Block> consumer) {
        consumer.accept(this.dead);
        consumer.accept(this.empyreal);
        consumer.accept(this.fir);
        consumer.accept(this.hellbark);
        consumer.accept(this.jacaranda);
        consumer.accept(this.magic);
        consumer.accept(this.mahogany);
        consumer.accept(this.maple);
        consumer.accept(this.palm);
        consumer.accept(this.pine);
        consumer.accept(this.redwood);
        consumer.accept(this.umbran);
        consumer.accept(this.willow);
    }

    public void forEach(BiConsumer<Block, BOPWood> consumer) {
        consumer.accept(this.dead, BOPWood.DEAD);
        consumer.accept(this.empyreal, BOPWood.EMPYREAL);
        consumer.accept(this.fir, BOPWood.FIR);
        consumer.accept(this.hellbark, BOPWood.HELLBARK);
        consumer.accept(this.jacaranda, BOPWood.JACARANDA);
        consumer.accept(this.magic, BOPWood.MAGIC);
        consumer.accept(this.mahogany, BOPWood.MAHOGANY);
        consumer.accept(this.maple, BOPWood.MAPLE);
        consumer.accept(this.palm, BOPWood.PALM);
        consumer.accept(this.pine, BOPWood.PINE);
        consumer.accept(this.redwood, BOPWood.REDWOOD);
        consumer.accept(this.umbran, BOPWood.UMBRAN);
        consumer.accept(this.willow, BOPWood.WILLOW);
    }

    public static BOPBlockSet create(
            String baseName,
            Function<AbstractBlock.Settings, Block> factory,
            AbstractBlock.Settings settings
    ) {
        EnumMap<BOPWood, Block> byWood = new EnumMap<>(BOPWood.class);

        for (BOPWood wood : BOPWood.values()) {
            byWood.put(wood, register(wood.id(baseName), factory, addDynamicSettings(wood, settings)));
        }

        return new BOPBlockSet(byWood);
    }

    private static AbstractBlock.Settings addDynamicSettings(BOPWood wood, AbstractBlock.Settings settings) {
        settings.sounds(wood.getBlockSoundGroup());
        settings.mapColor(wood.getMapColor());

        if (wood.isBurnable()) {
            settings.burnable();
        }

        return settings;
    }

    private static Block register(
            String name,
            Function<AbstractBlock.Settings, Block> factory,
            AbstractBlock.Settings settings
    ) {
        Block block = Blocks.register(keyOf(name), factory, settings);
        Items.register(block);
        return block;
    }

    private static RegistryKey<Block> keyOf(String id) {
        return RegistryKey.of(RegistryKeys.BLOCK, IntegrationsOPlenty.id(id));
    }
}
