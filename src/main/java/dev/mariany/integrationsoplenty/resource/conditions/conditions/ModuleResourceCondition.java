package dev.mariany.integrationsoplenty.resource.conditions.conditions;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import dev.mariany.integrationsoplenty.module.Module;
import dev.mariany.integrationsoplenty.module.Modules;
import dev.mariany.integrationsoplenty.resource.conditions.IOPResourceConditionTypes;
import net.fabricmc.fabric.api.resource.conditions.v1.ResourceCondition;
import net.fabricmc.fabric.api.resource.conditions.v1.ResourceConditionType;
import net.minecraft.registry.RegistryOps;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

public record ModuleResourceCondition(Module module) implements ResourceCondition {
    public static final MapCodec<ModuleResourceCondition> CODEC = RecordCodecBuilder.mapCodec(
            instance -> instance
                    .group(
                            Identifier.CODEC.fieldOf("module")
                                            .forGetter(condition -> condition.module().id())
                    )
                    .apply(instance, identifier -> new ModuleResourceCondition(Modules.getModule(identifier)))
    );

    @Override
    public ResourceConditionType<?> getType() {
        return IOPResourceConditionTypes.MODULE;
    }

    @Override
    public boolean test(@Nullable RegistryOps.RegistryInfoGetter registryInfo) {
        return this.module.isEnabled();
    }
}