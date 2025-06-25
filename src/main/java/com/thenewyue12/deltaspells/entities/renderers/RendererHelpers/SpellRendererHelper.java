package com.thenewyue12.deltaspells.entities.renderers.RendererHelpers;


import com.thenewyue12.deltaspells.DeltaSpells;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SpellRendererHelper {

    public static final ResourceLocation RUDE_BUSTER_TEXTURE = DeltaSpells.id("textures/entity/spells/rude_buster/texture.png");

    public static final ResourceLocation RUDE_BUSTER_ANIM = DeltaSpells.id("animation/rude_buster_slash.animation.json");
    public static final ResourceLocation RUDE_BUSTER_MODEL = DeltaSpells.id("geo/rude_buster_slash.geo.json");
}
