package com.thenewyue12.deltaspells.spells.rude;


import io.redspace.ironsspellbooks.api.config.DefaultConfig;
import io.redspace.ironsspellbooks.api.magic.MagicData;
import io.redspace.ironsspellbooks.api.spells.*;
import io.redspace.ironsspellbooks.api.util.AnimationHolder;
import io.redspace.ironsspellbooks.api.util.Utils;
import com.thenewyue12.deltaspellsmod.DeltaSpellsMod;
import com.thenewyue12.deltaspellsmod.entity.spells.rudebuster.RudeBusterProjectile;
import com.thenewyue12.deltaspellsmod.registry.DSSchoolRegistry;
import com.thenewyue12.deltaspellsmod.registry.DSSoundRegistry;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

import net.minecraft.world.entity.MobType;

import java.util.List;
import java.util.Optional;

@AutoSpellConfig
public class RudebusterSpell extends AbstractRudeSpell {
    private final ResourceLocation spellId = new ResourceLocation(DeltaSpellsMod.MODID, "rude_buster");
    @Override
    public List<MutableComponent> getUniqueInfo(int spellLevel, LivingEntity caster) {
        return List.of(
                Component.translatable("ui.irons_spellbooks.damage", Utils.stringTruncation(getDamage(spellLevel, caster), 2))
                  );
    }

    private final DefaultConfig defaultConfig = new DefaultConfig()
            .setMinRarity(SpellRarity.RARE)
            .setSchoolResource(DSSchoolRegistry.RUDE_RESOURCE)
            .setMaxLevel(5)
            .setCooldownSeconds(30)
            .build();

    public RudebusterSpell() {
        this.manaCostPerLevel = 15;
        this.baseSpellPower = 1;
        this.spellPowerPerLevel = 2;
        this.castTime = 20;
        this.baseManaCost = 60;
    }

    @Override
    public CastType getCastType() {
        return CastType.INSTANT;
    }

    @Override
    public DefaultConfig getDefaultConfig() {
        return defaultConfig;
    }

    @Override
    public ResourceLocation getSpellResource() {
        return spellId;
    }

    @Override
    public Optional<SoundEvent> getCastStartSound() {
        return Optional.of(DSSoundRegistry.RUDEBUSTER_CAST.get());
    }


    @Override
    public void onCast(Level world, int spellLevel, LivingEntity entity, CastSource castSource, MagicData playerMagicData) {
        Vec3 origin = entity.getEyePosition();

        RudeBusterProjectile rude_buster_slash = new RudeBusterProjectile(world, entity);

        rude_buster_slash.setDamage(getDamage(spellLevel, entity));


        rude_buster_slash.setPos(origin.add(entity.getForward()).subtract(0, rude_buster_slash.getBbHeight() / 2, 0));
        rude_buster_slash.shoot(entity.getLookAngle());

        world.addFreshEntity(rude_buster_slash);
        super.onCast(world, spellLevel, entity, castSource, playerMagicData);
    }

    public float getDamage(int spellLevel, LivingEntity caster) {

        return 5 + 5 * getSpellPower(spellLevel, caster);
    }

    @Override
    public AnimationHolder getCastStartAnimation() {
        return SpellAnimations.ONE_HANDED_HORIZONTAL_SWING_ANIMATION;
    }

    @Override
    public AnimationHolder getCastFinishAnimation() {
        return AnimationHolder.pass();
    }
}