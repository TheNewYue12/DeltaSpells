package com.thenewyue12.deltaspells.entities.spells.rudebuster;
import com.thenewyue12.deltaspells.registries.DSEntityRegistry;
import com.thenewyue12.deltaspells.registries.DSParticlesRegistry;
import com.thenewyue12.deltaspells.registries.DSSoundRegistry;
import com.thenewyue12.deltaspells.registries.DSSpellRegistries;
import io.redspace.ironsspellbooks.api.util.Utils;
import io.redspace.ironsspellbooks.damage.DamageSources;
import io.redspace.ironsspellbooks.entity.spells.AbstractMagicProjectile;
import net.minecraft.core.Holder;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.*;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.phys.EntityHitResult;
import software.bernie.geckolib.animatable.GeoAnimatable;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.AnimatableManager;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.Optional;

public class RudeBusterProjectile extends AbstractMagicProjectile implements GeoAnimatable {

    private final AnimatableInstanceCache geoCache = GeckoLibUtil.createInstanceCache(this);
    public RudeBusterProjectile(EntityType<? extends Projectile> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.setNoGravity(true);
    }

    public RudeBusterProjectile(Level level, LivingEntity shooter)
    {
        this(DSEntityRegistry.RUDE_BUSTER.get(), level);
        setOwner(shooter);

    }
    @Override
    public void trailParticles() {
   }

    @Override
    public void impactParticles(double x, double y, double z) {

    }

    @Override
    public float getSpeed() {
        return 2f;
    }

    @Override
    public Optional<Holder<SoundEvent>> getImpactSound() {
        return Optional.of(DSSoundRegistry.RUDE_BUSTER_HIT);
    }


    @Override
    protected void doImpactSound(Holder<SoundEvent> sound) {
        level.playSound(null, getX(), getY(), getZ(), sound, SoundSource.NEUTRAL, 2, 1.2f + Utils.random.nextFloat() * .2f);

    }
    @Override
    protected void onHitEntity(EntityHitResult pResult) {
        var target = pResult.getEntity();
        DamageSources.applyDamage(target, damage,
                DSSpellRegistries.RUDE_BUSTER.get().getDamageSource(this, getOwner()));

        discard();
    }
    @Override
    protected void onHit(HitResult hitresult) {
        super.onHit(hitresult);

        discard();
    }
    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {

    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.geoCache;
    }

    @Override
    public double getTick(Object object) {
        return 0;
    }

}