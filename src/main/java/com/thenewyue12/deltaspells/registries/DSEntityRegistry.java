package com.thenewyue12.deltaspells.registries;

import com.thenewyue12.deltaspells.DeltaSpells;
import com.thenewyue12.deltaspells.entities.spells.rudebuster.RudeBusterProjectile;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
public class DSEntityRegistry {
    private static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(Registries.ENTITY_TYPE, DeltaSpells.MODID);

    public static void register(IEventBus eventBus) {
        ENTITIES.register(eventBus);
    }
    public static final DeferredHolder<EntityType<?>, EntityType<RudeBusterProjectile>> RUDE_BUSTER=
            ENTITIES.register("rude_buster", () -> EntityType.Builder.<RudeBusterProjectile>of(RudeBusterProjectile::new, MobCategory.MISC)
                    .sized(8f, 1f)
                    .clientTrackingRange(64)
                    .build( ResourceLocation.fromNamespaceAndPath(DeltaSpells.MODID, "rude_buster").toString()));



}
