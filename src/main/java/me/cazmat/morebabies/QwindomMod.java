package me.cazmat.morebabies;

import me.cazmat.morebabies.config.ConfigManager;
import me.cazmat.morebabies.proxy.ClientProxy;
import me.cazmat.morebabies.proxy.CommonProxy;
import me.cazmat.morebabies.registry.EntityRegistry;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.npc.Villager;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
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

        modEventBus.addListener(this::setup);
        modEventBus.addListener(this::setupClient);

        EntityRegistry.DEF_REG.register(modEventBus);

        PROXY.init();
    }
    private void setup(FMLLoadCompleteEvent event) {
        MinecraftForge.EVENT_BUS.addListener(this::checkEntitySpawn);
        MinecraftForge.EVENT_BUS.addListener(this::checkSpecialEntitySpawn);
    }
    private void setupClient(FMLClientSetupEvent event) {
        PROXY.clientInit();
    }
    private void checkEntitySpawn(LivingSpawnEvent.CheckSpawn event) {
        if(event.getEntity() instanceof Villager) {
            if(!ConfigManager.allowVillagerSpawns()) {
                event.getEntity().remove(Entity.RemovalReason.DISCARDED);
                event.setResult(Event.Result.DENY);
            }
        }
    }
    private void checkSpecialEntitySpawn(LivingSpawnEvent.SpecialSpawn event) {
        if(event.getEntity() instanceof Villager) {
            if(!ConfigManager.allowVillagerSpawns()) {
                if((event.getSpawnReason() == MobSpawnType.SPAWN_EGG) && (ConfigManager.allowVillagerEggSpawn())) {
                    return;
                }
                if((event.getSpawnReason() == MobSpawnType.COMMAND) && (ConfigManager.allowVillagerCommandSpawn())) {
                    return;
                }
                event.getEntity().remove(Entity.RemovalReason.DISCARDED);
                event.setCanceled(true);
            }
        }
    }
}
