package dev.elty.thaumcraft.neoforge;

import dev.elty.thaumcraft.Thaumcraft;
import net.neoforged.fml.common.Mod;

@Mod(Thaumcraft.MOD_ID)
public final class ThaumcraftNeoForge {
    public ThaumcraftNeoForge() {
        // Run our common setup.
        Thaumcraft.init();
    }
}
