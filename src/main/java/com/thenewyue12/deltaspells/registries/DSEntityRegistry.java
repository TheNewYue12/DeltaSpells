package com.thenewyue12.deltaspells.registries;

import com.thenewyue12.deltaspells.DeltaSpells;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import com.thenewyue12.deltaspells.entities.spells.rudebuster.RudeBusterProjectile;
import com.thenewyue12.deltaspells.entities.spells.rudebuster.RudeBusterModel;
public class DSEntityRegistry {
    private static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(Registries.ENTITY_TYPE, DeltaSpells.MODID);

    public static void register(IEventBus eventBus) {
        ENTITIES.register(eventBus);
    }

    public static final DeferredHolder<EntityType<?>, EntityType<RudeBusterProjectile>> RUDE_BUSTER =
            ENTITIES.register("rude_buster", () -> EntityType.Builder.<RudeBusterProjectile>of(RudeBusterProjectile::new, MobCategory.MISC)
                    .sized(.5f, .5f)
                    .clientTrackingRange(64)
                    .build(new ResourceLocation(DeltaSpells.MODID, "rudebuster_entity").toString()));


}
