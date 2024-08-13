package me.cazmat.morebabies;

import me.cazmat.morebabies.client.renderer.entity.MindlessEntityRenderer;
import me.cazmat.morebabies.registry.EntityRegistry;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@OnlyIn(Dist.CLIENT)
@Mod.EventBusSubscriber(modid = Constants.MOD_ID, value = Dist.CLIENT)
public class ClientProxy extends CommonProxy {
    public void init() {
    }
    public void clientInit() {
        EntityRenderers.register(EntityRegistry.MINDLESS.get(), MindlessEntityRenderer::new);
    }
}
