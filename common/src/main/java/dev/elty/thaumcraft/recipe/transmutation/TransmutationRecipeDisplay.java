package dev.elty.thaumcraft.recipe.transmutation;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import dev.elty.thaumcraft.recipe.RecipeDisplays;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.crafting.display.RecipeDisplay;
import net.minecraft.world.item.crafting.display.SlotDisplay;

public record TransmutationRecipeDisplay(SlotDisplay usedItem, SlotDisplay usedBlock, SlotDisplay result) implements RecipeDisplay {


    public static final MapCodec<TransmutationRecipeDisplay> MAP_CODEC = RecordCodecBuilder.mapCodec(
            instance -> instance.group(
                            SlotDisplay.CODEC.fieldOf("usedItem").forGetter(TransmutationRecipeDisplay::usedItem),
                            SlotDisplay.CODEC.fieldOf("usedBlock").forGetter(TransmutationRecipeDisplay::usedBlock),
                            SlotDisplay.CODEC.fieldOf("result").forGetter(TransmutationRecipeDisplay::result)
                    )
                    .apply(instance, TransmutationRecipeDisplay::new)
    );

    public static final StreamCodec<RegistryFriendlyByteBuf, TransmutationRecipeDisplay> STREAM_CODEC = StreamCodec.composite(
            SlotDisplay.STREAM_CODEC,
            TransmutationRecipeDisplay::usedItem,
            SlotDisplay.STREAM_CODEC,
            TransmutationRecipeDisplay::usedBlock,
            SlotDisplay.STREAM_CODEC,
            TransmutationRecipeDisplay::result,
            TransmutationRecipeDisplay::new
    );

    @Override
    public SlotDisplay result() {
        return result;
    }

    @Override
    public SlotDisplay craftingStation() {
        return SlotDisplay.Empty.INSTANCE;
    }

    @Override
    public Type<? extends RecipeDisplay> type() {
        return RecipeDisplays.TRANSMUTATION_RECIPE_DISPLAY.get();
    }
}
