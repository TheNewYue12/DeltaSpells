package com.thenewyue12.deltaspellsmod.entity.spells.rudebuster;
import com.thenewyue12.deltaspellsmod.registry.DSEntityRegistry;
import com.thenewyue12.deltaspellsmod.registry.DSSoundRegistry;
import com.thenewyue12.deltaspellsmod.registry.DSSpellRegistry;
import io.redspace.ironsspellbooks.api.registry.SpellRegistry;
import io.redspace.ironsspellbooks.capabilities.magic.MagicManager;
import io.redspace.ironsspellbooks.damage.DamageSources;
import io.redspace.ironsspellbooks.entity.spells.AbstractMagicProjectile;
import io.redspace.ironsspellbooks.util.ParticleHelper;
import io.redspace.ironsspellbooks.api.util.Utils;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;

import java.util.Optional;

public class RudeBusterProjectile extends AbstractMagicProjectile {
    public RudeBusterProjectile(EntityType<? extends RudeBusterProjectile> entityType, Level level) {
        super(entityType, level);
        this.setNoGravity(true);
    }

    public RudeBusterProjectile(Level levelIn, LivingEntity shooter) {
        this(DSEntityRegistry.RUDE_BUSTER_PROJECTILE.get(), levelIn);
        setOwner(shooter);
    }

    @Override
    public float getSpeed() {
        return 1.75f;
    }

    @Override
    public Optional<SoundEvent> getImpactSound() {
        return Optional.of(DSSoundRegistry.RUDEBUSTER_HIT.get());
    }

    @Override
    protected void doImpactSound(SoundEvent sound) {
        level.playSound(null, getX(), getY(), getZ(), sound, SoundSource.NEUTRAL, 2, 1.2f + Utils.random.nextFloat() * .2f);

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
        DamageSources.applyDamage(target, getDamage(), DSSpellRegistry.RUDE_BUSTER.get().getDamageSource(this, getOwner()));
        discard();
    }

    @Override
    public void impactParticles(double x, double y, double z) {
   }

    @Override
    public void trailParticles() {

        for (int i = 0; i < 1; i++) {
            float yHeading = -((float) (Mth.atan2(getDeltaMovement().z, getDeltaMovement().x) * (double) (180F / (float) Math.PI)) + 90.0F);
            //float xHeading = -((float) (Mth.atan2(getDeltaMovement().horizontalDistance(), getDeltaMovement().y) * (double) (180F / (float) Math.PI)) - 90.0F);
            float radius = .25f;
            int steps = 6;
            for (int j = 0; j < steps; j++) {
                float offset = (1f / steps) * i;
                double radians = ((tickCount + offset) / 7.5f) * 360 * Mth.DEG_TO_RAD;
                Vec3 swirl = new Vec3(Math.cos(radians) * radius, Math.sin(radians) * radius, 0).yRot(yHeading * Mth.DEG_TO_RAD);
                double x = getX() + swirl.x;
                double y = getY() + swirl.y + getBbHeight() / 2;
                double z = getZ() + swirl.z;
                Vec3 jitter = Utils.getRandomVec3(.05f);
            }
            //level.addParticle(ParticleTypes.SMOKE, getX(), getY(), getZ(), 0, 0, 0);

        }
    }

}