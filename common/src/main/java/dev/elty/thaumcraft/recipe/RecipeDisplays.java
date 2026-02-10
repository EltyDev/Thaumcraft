package dev.elty.thaumcraft.recipe;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import dev.elty.thaumcraft.Thaumcraft;
import dev.elty.thaumcraft.recipe.transmutation.TransmutationRecipeDisplay;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.crafting.display.RecipeDisplay;

public class RecipeDisplays {

    public static final DeferredRegister<RecipeDisplay.Type<?>> RECIPE_DISPLAY_TYPES = DeferredRegister.create(Thaumcraft.MOD_ID, Registries.RECIPE_DISPLAY);


    public static final RegistrySupplier<RecipeDisplay.Type<TransmutationRecipeDisplay>> TRANSMUTATION_RECIPE_DISPLAY = RECIPE_DISPLAY_TYPES.register(
            "transmutation",
            () -> new RecipeDisplay.Type<>(TransmutationRecipeDisplay.MAP_CODEC, TransmutationRecipeDisplay.STREAM_CODEC)
    );


    public static void register() {
        RECIPE_DISPLAY_TYPES.register();
    }


}
