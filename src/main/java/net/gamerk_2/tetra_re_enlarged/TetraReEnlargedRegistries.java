package net.gamerk_2.tetra_re_enlarged;

import net.gamerk_2.tetra_re_enlarged.item.ModularLargeBladedItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import se.mickelus.tetra.TetraMod;

public class TetraReEnlargedRegistries {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, TetraMod.MOD_ID);

    public static void init(IEventBus bus) {
        ITEMS.register(bus);

        ITEMS.register(ModularLargeBladedItem.identifier, ModularLargeBladedItem::new);

    }
}
