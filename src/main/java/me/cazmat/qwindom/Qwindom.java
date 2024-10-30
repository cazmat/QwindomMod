package me.cazmat.qwindom;

import com.mojang.logging.LogUtils;
import me.cazmat.qwindom.events.UpdateNotifier;
import me.cazmat.qwindom.registry.BlockRegistry;
import me.cazmat.qwindom.registry.EntityRegistry;
import me.cazmat.qwindom.registry.ItemRegistry;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

import java.util.logging.LogManager;

@Mod(Qwindom.MOD_ID)
public class Qwindom {
    public static final String MOD_ID = "qwindom";
    public static final Logger LOGGER = LogUtils.getLogger();
    public Qwindom() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        EntityRegistry.register(modEventBus);
        BlockRegistry.register(modEventBus);
        ItemRegistry.register(modEventBus);

        MinecraftForge.EVENT_BUS.register(this);

        MinecraftForge.EVENT_BUS.register(new UpdateNotifier());
    }
}
