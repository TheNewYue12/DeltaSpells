package com.thenewyue12.deltaspells.entities.renderers.rudebuster;

import com.thenewyue12.deltaspells.DeltaSpells;
import com.thenewyue12.deltaspells.entities.spells.rudebuster.RudeBusterModel;
import com.thenewyue12.deltaspells.entities.spells.rudebuster.RudeBusterProjectile;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;
import software.bernie.geckolib.renderer.layer.AutoGlowingGeoLayer;

public class RudeBusterRenderer extends GeoEntityRenderer<RudeBusterProjectile> {
    public RudeBusterRenderer(EntityRendererProvider.Context context) {
        super(context, new RudeBusterModel(ResourceLocation.fromNamespaceAndPath(DeltaSpells.MODID, "rude_buster")));
        addRenderLayer(new AutoGlowingGeoLayer<>(this));

        this.shadowRadius = 0.5f;
    }
    @Override
    public ResourceLocation getTextureLocation(RudeBusterProjectile rudebuster) {
        return ResourceLocation.fromNamespaceAndPath(DeltaSpells.MODID, "textures/entity/rude_buster_projectile/texture.png");
    }
    @Override
    public void preRender(PoseStack poseStack, RudeBusterProjectile animatable, BakedGeoModel model, @Nullable MultiBufferSource bufferSource, @Nullable VertexConsumer buffer, boolean isReRender, float partialTick, int packedLight, int packedOverlay, int colour) {

        poseStack.mulPose(Axis.YP.rotationDegrees(Mth.lerp(partialTick, animatable.yRotO, animatable.getYRot())));
        poseStack.mulPose(Axis.XP.rotationDegrees(-Mth.lerp(partialTick, animatable.xRotO, animatable.getXRot())));

        super.preRender(poseStack, animatable, model, bufferSource, buffer, isReRender, partialTick, packedLight, packedOverlay, colour);

    }
}
