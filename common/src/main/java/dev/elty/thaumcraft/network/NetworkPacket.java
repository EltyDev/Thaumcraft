package dev.elty.thaumcraft.network;

import dev.architectury.networking.NetworkManager;
import dev.elty.thaumcraft.Thaumcraft;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.ResourceLocation;

import java.util.Collections;


//TODO Rework for use codec instead of RegistryFriendlyByteBuf
public abstract class NetworkPacket  {

    public NetworkPacket() {}

    public NetworkPacket(RegistryFriendlyByteBuf buf) {
        decode(buf);
    };

    public abstract void decode(RegistryFriendlyByteBuf buf);
    public abstract RegistryFriendlyByteBuf encode();
    public abstract void handle(NetworkManager.PacketContext ctx);

    public static class RegisterPacket<T extends NetworkPacket> {

        private final Class<T> packetClass;
        private final ResourceLocation id;
        private final NetworkManager.Side side;

        public RegisterPacket(Class<T> packetClass, String id, NetworkManager.Side side) {
            this.packetClass = packetClass;
            this.id = ResourceLocation.fromNamespaceAndPath(Thaumcraft.MOD_ID, id);
            this.side = side;
        }

        public T getPacket(RegistryFriendlyByteBuf buf) {
            try {
                return packetClass.getDeclaredConstructor(RegistryFriendlyByteBuf.class).newInstance(buf);
            } catch (Exception error) {
                return null;
            }
        }

        public void register() {
            NetworkManager.registerReceiver(this.side, this.id, Collections.emptyList(), (buf, context) -> {
                T packet = getPacket(buf);
                if (packet == null) return;
                packet.handle(context);
            });
        }

        public ResourceLocation getId() { return id; }
        public NetworkManager.Side getSide() { return side; }

    }

}
