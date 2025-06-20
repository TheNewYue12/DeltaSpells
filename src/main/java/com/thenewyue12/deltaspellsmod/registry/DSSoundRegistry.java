package com.thenewyue12.deltaspellsmod.registry;
import com.thenewyue12.deltaspellsmod.DeltaSpellsMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class DSSoundRegistry {
    private static final DeferredRegister<SoundEvent> SOUND_EVENTS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, DeltaSpellsMod.MODID);

    public static void register(IEventBus eventBus) {
        SOUND_EVENTS.register(eventBus);
    }

    public static RegistryObject<SoundEvent> RUDEBUSTER_CAST = registerSoundEvent("rudebuster_swing");
    public static RegistryObject<SoundEvent> RUDEBUSTER_HIT = registerSoundEvent("rudebuster_hit");
    private static RegistryObject<SoundEvent> registerSoundEvent(String name) {
        return SOUND_EVENTS.register(name, () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(DeltaSpellsMod.MODID, name)));
    }
}
