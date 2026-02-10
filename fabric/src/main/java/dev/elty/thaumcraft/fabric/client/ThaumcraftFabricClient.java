package dev.elty.thaumcraft.fabric.client;

import dev.elty.thaumcraft.ThaumcraftClient;
import net.fabricmc.api.ClientModInitializer;

public final class ThaumcraftFabricClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ThaumcraftClient.init();
    }
}
