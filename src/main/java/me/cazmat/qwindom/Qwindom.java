package me.cazmat.qwindom;

import me.cazmat.qwindom.registry.BlockRegistry;
import me.cazmat.qwindom.registry.EntityRegistry;
import me.cazmat.qwindom.registry.ItemRegistry;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(Qwindom.MOD_ID)
public class Qwindom {
    public static final String MOD_ID = "qwindom";
    public Qwindom() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        EntityRegistry.register(modEventBus);
        BlockRegistry.register(modEventBus);
        ItemRegistry.register(modEventBus);

        MinecraftForge.EVENT_BUS.register(this);
    }
}
