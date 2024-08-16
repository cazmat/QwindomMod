package me.cazmat.morebabies.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import me.cazmat.morebabies.entity.SprogEntity;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.resources.ResourceLocation;

public class SprogEntityRenderer extends HumanoidMobRenderer<SprogEntity, PlayerModel<SprogEntity>> {
    public SprogEntityRenderer(EntityRendererProvider.Context context, boolean normal) {
        super(context, new PlayerModel<>(context.bakeLayer(normal ? ModelLayers.PLAYER_SLIM : ModelLayers.PLAYER), false), 0.5F);
    }
    @Override
    public void render(SprogEntity entity, float entityYaw, float partialTicks, PoseStack matrixStack, MultiBufferSource buffer, int packedLight) {
        super.render(entity, entityYaw, partialTicks, matrixStack, buffer, packedLight);
    }
    @Override
    public void scale(SprogEntity entity, PoseStack matrixStack, float partialTickTime) {
        matrixStack.scale(0.5F, 0.5F, 0.5F);
    }
    @Override
    public ResourceLocation getTextureLocation(SprogEntity entity) {
        return entity.getSkinLocation();
    }
}
