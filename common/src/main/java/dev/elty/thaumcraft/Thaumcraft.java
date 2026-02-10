package dev.elty.thaumcraft;

import dev.architectury.event.events.common.InteractionEvent;
import dev.architectury.registry.level.biome.BiomeModifications;
import dev.elty.thaumcraft.block.ModBlocks;
import dev.elty.thaumcraft.generation.BiomeFeatures;
import dev.elty.thaumcraft.generation.ModFeatures;
import dev.elty.thaumcraft.item.ModItems;
import dev.elty.thaumcraft.network.NetworkHandler;
import dev.elty.thaumcraft.recipe.RecipeDisplays;
import dev.elty.thaumcraft.recipe.RecipeSerializers;
import dev.elty.thaumcraft.recipe.RecipeTypes;
import dev.elty.thaumcraft.recipe.transmutation.TransmutationRecipe;
import net.minecraft.world.level.levelgen.GenerationStep;

public final class Thaumcraft {

    public static final String MOD_ID = "thaumcraft";

    public static void init() {
        registerRecipes();
        NetworkHandler.register();
        ModBlocks.register();
        ModItems.register();
        ModFeatures.register();
        BiomeFeatures.addFeatures();
        InteractionEvent.RIGHT_CLICK_BLOCK.register(TransmutationRecipe::onRightClick);
    }

    public static void registerRecipes() {
        RecipeTypes.register();
        RecipeSerializers.register();
        RecipeDisplays.register();
    }
}
