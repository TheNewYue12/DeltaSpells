package com.thenewyue12.deltaspells.entities.spells.rudebuster;

import com.thenewyue12.deltaspells.registries.DSEntityRegistry;
import com.thenewyue12.deltaspells.registries.DSSoundRegistry;
import com.thenewyue12.deltaspells.registries.DSSpellRegistries;
import io.redspace.ironsspellbooks.api.registry.SpellRegistry;
import io.redspace.ironsspellbooks.capabilities.magic.MagicManager;
import io.redspace.ironsspellbooks.damage.DamageSources;
import io.redspace.ironsspellbooks.registries.EntityRegistry;
import io.redspace.ironsspellbooks.util.ParticleHelper;
import net.minecraft.core.Holder;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import com.thenewyue12.deltaspells.registries.DSParticlesRegistry;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import io.redspace.ironsspellbooks.entity.mobs.AntiMagicSusceptible;
import io.redspace.ironsspellbooks.entity.spells.AbstractMagicProjectile;
public class RudeBusterProjectile extends AbstractMagicProjectile  {
    public RudeBusterProjectile(EntityType<? extends RudeBusterProjectile> entityType, Level level) {
        super(entityType, level);
        this.setNoGravity(true);
    }

    public RudeBusterProjectile(Level levelIn, LivingEntity shooter) {
        this(DSEntityRegistry.RUDE_BUSTER.get(), levelIn);
        setOwner(shooter);
    }

    @Override
    public float getSpeed() {
        return 1.75f;
    }


    @Override
    protected void doImpactSound(Holder<SoundEvent> sound) {
        level.playSound(null, getX(), getY(), getZ(), sound, SoundSource.NEUTRAL, 2, 1 );

    }

    @Override
    protected void onHitBlock(BlockHitResult blockHitResult) {
        super.onHitBlock(blockHitResult);
        discard();
    }

    @Override
    protected void onHitEntity(EntityHitResult entityHitResult) {
        super.onHitEntity(entityHitResult);
        var target = entityHitResult.getEntity();
        DamageSources.applyDamage(target, getDamage(), DSSpellRegistries.RUDE_BUSTER.get().getDamageSource(this, getOwner()));
        discard();
    }

    @Override
    public void impactParticles(double x, double y, double z) {
        MagicManager.spawnParticles(level, ParticleTypes.LAVA, x, y, z, 5, .1, .1, .1, .25, true);
    }

    @Override
    public void trailParticles() {
        float yHeading = -((float) (Mth.atan2(getDeltaMovement().z, getDeltaMovement().x) * (double) (180F / (float) Math.PI)) + 90.0F);
        float radius = .25f;
        int steps = 2;
        var vec = getDeltaMovement();
        double x2 = getX();
        double x1 = x2 - vec.x;
        double y2 = getY();
        double y1 = y2 - vec.y;
        double z2 = getZ();
        double z1 = z2 - vec.z;
        for (int j = 0; j < steps; j++) {
            float offset = (1f / steps) * j;
            double radians = ((tickCount + offset) / 7.5f) * 360 * Mth.DEG_TO_RAD;
            Vec3 swirl = new Vec3(Math.cos(radians) * radius, Math.sin(radians) * radius, 0).yRot(yHeading * Mth.DEG_TO_RAD);
            double x = Mth.lerp(offset, x1, x2) + swirl.x;
            double y = Mth.lerp(offset, y1, y2) + swirl.y + getBbHeight() / 2;
            double z = Mth.lerp(offset, z1, z2) + swirl.z;
            Vec3 jitter = Vec3.ZERO;//Utils.getRandomVec3(.05f);
            level.addParticle(DSParticlesRegistry.RUDE_BUSTER_PARTICLES::get, x, y, z, jitter.x, jitter.y, jitter.z);
        }
    }
    @Override
    public Optional<Holder<SoundEvent>> getImpactSound() {
        return Optional.of(DSSoundRegistry.RUDE_BUSTER_HIT);
    }

}