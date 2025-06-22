package com.thenewyue12.deltaspells.registry;
import com.thenewyue12.deltaspellsmod.damage.DSDamageTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import com.thenewyue12.deltaspellsmod.DeltaSpellsMod;
import com.thenewyue12.deltaspellsmod.util.DSTags;
import io.redspace.ironsspellbooks.api.registry.SchoolRegistry;
import io.redspace.ironsspellbooks.api.spells.SchoolType;
import io.redspace.ironsspellbooks.registries.SoundRegistry;
import com.thenewyue12.deltaspellsmod.util.DSTags;

public class DSSchoolRegistry extends SchoolRegistry {
    private static final DeferredRegister<SchoolType> DELTASPELLS_SCHOOLS = DeferredRegister.create(SCHOOL_REGISTRY_KEY, DeltaSpellsMod.MODID);

    public static void register(IEventBus eventBus) {
        DELTASPELLS_SCHOOLS.register(eventBus);
    }

    private static RegistryObject<SchoolType> registerSchool(SchoolType type) {
        return DELTASPELLS_SCHOOLS.register(type.getId().getPath(), () -> type);
    }

    // Abyssal
    public static final ResourceLocation RUDE_RESOURCE = DeltaSpellsMod.id("rude");

    public static final RegistryObject<SchoolType> RUDE = registerSchool(new SchoolType
            (
                    RUDE_RESOURCE,
                    DSTags.RUDE_FOCUS,
                    Component.translatable("school.deltaspells.rude").withStyle(Style.EMPTY.withColor(0xd0f9ff)),
                   LazyOptional.of(DSAttributeRegistry.RUDE_MAGIC_POWER::get),
                    LazyOptional.of(DSAttributeRegistry.RUDE_MAGIC_RESIST::get),
                    LazyOptional.of(SoundRegistry.EVOCATION_CAST::get),
                    DSDamageTypes.RUDE_MAGIC
            ));
}