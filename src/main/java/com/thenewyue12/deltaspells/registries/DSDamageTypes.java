package com.thenewyue12.deltaspells.registries;

import com.thenewyue12.deltaspells.DeltaSpells;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageScaling;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.level.levelgen.DebugLevelSource;

public class DSDamageTypes {


    public static ResourceKey<DamageType> register(String name) {
        return ResourceKey.create(Registries.DAMAGE_TYPE, ResourceLocation.fromNamespaceAndPath(DeltaSpells.MODID, name));
    }

    public static final ResourceKey<DamageType> RUDE_MAGIC = register("rude_magic");


    public static void bootstrap(BootstrapContext<DamageType> context) {
        context.register(RUDE_MAGIC, new DamageType(RUDE_MAGIC.location().getPath(), DamageScaling.WHEN_CAUSED_BY_LIVING_NON_PLAYER, 0f));

    }
}