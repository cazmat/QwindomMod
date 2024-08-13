package me.cazmat.morebabies.client.model.entity;

import me.cazmat.morebabies.Constants;
import me.cazmat.morebabies.entity.MindlessEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class MindlessModel extends AnimatedGeoModel<MindlessEntity> {
    private static final ResourceLocation modelResource = new ResourceLocation(Constants.MOD_ID, "geo/mindless.geo.json");
    private static final ResourceLocation textureResource = new ResourceLocation(Constants.MOD_ID, "textures/entity/mindless.png");
    private static final ResourceLocation animationResource = new ResourceLocation(Constants.MOD_ID, "animations/entity/mindless.animation.json");
    @Override
    public ResourceLocation getModelResource(MindlessEntity mindlessEntity) {
        return modelResource;
    }

    @Override
    public ResourceLocation getTextureResource(MindlessEntity mindlessEntity) {
        return textureResource;
    }

    @Override
    public ResourceLocation getAnimationResource(MindlessEntity mindlessEntity) {
        return animationResource;
    }
}
