package br.com.jprtoledo.whatsappmod.items;

import br.com.jprtoledo.whatsappmod.networking.ModMessages;
import br.com.jprtoledo.whatsappmod.networking.messages.PlayerSentWppC2SPacket;
import br.com.jprtoledo.whatsappmod.screen.CellphoneScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class Celphone extends Item {
    public Celphone(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        if (level.isClientSide) {
            Minecraft.getInstance().setScreen(new CellphoneScreen(player));
        }
        return super.use(level, player, hand);
    }
}
