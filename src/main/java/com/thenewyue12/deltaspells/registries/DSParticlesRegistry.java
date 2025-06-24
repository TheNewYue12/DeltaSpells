package com.thenewyue12.deltaspells.registries;

import com.thenewyue12.deltaspells.DeltaSpells;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class DSParticlesRegistry{
    public static final DeferredRegister<ParticleType<?>> PARTICLE_TYPES =
            DeferredRegister.create(BuiltInRegistries.PARTICLE_TYPE, DeltaSpells.MODID);
 public static final Supplier<SimpleParticleType> RUDE_BUSTER_PARTICLES =
         PARTICLE_TYPES.register("rude_buster_particles", () -> new SimpleParticleType(true));


    public static void register(IEventBus eventBus){
        PARTICLE_TYPES.register(eventBus);
    }
}
