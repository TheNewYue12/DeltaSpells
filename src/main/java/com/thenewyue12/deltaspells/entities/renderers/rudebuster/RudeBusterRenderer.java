package com.thenewyue12.deltaspells.entities.renderers.rudebuster;

import com.thenewyue12.deltaspells.DeltaSpells;
import com.thenewyue12.deltaspells.entities.spells.rudebuster.RudeBusterProjectile;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import io.redspace.ironsspellbooks.IronsSpellbooks;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;
import software.bernie.geckolib.renderer.layer.AutoGlowingGeoLayer;

public class RudeBusterRenderer  extends EntityRenderer<Projectile> {

    public static final ModelLayerLocation MODEL_LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(IronsSpellbooks.MODID, "firebolt_model"), "main");
    private final ResourceLocation TEXTURE = IronsSpellbooks.id("textures/entity/fireball/firebolt.png");


    private final ModelPart body;

    public RudeBusterRenderer(EntityRendererProvider.Context context) {
        super(context);
        ModelPart modelpart = context.bakeLayer(MODEL_LAYER_LOCATION);
        this.body = modelpart.getChild("body");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();
        partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -1.0F, -4.0F, 2.0F, 2.0F, 8.0F), PartPose.ZERO);
        return LayerDefinition.create(meshdefinition, 20, 10);
    }

    @Override
    public void render(Projectile entity, float yaw, float partialTicks, PoseStack poseStack, MultiBufferSource bufferSource, int light) {
        poseStack.pushPose();
        poseStack.translate(0, entity.getBoundingBox().getYsize() * .5f, 0);

        Vec3 motion = entity.getDeltaMovement();
        float xRot = -((float) (Mth.atan2(motion.horizontalDistance(), motion.y) * (double) (180F / (float) Math.PI)) - 90.0F);
        float yRot = -((float) (Mth.atan2(motion.z, motion.x) * (double) (180F / (float) Math.PI)) + 90.0F);
        poseStack.mulPose(Axis.YP.rotationDegrees(yRot));
        poseStack.mulPose(Axis.XP.rotationDegrees(xRot));

        VertexConsumer consumer = bufferSource.getBuffer(RenderType.entityCutoutNoCull(getTextureLocation(entity)));
        this.body.render(poseStack, consumer, LightTexture.FULL_BRIGHT, OverlayTexture.NO_OVERLAY);

        poseStack.popPose();
        super.render(entity, yaw, partialTicks, poseStack, bufferSource, light);
    }

    @Override
    public ResourceLocation getTextureLocation(Projectile entity) {
        return TEXTURE;
    }
}
