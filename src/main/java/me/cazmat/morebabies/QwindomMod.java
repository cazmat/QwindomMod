package me.cazmat.morebabies;

import me.cazmat.morebabies.client.renderer.entity.MindlessEntityRenderer;
import me.cazmat.morebabies.registry.EntityRegistry;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLPaths;

@Mod(Constants.MOD_ID)
@Mod.EventBusSubscriber(modid = Constants.MOD_ID)
public class QwindomMod {
    public static CommonProxy PROXY = DistExecutor.runForDist(() -> ClientProxy::new, () -> CommonProxy::new);
    public QwindomMod() {
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, ConfigManager.SPEC);
        ConfigManager.loadConfig(ConfigManager.SPEC, FMLPaths.CONFIGDIR.get().resolve(Constants.MOD_ID + "-common.toml"));
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        modEventBus.addListener(this::setupClient);

        EntityRegistry.DEF_REG.register(modEventBus);

        PROXY.init();
    }
    private void setupClient(FMLClientSetupEvent event) {
        PROXY.clientInit();
    }
}
