package dev.mariany.integrationsoplenty.resource.conditions;

import com.mojang.serialization.MapCodec;
import dev.mariany.integrationsoplenty.IntegrationsOPlenty;
import dev.mariany.integrationsoplenty.resource.conditions.conditions.ModuleResourceCondition;
import net.fabricmc.fabric.api.resource.conditions.v1.ResourceCondition;
import net.fabricmc.fabric.api.resource.conditions.v1.ResourceConditionType;
import net.fabricmc.fabric.api.resource.conditions.v1.ResourceConditions;

public class IOPResourceConditionTypes {
    public static final ResourceConditionType<ModuleResourceCondition> MODULE = createResourceConditionType(
            "module",
            ModuleResourceCondition.CODEC
    );

    private IOPResourceConditionTypes() {
    }

    private static <T extends ResourceCondition> ResourceConditionType<T> createResourceConditionType(
            String name,
            MapCodec<T> codec
    ) {
        ResourceConditionType<T> resourceConditionType = ResourceConditionType.create(
                IntegrationsOPlenty.id(name),
                codec
        );
        ResourceConditions.register(resourceConditionType);
        return resourceConditionType;
    }

    public static void bootstrap() {
        IntegrationsOPlenty.bootstrapLog("Resource Condition Types");
    }
}
