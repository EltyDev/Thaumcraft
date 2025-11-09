package dev.elty.thaumcraft;

import dev.architectury.event.events.common.InteractionEvent;
import dev.architectury.event.events.common.PlayerEvent;
import dev.elty.thaumcraft.recipe.RecipeSerializers;
import dev.elty.thaumcraft.recipe.RecipeTypes;
import dev.elty.thaumcraft.recipe.transmutation.TransmutationRecipe;
import net.minecraft.world.item.crafting.BlastingRecipe;

public final class Thaumcraft {
    public static final String MOD_ID = "thaumcraft";

    public static void init() {
        RecipeTypes.register();
        RecipeSerializers.register();
        InteractionEvent.RIGHT_CLICK_BLOCK.register(TransmutationRecipe::onRightClick);
    }
}
