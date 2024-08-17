package me.cazmat.morebabies.client.renderer.entity.firefly;

import me.cazmat.morebabies.Constants;
import me.cazmat.morebabies.client.model.entity.firefly.FireflyModel;
import me.cazmat.morebabies.client.model.entity.firefly.LushFireflyModel;
import me.cazmat.morebabies.entity.firefly.FireflyEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class LushFireflyEntityRenderer extends FireflyEntityRenderer {
    public LushFireflyEntityRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new LushFireflyModel());
    }

    @Override
    public ResourceLocation getTextureLocation(FireflyEntity animatable) {
        return new ResourceLocation(Constants.MOD_ID, "textures/entity/firefly/firefly_lush.png");
    }
}
