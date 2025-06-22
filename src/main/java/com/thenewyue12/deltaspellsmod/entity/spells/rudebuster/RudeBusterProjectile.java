package com.thenewyue12.deltaspellsmod.entity.spells.rudebuster;
import com.thenewyue12.deltaspellsmod.registry.DSEntityRegistry;
import com.thenewyue12.deltaspellsmod.registry.DSSoundRegistry;
import com.thenewyue12.deltaspellsmod.registry.DSSpellRegistry;
import io.redspace.ironsspellbooks.damage.DamageSources;
import io.redspace.ironsspellbooks.entity.spells.AbstractMagicProjectile;
import io.redspace.ironsspellbooks.api.util.Utils;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;

import java.util.Optional;

public class RudeBusterProjectile extends AbstractMagicProjectile {
    public RudeBusterProjectile(EntityType<? extends RudeBusterProjectile> entityType, Level level) {
        super(entityType, level);
        this.setNoGravity(true);
    }

    public RudeBusterProjectile(Level levelIn, LivingEntity shooter) {
        this(DSEntityRegistry.RUDE_BUSTER_SLASH.get(), levelIn);
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
        DamageSources.applyDamage(target, getDamage(), DSSpellRegistry.rude_buster.get().getDamageSource(this, getOwner()));
        discard();
    }

    @Override
    public void impactParticles(double x, double y, double z) {
   }

    @Override
    public void trailParticles() {


    }

}