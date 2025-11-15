package dev.elty.thaumcraft.item;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import dev.elty.thaumcraft.Thaumcraft;
import dev.elty.thaumcraft.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.ArrowItem;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import java.util.Iterator;

public class ModItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(Thaumcraft.MOD_ID, Registries.ITEM);

    public static void register() {
        for (Iterator<RegistrySupplier<Block>> it = ModBlocks.BLOCKS.iterator(); it.hasNext(); ) {
            RegistrySupplier<Block> block = it.next();
            System.out.println(block.get().getName());
            ITEMS.register(block.getRegistryId(), () -> new BlockItem(block.get(), new Item.Properties().setId(
                    ResourceKey.create(Registries.ITEM, block.getId())
            )));
        }
        ITEMS.register();
    }

}
