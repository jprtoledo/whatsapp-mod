package br.com.jprtoledo.whatsappmod.networking.messages;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

import java.util.UUID;
import java.util.function.Supplier;

public class PlayerSentWppC2SPacket {
    private final String text;
    private final UUID receiverId;
    public PlayerSentWppC2SPacket(String text, UUID receiverId) {
        this.text = text;
        this.receiverId = receiverId;
    }
    public PlayerSentWppC2SPacket(FriendlyByteBuf buf) {
        this.text = buf.readUtf();
        this.receiverId = buf.readUUID();
    }
    public void toBytes(FriendlyByteBuf buf) {
        buf.writeUtf(text);
        buf.writeUUID(receiverId);
    }
    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            ServerPlayer sender = context.getSender();
            sender.sendSystemMessage(Component.literal(text));
        });
        return true;
    }
}
