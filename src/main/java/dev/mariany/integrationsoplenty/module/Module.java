package dev.mariany.integrationsoplenty.module;

import net.minecraft.util.Identifier;

import java.util.function.BooleanSupplier;

public record Module(Identifier id, BooleanSupplier supplier) {
    public boolean isEnabled() {
        return supplier.getAsBoolean();
    }
}
