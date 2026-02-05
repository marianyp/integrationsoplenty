package dev.mariany.integrationsoplenty.block;

import com.google.common.collect.ImmutableSet;
import dev.mariany.integrationsoplenty.IntegrationsOPlenty;
import dev.mariany.integrationsoplenty.block.type.Beehives;
import dev.mariany.integrationsoplenty.block.type.farmersdelight.Cabinets;
import dev.mariany.integrationsoplenty.mixin.accessor.PointOfInterestTypeAccessor;
import dev.mariany.integrationsoplenty.mixin.accessor.PointOfInterestTypesAccessor;
import dev.mariany.integrationsoplenty.module.Modules;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.text.Text;
import net.minecraft.world.poi.PointOfInterestType;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class IOPBlocks {
    public static void bootstrap() {
        IntegrationsOPlenty.bootstrapLog("Blocks");

        if (Modules.BEEHIVES.isEnabled()) {
            Beehives.bootstrap();
        }

        if (Modules.FARMERS_DELIGHT.isEnabled()) {
            Cabinets.bootstrap();
        }
    }

    public static void registerItemGroup(String name, BOPBlockSet blockSet) {
        List<Block> blocks = blockSet.getAll();

        Block randomBlock = blocks.get(ThreadLocalRandom.current().nextInt(blocks.size()));
        Item icon = randomBlock.asItem();

        Registry.register(
                Registries.ITEM_GROUP,
                IntegrationsOPlenty.id(name),
                FabricItemGroup.builder()
                               .displayName(Text.translatable("itemGroup.integrationsoplenty." + name))
                               .icon(icon::getDefaultStack)
                               .entries(
                                       (context, entries) -> blockSet.forEach(
                                               block -> entries.add(block)
                                       )
                               )
                               .build()
        );
    }

    public static void registerFlammable(BOPBlockSet blockSet, int burnChance, int spreadChance) {
        blockSet.forEach((block, wood) -> {
            if (wood.isBurnable()) {
                FlammableBlockRegistry.getDefaultInstance().add(block, burnChance, spreadChance);
            }
        });
    }

    public static void registerBlockEntities(
            BlockEntityType<?> blockEntityType,
            BOPBlockSet blockSet
    ) {
        blockSet.forEach(blockEntityType::addSupportedBlock);
    }

    public static void registerPointOfInterestStates(RegistryKey<PointOfInterestType> poiKey, BOPBlockSet blockSet) {
        PointOfInterestType pointOfInterestType = Registries.POINT_OF_INTEREST_TYPE.get(poiKey);

        if (pointOfInterestType == null) {
            IntegrationsOPlenty.LOGGER.error(
                    "Unable to register states for beehive point of interest. Point of interest not yet registered."
            );

            return;
        }

        Set<BlockState> newStates = blockSet.getAll()
                                            .stream()
                                            .map(IOPBlocks::getStatesOfBlock)
                                            .flatMap(Collection::stream)
                                            .collect(Collectors.toUnmodifiableSet());

        Set<BlockState> states = Stream.of(newStates, pointOfInterestType.blockStates())
                                       .flatMap(Collection::stream)
                                       .collect(Collectors.toUnmodifiableSet());

        ((PointOfInterestTypeAccessor) (Object) pointOfInterestType).integrationsoplenty$setBlockStates(states);

        registerCachedStates(pointOfInterestType, newStates);
    }

    private static Set<BlockState> getStatesOfBlock(Block block) {
        return ImmutableSet.copyOf(block.getStateManager().getStates());
    }

    private static void registerCachedStates(PointOfInterestType pointOfInterestType, Set<BlockState> states) {
        RegistryEntry<PointOfInterestType> entry = Registries.POINT_OF_INTEREST_TYPE.getEntry(pointOfInterestType);
        PointOfInterestTypesAccessor.integrationsoplenty$registerStates(entry, states);
    }
}
