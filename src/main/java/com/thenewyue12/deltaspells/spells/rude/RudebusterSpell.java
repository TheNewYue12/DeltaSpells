package com.thenewyue12.deltaspells.spells.rude;


import com.thenewyue12.deltaspells.DeltaSpells;
import com.thenewyue12.deltaspells.registries.DSSchoolRegistries;
import com.thenewyue12.deltaspells.util.DSUtils;
import io.redspace.ironsspellbooks.api.config.DefaultConfig;
import io.redspace.ironsspellbooks.api.magic.MagicData;
import io.redspace.ironsspellbooks.api.registry.SchoolRegistry;
import io.redspace.ironsspellbooks.api.spells.*;
import io.redspace.ironsspellbooks.api.util.AnimationHolder;
import io.redspace.ironsspellbooks.api.util.Utils;
import com.thenewyue12.deltaspells.entities.spells.rudebuster.RudeBusterProjectile;
import com.thenewyue12.deltaspells.registries.DSSoundRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.registries.DeferredHolder;


import javax.annotation.Nullable;
import java.util.List;
import java.util.Optional;

@AutoSpellConfig
public class RudebusterSpell extends AbstractRudeSpell {
    private final ResourceLocation spellId = new ResourceLocation(DeltaSpells.MODID, "rude_buster");

    @Override
    public List<MutableComponent> getUniqueInfo(int spellLevel, LivingEntity caster) {
        return List.of(
                Component.translatable("ui.irons_spellbooks.damage", Utils.stringTruncation(getDamage(spellLevel, caster), 2))
                  );
    }


    private final DefaultConfig defaultConfig = new DefaultConfig()
            .setMinRarity(SpellRarity.RARE)
            .setSchoolResource(DSSchoolRegistries.RUDE_RESOURCE)
            .setMaxLevel(5)
            .setCooldownSeconds(30)
            .build();

    @Override
    public DefaultConfig getDefaultConfig() {
        return defaultConfig;
    }

    public RudebusterSpell() {
        this.manaCostPerLevel = 15;
        this.baseSpellPower = 1;
        this.spellPowerPerLevel = 2;
        this.castTime = 20;
        this.baseManaCost = 60;
    }

    @Override
    public CastType getCastType() {
        return CastType.LONG;
    }


    @Override
    public ResourceLocation getSpellResource() {
        return spellId;
    }

    @Override
    public void onCast(Level world, int spellLevel, LivingEntity entity, CastSource castSource, MagicData playerMagicData) {
        playSound(Optional.of(DSSoundRegistry.RUDE_BUSTER_CAST.get()),entity);
        var plrPos = entity.position();
        RudeBusterProjectile RudeBuster = new RudeBusterProjectile(world, entity);
        RudeBuster.setPos(entity.position().add(0, entity.getEyeHeight() - RudeBuster.getBoundingBox().getYsize() * .5f, 0));
        RudeBuster.shoot(entity.getLookAngle());
        RudeBuster.setDamage(getDamage(spellLevel, entity));
        world.addFreshEntity(RudeBuster);
        super.onCast(world, spellLevel, entity, castSource, playerMagicData);
    }

    private float getDamage(int spellLevel, LivingEntity caster)
    {

        return 5 + 5 * getSpellPower(spellLevel, caster);
    }



    @Override
    public AnimationHolder getCastStartAnimation() {
        return SpellAnimations.OVERHEAD_MELEE_SWING_ANIMATION;
    }

    @Override
    public AnimationHolder getCastFinishAnimation() {
        return AnimationHolder.pass();
    }

}