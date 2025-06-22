package com.thenewyue12.deltaspells.registry;
import com.thenewyue12.deltaspellsmod.entity.spells.rudebuster.RudeBusterProjectile;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

public class DSEntityRegistry {  private static final DeferredRegister<EntityType<?>> ENTITIES =
        DeferredRegister.create(NeoForgeRegistries.ENTITY_TYPES, DeltaSpells.MODID);
    public static final RegistryObject<EntityType<RudeBusterProjectile>> RUDE_BUSTER_SLASH =
            ENTITIES.register("rude_buster_slash", () -> EntityType.Builder.<RudeBusterProjectile>of(RudeBusterProjectile::new, MobCategory.MISC)
                    .sized(1f, 1f)
                    .clientTrackingRange(64)
                    .build(new ResourceLocation(DeltaSpellsMod.MODID, "rude_buster_slash").toString()));
public static void register(IEventBus eventbus) {
    ENTITIES.register(eventbus);
}
}
