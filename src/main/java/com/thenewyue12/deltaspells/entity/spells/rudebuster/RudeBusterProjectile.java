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
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.builder.ILoopType;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.util.GeckoLibUtil;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;

import java.util.Optional;

public class RudeBusterProjectile extends AbstractMagicProjectile  implements IAnimatable{
    private static final EntityDataAccessor<Boolean> SOUL;

    private final AnimationFactory factory = GeckoLibUtil.createFactory(this);
    static {
        SOUL = SynchedEntityData.defineId(RudeBusterProjectile.class, EntityDataSerializers.BOOLEAN);
    }

    public RudeBusterProjectile(EntityType<? extends Projectile> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.setNoGravity(true);
    }

    public RudeBusterProjectile(Level level, LivingEntity shooter)
    {
        this(DSEntityRegistry.RUDE_BUSTER_SLASH.get(), level);
        setOwner(shooter);
    }

    @Override
    public void travel() {
        this.setPos(this.position().add(this.getDeltaMovement()));
        if (!this.isNoGravity())
        {
            Vec3 vec3 = this.getDeltaMovement();
            this.setDeltaMovement(vec3.x, vec3.y - 0.05000000074505806, vec3.z);
        }
    }

    @Override
    public void tick() {
        Vec3 deltaMovement = getDeltaMovement();
        double distance = deltaMovement.horizontalDistance();

        double x = deltaMovement.x;
        double y = deltaMovement.y;
        double z = deltaMovement.z;

        setYRot((float) (Mth.atan2(x, z) * (180 / Math.PI)));
        setXRot((float) (Mth.atan2(y, distance) * (180 / Math.PI)));
        setXRot(lerpRotation(xRotO, getXRot()));
        setYRot(lerpRotation(yRotO, getYRot()));
        super.tick();
    }

    @Override
    public void trailParticles() {
  }

    @Override
    public void impactParticles(double x, double y, double z) {
       }

    @Override
    public float getSpeed() {
        return 0.7f;
    }

    @Override
    public Optional<SoundEvent> getImpactSound() {
        return Optional.of(DSSoundRegistry.RUDEBUSTER_HIT.get());
    }

    @Override
    protected void doImpactSound(SoundEvent sound) {
        level.playSound(null, getX(), getY(), getZ(), sound, SoundSource.NEUTRAL, 1.5f, 0.5f);
    }

    @Override
    protected void onHitEntity(EntityHitResult pResult) {
        var target = pResult.getEntity();
        DamageSources.applyDamage(target, damage,
                DSSpellRegistry.rude_buster.get().getDamageSource(this, getOwner()));

        discard();
    }

    @Override
    protected void onHitBlock(BlockHitResult pResult) {
        super.onHitBlock(pResult);
        discard();
    }

    public boolean getIsSoul()
    {
        return this.entityData.get(SOUL);
    }

    public void setIsSoul(boolean soul)
    {
        this.entityData.set(SOUL, soul);
    }

    // Geckolib
    @Override
    public void registerControllers(AnimationData data) {

    }

    private <E extends IAnimatable>PlayState predicate(AnimationEvent<E> event)
    {
        AnimationBuilder builder = new AnimationBuilder();
        builder.addAnimation("animation.infernal_blade_small.idle", ILoopType.EDefaultLoopTypes.LOOP);
        event.getController().setAnimation(builder);

        return PlayState.CONTINUE;
    }

    @Override
    public AnimationFactory getFactory() {
        return factory;
    }


    // NBT


}