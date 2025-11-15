package dev.elty.thaumcraft.generation;

import dev.architectury.registry.level.biome.BiomeModifications;
import dev.elty.thaumcraft.Thaumcraft;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

public class ModFeatures {

    public static final ResourceKey<ConfiguredFeature<?, ?>> CRYSTAL_CONFIGURED = ResourceKey.create(Registries.CONFIGURED_FEATURE, ResourceLocation.fromNamespaceAndPath(Thaumcraft.MOD_ID, "crystal"));
    public static final ResourceKey<PlacedFeature> CRYSTAL_PLACED = ResourceKey.create(Registries.PLACED_FEATURE, ResourceLocation.fromNamespaceAndPath(Thaumcraft.MOD_ID, "crystal"));


    public static void register() {
        BiomeModifications.addProperties((ctx, mutable) -> {
            if (!ctx.hasTag(BiomeTags.IS_OVERWORLD)) return;
            mutable.getGenerationProperties().addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, CRYSTAL_PLACED);
        });
    }

}
