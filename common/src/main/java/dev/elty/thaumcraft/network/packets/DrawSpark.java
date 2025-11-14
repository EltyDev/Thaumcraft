package dev.elty.thaumcraft.network.packets;

import dev.architectury.networking.NetworkManager;
import dev.elty.thaumcraft.network.NetworkPacket;
import io.netty.buffer.Unpooled;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.RegistryFriendlyByteBuf;

public class DrawSpark extends NetworkPacket {

    public DrawSpark() {};

    public DrawSpark(RegistryFriendlyByteBuf buf) {
        super(buf);
    }

    @Override
    public void decode(RegistryFriendlyByteBuf buf) {

    }

    @Override
    public RegistryFriendlyByteBuf encode() {
        RegistryFriendlyByteBuf buf = new RegistryFriendlyByteBuf(Unpooled.buffer(), RegistryAccess.EMPTY);
        return buf;
    }

    @Override
    public void handle(NetworkManager.PacketContext ctx) {
        //TODO Create visual for transmutation crafting
    }
}
