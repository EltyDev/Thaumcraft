package dev.elty.thaumcraft.fabric;

import dev.elty.thaumcraft.Thaumcraft;
import net.fabricmc.api.ModInitializer;

public final class ThaumcraftFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        Thaumcraft.init();
    }
}
