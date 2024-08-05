package me.cazmat.morebabies.registry;

import me.cazmat.morebabies.QwindomMod;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ItemRegistry {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, QwindomMod.MOD_ID);
}
