package dev.elty.thaumcraft.neoforge.client;

import dev.elty.thaumcraft.Thaumcraft;
import dev.elty.thaumcraft.ThaumcraftClient;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;

@Mod(value = Thaumcraft.MOD_ID, dist = Dist.CLIENT)
public class ThaumcraftNeoForgeClient {

    public ThaumcraftNeoForgeClient(IEventBus modBus) {
        ThaumcraftClient.init();
    }
}
