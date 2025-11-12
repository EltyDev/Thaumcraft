package dev.elty.thaumcraft.network.packets;

import dev.architectury.networking.NetworkManager;
import dev.elty.thaumcraft.network.NetworkPacket;
import io.netty.buffer.Unpooled;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;

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

    public StreamCodec<? super RegistryFriendlyByteBuf, NetworkPacket> codec() {

    }

    @Override
    public void handle(NetworkManager.PacketContext ctx) {
        System.out.println("SAlut");
    }
}
