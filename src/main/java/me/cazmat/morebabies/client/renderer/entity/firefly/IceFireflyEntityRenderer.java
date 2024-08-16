package me.cazmat.morebabies.client.renderer.entity.firefly;

import me.cazmat.morebabies.Constants;
import me.cazmat.morebabies.client.model.entity.firefly.IceFireflyModel;
import me.cazmat.morebabies.entity.firefly.FireflyEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class IceFireflyEntityRenderer extends FireflyEntityRenderer {
    public IceFireflyEntityRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new IceFireflyModel());
    }

    @Override
    public ResourceLocation getTextureLocation(FireflyEntity animatable) {
        return new ResourceLocation(Constants.MOD_ID, "textures/entity/firefly/firefly_ice.png");
    }
}
