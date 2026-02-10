package dev.elty.thaumcraft.generation;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import dev.elty.thaumcraft.Thaumcraft;
import dev.elty.thaumcraft.generation.features.CrystalCaveFeature;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.feature.Feature;

public class ModFeatures {


    public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(Thaumcraft.MOD_ID, Registries.FEATURE);

    public static RegistrySupplier<CrystalCaveFeature> CRYSTAL_CAVE = FEATURES.register("crystal_cave", CrystalCaveFeature::new);

    public static void register() {
        FEATURES.register();
    }

}
