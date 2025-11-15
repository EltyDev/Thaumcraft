package dev.elty.thaumcraft.block;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import dev.elty.thaumcraft.Thaumcraft;
import dev.elty.thaumcraft.block.crystal.Crystal;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;

import java.lang.reflect.InvocationTargetException;


public class ModBlocks {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(Thaumcraft.MOD_ID, Registries.BLOCK);

    public static RegistrySupplier<Block> CRYSTAL;

    private static <T extends Block> RegistrySupplier<Block> registerInternal(String name, Class<T> classe) {
        return BLOCKS.register(name, () -> {
            try {
                return classe.getDeclaredConstructor(BlockBehaviour.Properties.class).newInstance(
                        BlockBehaviour.Properties.of().setId(ResourceKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(Thaumcraft.MOD_ID, name)))
                );
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException error) {
                throw new RuntimeException(error);
            }
        });
    }

    public static void register() {
        CRYSTAL = registerInternal("crystal", Crystal.class);
        BLOCKS.register();
    }

}
