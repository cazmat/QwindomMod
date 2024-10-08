package me.cazmat.morebabies.proxy;

import me.cazmat.morebabies.Constants;
import me.cazmat.morebabies.client.renderer.entity.SprogEntityRenderer;
import me.cazmat.morebabies.client.renderer.entity.firefly.FireflyEntityRenderer;
import me.cazmat.morebabies.client.renderer.entity.MindlessEntityRenderer;
import me.cazmat.morebabies.client.renderer.entity.firefly.IceFireflyEntityRenderer;
import me.cazmat.morebabies.client.renderer.entity.firefly.LushFireflyEntityRenderer;
import me.cazmat.morebabies.client.renderer.entity.firefly.MagmaFireflyEntityRenderer;
import me.cazmat.morebabies.registry.EntityRegistry;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.common.Mod;

@OnlyIn(Dist.CLIENT)
@Mod.EventBusSubscriber(modid = Constants.MOD_ID, value = Dist.CLIENT)
public class ClientProxy extends CommonProxy {
    public void clientInit() {
        EntityRenderers.register(EntityRegistry.FIREFLY.get(), FireflyEntityRenderer::new);
        EntityRenderers.register(EntityRegistry.ICE_FIREFLY.get(), IceFireflyEntityRenderer::new);
        EntityRenderers.register(EntityRegistry.LUSH_FIREFLY.get(), LushFireflyEntityRenderer::new);
        EntityRenderers.register(EntityRegistry.MAGMA_FIREFLY.get(), MagmaFireflyEntityRenderer::new);
        EntityRenderers.register(EntityRegistry.MINDLESS.get(), MindlessEntityRenderer::new);
        EntityRenderers.register(EntityRegistry.SPROG.get(), (EntityRendererProvider.Context context) -> new SprogEntityRenderer(context, true));
    }
}
