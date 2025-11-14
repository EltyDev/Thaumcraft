package dev.elty.thaumcraft.network;

import dev.architectury.networking.NetworkManager;
import dev.elty.thaumcraft.network.packets.DrawSpark;

import java.nio.channels.NetworkChannel;

public class NetworkHandler {

    public static final NetworkPacket.RegisterPacket<DrawSpark> DRAW_SPARK = new NetworkPacket.RegisterPacket<>(DrawSpark.class, "draw_spark", NetworkManager.Side.S2C);

    public static void register() {
        DRAW_SPARK.register();
    }

}
