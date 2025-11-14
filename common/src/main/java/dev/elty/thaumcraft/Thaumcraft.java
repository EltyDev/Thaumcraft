package dev.elty.thaumcraft;

import dev.architectury.event.events.common.InteractionEvent;
import dev.elty.thaumcraft.network.NetworkHandler;
import dev.elty.thaumcraft.recipe.RecipeSerializers;
import dev.elty.thaumcraft.recipe.RecipeTypes;
import dev.elty.thaumcraft.recipe.transmutation.TransmutationRecipe;

public final class Thaumcraft {

    public static final String MOD_ID = "thaumcraft";

    public static void init() {
        RecipeTypes.register();
        RecipeSerializers.register();
        NetworkHandler.register();
        InteractionEvent.RIGHT_CLICK_BLOCK.register(TransmutationRecipe::onRightClick);
    }
}
