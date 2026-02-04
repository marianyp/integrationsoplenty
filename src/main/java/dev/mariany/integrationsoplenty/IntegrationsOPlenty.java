package dev.mariany.integrationsoplenty;

import dev.mariany.integrationsoplenty.block.IOPBlocks;
import dev.mariany.integrationsoplenty.module.Modules;
import dev.mariany.integrationsoplenty.resource.conditions.IOPResourceConditionTypes;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IntegrationsOPlenty implements ModInitializer {
    public static final String MOD_ID = "integrationsoplenty";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public static Identifier id(String resource) {
        return Identifier.of(MOD_ID, resource);
    }

    public static void bootstrapLog(String type) {
        if (FabricLoader.getInstance().isDevelopmentEnvironment()) {
            LOGGER.info("Registering {}", type);
        }
    }

    @Override
    public void onInitialize() {
        Modules.bootstrap();
        IOPResourceConditionTypes.bootstrap();
        IOPBlocks.bootstrap();
    }
}