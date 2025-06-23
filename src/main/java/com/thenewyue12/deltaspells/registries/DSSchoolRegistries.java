package com.thenewyue12.deltaspells.registries;
import com.thenewyue12.deltaspells.DeltaSpells;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.resources.ResourceLocation;
import io.redspace.ironsspellbooks.api.registry.SchoolRegistry;
import net.neoforged.bus.EventBus;
import net.neoforged.bus.api.IEventBus;
import io.redspace.ironsspellbooks.api.spells.SchoolType;
import io.redspace.ironsspellbooks.registries.SoundRegistry;
import net.neoforged.neoforge.registries.DeferredRegister;
import java.util.function.Supplier;

import javax.annotation.Nullable;
import java.util.Optional;
import java.util.function.Supplier;
import com.thenewyue12.deltaspells.registries.DSTags;
import io.redspace.ironsspellbooks.api.registry.SchoolRegistry;
import io.redspace.ironsspellbooks.api.spells.SchoolType;
import io.redspace.ironsspellbooks.registries.SoundRegistry;
import com.thenewyue12.deltaspells.damagetypes.*;
import net.minecraft.ChatFormatting;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

public class DSSchoolRegistries extends SchoolRegistry{
    private static final DeferredRegister<SchoolType> DELTASPELLS_SCHOOLS = DeferredRegister.create(SCHOOL_REGISTRY_KEY, DeltaSpells.MODID);

    public static void register(IEventBus eventBus) {

        DELTASPELLS_SCHOOLS.register(eventBus);
    }

    private static Holder<SchoolType> registerSchool(SchoolType type) {
        return DELTASPELLS_SCHOOLS.register(type.getId().getPath(), () -> type);
    }






    @Nullable
    public static SchoolType getSchoolFromFocus(ItemStack focusStack) {

        for (SchoolType school : REGISTRY) {
            if (school.isFocus(focusStack)) {
                return school;
            }
        }
        return null;
    }
    public static final ResourceLocation RUDE_RESOURCE = ResourceLocation.fromNamespaceAndPath(DeltaSpells.MODID, "rude");


    public static final Supplier<SchoolType> RUDE = (Supplier<SchoolType>) registerSchool(new SchoolType(

                    RUDE_RESOURCE,
                    DSTags.RUDE_FOCUS,
                    Component.translatable("school.deltaspells.rude").withStyle(ChatFormatting.RED),
                    DSAttributeRegistry.RUDE_SPELL_POWER,
                    DSAttributeRegistry.RUDE_MAGIC_RESIST,
                    SoundRegistry.EVOCATION_CAST,
            DSDamageTypes.RUDE_MAGIC


        ));

}
