package dev.elty.thaumcraft.recipe.transmutation;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.NotNull;

public class TransmutationRecipeInput implements RecipeInput {

    private final ItemStack usedItem;
    private final ItemStack usedBlock;

    public TransmutationRecipeInput(ItemStack usedItem, ItemStack usedBlock) {
        this.usedItem = usedItem;
        this.usedBlock = usedBlock;
    }

    public @NotNull ItemStack getItem(int i) {
        return switch (i) {
            case 0 -> usedItem;
            case 1 -> usedBlock;
            default -> throw new IllegalArgumentException("No item for index " + i);
        };
    }

    public int size() {
        return 2;
    }

    public ItemStack usedItem() {
        return usedItem;
    }

    public ItemStack usedBlock() {
        return usedBlock;
    }

}
