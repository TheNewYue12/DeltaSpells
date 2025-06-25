package com.thenewyue12.deltaspells.entities.spells.rudebuster;

import com.thenewyue12.deltaspells.DeltaSpells;
import com.thenewyue12.deltaspells.entities.renderers.RendererHelpers.SpellRendererHelper;
import io.redspace.ironsspellbooks.render.ChargeSpellLayer;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.common.EventBusSubscriber;
import software.bernie.geckolib.animatable.GeoAnimatable;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.AnimatableManager;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;
import software.bernie.geckolib.model.DefaultedGeoModel;
import software.bernie.geckolib.model.GeoModel;

public class RudeBusterModel extends GeoModel<RudeBusterProjectile> implements GeoAnimatable
{

    @Override
    public ResourceLocation getModelResource(RudeBusterProjectile object) {
        return SpellRendererHelper.RUDE_BUSTER_MODEL;
    }
    @Override
    public ResourceLocation getTextureResource(RudeBusterProjectile object) {
        return SpellRendererHelper.RUDE_BUSTER_TEXTURE;

    }

    @Override
    public ResourceLocation getAnimationResource(RudeBusterProjectile animatable) {
        return SpellRendererHelper.RUDE_BUSTER_ANIM;

    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {

    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return null;
    }

    @Override
    public double getTick(Object object) {
        return 0;
    }
}
