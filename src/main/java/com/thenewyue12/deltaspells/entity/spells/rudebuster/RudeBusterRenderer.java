package com.thenewyue12.deltaspellsmod.entity.spells.rudebuster;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import net.minecraft.client.renderer.MultiBufferSource;
import software.bernie.geckolib3.renderers.geo.GeoProjectilesRenderer;
import net.minecraft.util.Mth;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib3.core.util.Color;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.geo.render.built.GeoModel;
import software.bernie.geckolib3.model.provider.data.EntityModelData;
import software.bernie.geckolib3.util.EModelRenderCycle;

import java.util.Collections;


public class RudeBusterRenderer extends GeoProjectilesRenderer<RudeBusterProjectile>  {


    public RudeBusterRenderer(EntityRendererProvider.Context context) {
        super(context, new RudeBusterModel());
        this.shadowRadius = 0.5f;
    }
    @Override
    public void render(RudeBusterProjectile projectile, float yaw, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {
        poseStack.pushPose();
        poseStack.mulPose(Vector3f.YP.rotationDegrees(Mth.lerp(partialTick, projectile.yRotO, projectile.getYRot()) + 180));
        poseStack.mulPose(Vector3f.XP.rotationDegrees(Mth.lerp(partialTick, projectile.xRotO, projectile.getXRot())));
        GeoModel model = this.modelProvider.getModel(modelProvider.getModelResource(animatable));
        this.dispatchedMat = poseStack.last().pose().copy();
        setCurrentModelRenderCycle(EModelRenderCycle.INITIAL);
        AnimationEvent<RudeBusterProjectile> predicate = new AnimationEvent<>(projectile, 0, 0, partialTick, false, Collections.singletonList(new EntityModelData()));
        modelProvider.setCustomAnimations(projectile, getInstanceId(projectile), predicate);
        RenderSystem.setShaderTexture(0, getTextureLocation(projectile));
        Color renderColor = getRenderColor(projectile, partialTick, poseStack, bufferSource, null, packedLight);
        RenderType renderType = getRenderType(projectile, partialTick, poseStack, bufferSource, null, packedLight, getTextureLocation(projectile));

        poseStack.popPose();
    }


}
