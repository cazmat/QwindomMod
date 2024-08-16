package me.cazmat.morebabies.client.model.entity.firefly;

import me.cazmat.morebabies.Constants;
import me.cazmat.morebabies.entity.firefly.FireflyEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class FireflyModel extends AnimatedGeoModel<FireflyEntity> {
    private static final ResourceLocation modelResource = new ResourceLocation(Constants.MOD_ID, "geo/firefly.geo.json");
    private static final ResourceLocation textureResource = new ResourceLocation(Constants.MOD_ID, "textures/entity/firefly/firefly_base.png");
    private static final ResourceLocation animationResource = new ResourceLocation(Constants.MOD_ID, "animations/firefly.animation.json");
    @Override
    public ResourceLocation getModelResource(FireflyEntity fireflyEntity) {
        return modelResource;
    }

    @Override
    public ResourceLocation getTextureResource(FireflyEntity fireflyEntity) {
        return textureResource;
    }

    @Override
    public ResourceLocation getAnimationResource(FireflyEntity fireflyEntity) {
        return animationResource;
    }
}
