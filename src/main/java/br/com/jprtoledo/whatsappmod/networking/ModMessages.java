package br.com.jprtoledo.whatsappmod.networking;

import br.com.jprtoledo.whatsappmod.WhatsappMod;
import br.com.jprtoledo.whatsappmod.networking.messages.PlayerSentWppC2SPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;

public class ModMessages {
    private static final String PROTOCOL_VERSION = "1.0";
    private static SimpleChannel INSTANCE;
    private static int packetId = 0;
    private static int id() {
        return packetId++;
    }
    public static void register() {
        SimpleChannel net = NetworkRegistry.ChannelBuilder
                .named(new ResourceLocation(WhatsappMod.MODID, "messages"))
                .networkProtocolVersion(() -> PROTOCOL_VERSION)
                .clientAcceptedVersions(PROTOCOL_VERSION::equals)
                .serverAcceptedVersions(PROTOCOL_VERSION::equals)
                .simpleChannel();

        INSTANCE = net;

        net.messageBuilder(PlayerSentWppC2SPacket.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .encoder(PlayerSentWppC2SPacket::toBytes)
                .decoder(PlayerSentWppC2SPacket::new)
                .consumerMainThread(PlayerSentWppC2SPacket::handle)
                .add();
    }
    public static <MSG> void sendToServer(MSG message) {
        INSTANCE.sendToServer(message);
    }
    public static <MSG> void sendToClient(MSG message, ServerPlayer player) {
        INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), message);
    }
}
