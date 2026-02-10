package dev.elty.thaumcraft.generation;

import dev.architectury.registry.level.biome.BiomeModifications;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.GenerationStep;

public class BiomeFeatures {

    public static void addFeatures() {

        BiomeModifications.addProperties((biomeContext, mutable) -> {
            mutable.getGenerationProperties().addFeature(
                    GenerationStep.Decoration.UNDERGROUND_ORES,
                    ModPlacedFeatures.CRYSTAL_PLACED
            );
        });

    }

}
