package br.com.jprtoledo.whatsappmod.init;

import br.com.jprtoledo.whatsappmod.items.Celphone;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

import static br.com.jprtoledo.whatsappmod.WhatsappMod.ITEM_REGISTER;
import static br.com.jprtoledo.whatsappmod.api.ModItems.*;

public class ItemsInit {
    public static void setup() {
        register();
    }

    private static void register() {
        CELLPHONE = registerItem("cellphone", () -> new Celphone(props().tab(CreativeModeTab.TAB_MISC)));
    }

    private static Item.Properties props() {
        return new Item.Properties();
    }

    private static <T extends Item> RegistryObject<T> registerItem(String name, Supplier<T> item) {
        return ITEM_REGISTER.register(name, item);
    }


}
