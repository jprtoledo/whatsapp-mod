package br.com.jprtoledo.whatsappmod.screen;

import br.com.jprtoledo.whatsappmod.networking.ModMessages;
import br.com.jprtoledo.whatsappmod.networking.messages.PlayerSentWppC2SPacket;
import net.minecraft.client.GameNarrator;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;

public class CellphoneScreen extends Screen {
    private Button sendMessage;
    private Button exit;
    private final Player owner;
    public CellphoneScreen(Player player) {
        super(GameNarrator.NO_TITLE);
        this.owner = player;
    }

    @Override
    protected void init() {
        super.init();
        this.exit = this.addRenderableWidget(new Button(this.width / 2 + 2, 196, 98, 20, CommonComponents.GUI_DONE, (p_98173_) -> {
            this.minecraft.setScreen((Screen)null);
        }));
        this.sendMessage = this.addRenderableWidget(new Button(this.width / 2 - 100, 196, 98, 20, Component.literal("Send Message"), (p_98177_) -> {
            ModMessages.sendToServer(new PlayerSentWppC2SPacket("Essa é a minha mensagem", owner.getUUID()));
        }));
    }
}
