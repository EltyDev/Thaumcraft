package dev.elty.thaumcraft.neoforge;

import dev.elty.thaumcraft.Thaumcraft;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;

@Mod(Thaumcraft.MOD_ID)
public final class ThaumcraftNeoForge {

    public static PayloadRegistrar NETWORK;

    public ThaumcraftNeoForge(IEventBus bus) {
        Thaumcraft.init();
    }
}

