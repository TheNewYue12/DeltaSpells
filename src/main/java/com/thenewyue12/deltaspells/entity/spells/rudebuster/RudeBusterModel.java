package com.thenewyue12.deltaspellsmod.entity.spells.rudebuster;

import  com.thenewyue12.deltaspellsmod.DeltaSpellsMod;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;
public class RudeBusterModel extends AnimatedGeoModel<RudeBusterProjectile>
{

    @Override
    public ResourceLocation getModelResource(RudeBusterProjectile object) {
        return new ResourceLocation(DeltaSpellsMod.MODID, "geo/rude_buster_slash.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(RudeBusterProjectile object) {
        return new ResourceLocation(DeltaSpellsMod.MODID, "texture/entity/rude_buster_projectile/texture.png");

    }

    @Override
    public ResourceLocation getAnimationResource(RudeBusterProjectile animatable) {
        return new ResourceLocation(DeltaSpellsMod.MODID, "animations/rude_buster_slash.animation.json");
    }
}
