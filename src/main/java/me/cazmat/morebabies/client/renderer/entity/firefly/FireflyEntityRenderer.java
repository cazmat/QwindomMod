package me.cazmat.morebabies.client.renderer.entity.firefly;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import me.cazmat.morebabies.Constants;
import me.cazmat.morebabies.client.model.entity.firefly.FireflyModel;
import me.cazmat.morebabies.entity.firefly.FireflyEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib3.geo.render.built.GeoModel;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class FireflyEntityRenderer extends GeoEntityRenderer<FireflyEntity> {
    public FireflyEntityRenderer(EntityRendererProvider.Context renderManager, FireflyModel model) {
        super(renderManager, model);
    }
    public FireflyEntityRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new FireflyModel());
    }

    @Override
    public ResourceLocation getTextureLocation(FireflyEntity animatable) {
        return new ResourceLocation(Constants.MOD_ID, "textures/entity/firefly/firefly_base.png");
    }

    @Override
    public RenderType getRenderType(FireflyEntity animatable, float partialTick, PoseStack poseStack, @Nullable MultiBufferSource bufferSource, @Nullable VertexConsumer buffer, int packedLight, ResourceLocation texture) {
        return RenderType.entityCutout(getTextureLocation(animatable));
    }

    @Override
    public void render(GeoModel model, FireflyEntity animatable, float partialTick, RenderType type, PoseStack poseStack, @Nullable MultiBufferSource bufferSource, @Nullable VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        super.render(model, animatable, partialTick, type, poseStack, bufferSource, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    }
}
