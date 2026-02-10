package dev.elty.thaumcraft.fabric;

import dev.architectury.registry.level.biome.BiomeModifications;
import dev.elty.thaumcraft.Thaumcraft;
import net.fabricmc.api.ModInitializer;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.GenerationStep;

public final class ThaumcraftFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        Thaumcraft.init();
    }
}
