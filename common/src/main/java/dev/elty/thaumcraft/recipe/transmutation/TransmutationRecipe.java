package dev.elty.thaumcraft.recipe.transmutation;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import dev.architectury.networking.NetworkManager;
import dev.elty.thaumcraft.network.NetworkHandler;
import dev.elty.thaumcraft.network.packets.DrawSpark;
import dev.elty.thaumcraft.recipe.RecipeSerializers;
import dev.elty.thaumcraft.recipe.RecipeTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.item.crafting.display.RecipeDisplay;
import net.minecraft.world.item.crafting.display.SlotDisplay;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

import java.util.List;
import java.util.Optional;

public class TransmutationRecipe implements Recipe<TransmutationRecipeInput> {

    private final boolean drop;
    private final Ingredient usedItem;
    private final Ingredient usedBlock;
    private final ItemStack result;

    public TransmutationRecipe(boolean drop, Ingredient usedItem, Ingredient usedBlock, ItemStack result) {
        this.drop = drop;
        this.usedItem = usedItem;
        this.usedBlock = usedBlock;
        this.result = result;
    }

    public static InteractionResult onRightClick(Player player, InteractionHand hand, BlockPos pos, Direction direction) {
        Level level = player.level();
        BlockState state = level.getBlockState(pos);
        ItemStack usedItem = player.getItemInHand(hand);
        if (state.isAir() || usedItem.isEmpty() || level.isClientSide()) return InteractionResult.PASS;
        ItemStack usedBlock = state.getBlock().asItem().getDefaultInstance();
        TransmutationRecipeInput input = new TransmutationRecipeInput(usedItem, usedBlock);
        Optional<RecipeHolder<TransmutationRecipe>> match = level.getServer().getRecipeManager().getRecipeFor(RecipeTypes.TRANSMUTATION, input, level);
        if (!match.isPresent()) return InteractionResult.PASS;
        TransmutationRecipe recipe = match.get().value();
        ItemStack result = recipe.result;
        if (!player.isCreative())
            player.setItemInHand(hand, ItemStack.EMPTY);
        if (result.getItem() instanceof BlockItem blockItem && !recipe.drop)
            level.setBlock(pos, blockItem.getBlock().defaultBlockState(), 3);
        else {
            level.setBlock(pos, Blocks.AIR.defaultBlockState(), 3);
            Vec3 center = pos.getCenter();
            ItemEntity entity = new ItemEntity(level, center.x, center.y - 0.5, center.z, result);
            entity.setDeltaMovement(0, 0, 0);
            level.addFreshEntity(entity);
        }
        DrawSpark spark = new DrawSpark();
        NetworkManager.sendToPlayer((ServerPlayer) player, NetworkHandler.DRAW_SPARK.getId(), spark.encode());
        return InteractionResult.SUCCESS;
    }

    @Override
    public boolean matches(TransmutationRecipeInput recipeInput, Level level) {
        return usedItem.test(recipeInput.usedItem()) && usedBlock.test(recipeInput.usedBlock());
    }

    public ItemStack assemble(TransmutationRecipeInput recipeInput, HolderLookup.Provider provider) {
        return result.copy();
    }

    @Override
    public RecipeSerializer<? extends Recipe<TransmutationRecipeInput>> getSerializer() {
        return RecipeSerializers.TRANSMUTATION_RECIPE;
    }

    @Override
    public RecipeType<? extends Recipe<TransmutationRecipeInput>> getType() {
        return RecipeTypes.TRANSMUTATION;
    }

    @Override
    public PlacementInfo placementInfo() {
        return PlacementInfo.NOT_PLACEABLE;
    }

    @Override
    public boolean isSpecial() {
        return true;
    }

    @Override
    public List<RecipeDisplay> display() {
        return List.of(
                new TransmutationRecipeDisplay(
                        this.usedItem.display(),
                        this.usedBlock.display(),
                        new SlotDisplay.ItemSlotDisplay(this.result.getItem())
                )
        );
    }

    @Override
    public RecipeBookCategory recipeBookCategory() {
        return null;
    }

    public static class Serializer implements RecipeSerializer<TransmutationRecipe> {

        private static final MapCodec<TransmutationRecipe> CODEC = RecordCodecBuilder.mapCodec((instance) -> instance.group(
                Codec.BOOL.fieldOf("drop").forGetter(transmutationRecipe -> transmutationRecipe.drop),
                Ingredient.CODEC.fieldOf("input").forGetter(transmutationRecipe -> transmutationRecipe.usedItem),
                Ingredient.CODEC.fieldOf("target").forGetter(transmutationRecipe -> transmutationRecipe.usedBlock),
                ItemStack.CODEC.fieldOf("result").forGetter(transmutationRecipe -> transmutationRecipe.result)
        ).apply(instance, TransmutationRecipe::new));

        public static final StreamCodec<RegistryFriendlyByteBuf, TransmutationRecipe> STREAM_CODEC;

        @Override
        public MapCodec<TransmutationRecipe> codec() {
            return CODEC;
        }

        @Override
        public StreamCodec<RegistryFriendlyByteBuf, TransmutationRecipe> streamCodec() {
            return STREAM_CODEC;
        }

        static {
            STREAM_CODEC = StreamCodec.composite(
                    ByteBufCodecs.BOOL, transmutationRecipe -> transmutationRecipe.drop,
                    Ingredient.CONTENTS_STREAM_CODEC, transmutationRecipe -> transmutationRecipe.usedItem,
                    Ingredient.CONTENTS_STREAM_CODEC, transmutationRecipe -> transmutationRecipe.usedBlock,
                    ItemStack.STREAM_CODEC, transmutationRecipe -> transmutationRecipe.result,
                    TransmutationRecipe::new
            );
        }
    }
}
