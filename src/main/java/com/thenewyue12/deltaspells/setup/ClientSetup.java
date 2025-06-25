package com.thenewyue12.deltaspells.setup;

import com.thenewyue12.deltaspells.DeltaSpells;
import com.thenewyue12.deltaspells.entities.renderers.rudebuster.RudeBusterRenderer;
import com.thenewyue12.deltaspells.entities.spells.rudebuster.RudeBusterModel;
import com.thenewyue12.deltaspells.entities.spells.rudebuster.RudeBusterProjectile;
import com.thenewyue12.deltaspells.particles.RudeBusterParticles;
import com.thenewyue12.deltaspells.registries.DSEntityRegistry;
import com.thenewyue12.deltaspells.registries.DSParticlesRegistry;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.*;


@EventBusSubscriber(modid = DeltaSpells.MODID, bus = EventBusSubscriber.Bus.MOD, value = {Dist.CLIENT})

public class ClientSetup {
    @SubscribeEvent
    public static void rendererRegister(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(DSEntityRegistry.RUDE_BUSTER.get(), RudeBusterRenderer::new);
    }

    @SubscribeEvent
    public static void registerParticles(RegisterParticleProvidersEvent event) {
        event.registerSpriteSet(DSParticlesRegistry.RUDE_BUSTER_PARTICLES.get(), RudeBusterParticles.Provider::new);
    }


    }
