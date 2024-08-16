package me.cazmat.morebabies.client.renderer.entity.firefly;

import me.cazmat.morebabies.Constants;
import me.cazmat.morebabies.client.model.entity.firefly.MagmaFireflyModel;
import me.cazmat.morebabies.entity.firefly.FireflyEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class MagmaFireflyEntityRenderer extends FireflyEntityRenderer {
    public MagmaFireflyEntityRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new MagmaFireflyModel());
    }
    @Override
    public ResourceLocation getTextureLocation(FireflyEntity animatable) {
        return new ResourceLocation(Constants.MOD_ID, "textures/entity/firefly/firefly_magma.png");
    }
}
