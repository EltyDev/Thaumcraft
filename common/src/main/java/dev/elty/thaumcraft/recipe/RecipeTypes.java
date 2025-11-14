package dev.elty.thaumcraft.recipe;

import dev.elty.thaumcraft.Thaumcraft;
import dev.elty.thaumcraft.recipe.transmutation.TransmutationRecipe;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeType;

public class RecipeTypes {

    public static RecipeType<TransmutationRecipe> TRANSMUTATION;

    private static <T extends Recipe<?>> RecipeType<T> regiserType(String name) {
        return Registry.register(
                BuiltInRegistries.RECIPE_TYPE,
                ResourceLocation.fromNamespaceAndPath(Thaumcraft.MOD_ID, name),
                new RecipeType<T>() {
                    public String toString() {
                        return name;
                    }
                }
        );
    }

    public static void register() {
        TRANSMUTATION = regiserType("transmutation");
    }

}