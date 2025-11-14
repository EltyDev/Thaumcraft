package dev.elty.thaumcraft.recipe;

import dev.elty.thaumcraft.Thaumcraft;
import dev.elty.thaumcraft.recipe.transmutation.TransmutationRecipe;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;

public class RecipeSerializers {

    public static RecipeSerializer<TransmutationRecipe> TRANSMUTATION_RECIPE;

    private static <S extends RecipeSerializer<T>, T extends Recipe<?>> S registerSerializer(String name, S serializer) {
        return Registry.register(BuiltInRegistries.RECIPE_SERIALIZER, Thaumcraft.MOD_ID + ":" + name, serializer);
    }

    public static void register() {
        TRANSMUTATION_RECIPE = registerSerializer("transmutation_recipe", new TransmutationRecipe.Serializer());
    }

}
