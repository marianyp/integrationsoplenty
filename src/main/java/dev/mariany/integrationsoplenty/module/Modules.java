package dev.mariany.integrationsoplenty.module;

import dev.mariany.integrationsoplenty.IntegrationsOPlenty;
import dev.mariany.integrationsoplenty.config.ConfigHandler;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.function.BooleanSupplier;

public class Modules {
    private static final ConfigHandler<ModuleConfig> CONFIG = new ConfigHandler<>(
            "integrationsoplenty-modules",
            new ModuleConfig()
    );

    private static final Map<Identifier, Module> MAP = new HashMap<>();

    public static final Module BEEHIVES = register("beehives");
    public static final Module FARMERS_DELIGHT = registerWithDependency(
            "farmers_delight",
            "farmersdelight"
    );

    private Modules() {
    }

    private static Module registerWithDependency(String name, String requiredMod) {
        return register(name, () -> FabricLoader.getInstance().isModLoaded(requiredMod));
    }

    private static Module register(String name) {
        return register(name, () -> true);
    }

    private static Module register(String name, BooleanSupplier rawSupplier) {
        Identifier identifier = IntegrationsOPlenty.id(name);

        if (MAP.containsKey(identifier)) {
            throw new Error("Module with name %s already exists".formatted(name));
        }

        BooleanSupplier supplier = () -> rawSupplier.getAsBoolean() && configAllows(identifier);

        Module module = new Module(identifier, supplier);

        MAP.put(identifier, module);

        return module;
    }

    private static ModuleConfig getConfig() {
        return CONFIG.getConfig();
    }

    private static boolean configAllows(Identifier id) {
        Boolean override = getConfig().moduleOverrides.get(id.toString());
        return override == null || override;
    }

    public static Module getModule(@NotNull Identifier identifier) {
        if (!MAP.containsKey(identifier)) {
            throw new Error("Module %s does not exist".formatted(identifier));
        }

        return MAP.get(identifier);
    }

    public static void bootstrap() {
        IntegrationsOPlenty.bootstrapLog("Modules");

        CONFIG.loadConfig();
        updateConfig();
    }

    private static void updateConfig() {
        boolean dirty = false;

        Map<String, Boolean> moduleOverrides = getConfig().moduleOverrides;

        for (
                Iterator<Map.Entry<String, Boolean>> iterator = moduleOverrides.entrySet().iterator();
                iterator.hasNext();
        ) {
            Map.Entry<String, Boolean> entry = iterator.next();
            String key = entry.getKey();

            if (!MAP.containsKey(Identifier.of(key))) {
                iterator.remove();
                dirty = true;
            }
        }

        for (Map.Entry<Identifier, Module> entry : MAP.entrySet()) {
            Identifier key = entry.getKey();
            String stringKey = key.toString();

            if (!moduleOverrides.containsKey(stringKey)) {
                moduleOverrides.put(stringKey, entry.getValue().isEnabled());
                dirty = true;
            }
        }

        if (dirty) {
            CONFIG.saveConfig();
        }
    }
}
