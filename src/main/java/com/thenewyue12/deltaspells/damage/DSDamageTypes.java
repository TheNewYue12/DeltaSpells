package com.thenewyue12.deltaspellsmod.damage;

import io.redspace.ironsspellbooks.IronsSpellbooks;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageScaling;
import net.minecraft.world.damagesource.DamageType;

public class DSDamageTypes {
    public static ResourceKey<DamageType> register(String name) {
        return ResourceKey.create(Registries.DAMAGE_TYPE, new ResourceLocation(IronsSpellbooks.MODID, name));
    }

    // Spell School Related
    public static final ResourceKey<DamageType> RUDE_MAGIC = register("rude_magic");

    public static void bootstrap(BootstapContext<DamageType> context) {
        context.register(RUDE_MAGIC, new DamageType(RUDE_MAGIC.location().getPath(), DamageScaling.WHEN_CAUSED_BY_LIVING_NON_PLAYER, 0f));
      }
}