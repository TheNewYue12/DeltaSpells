package com.thenewyue12.deltaspells.registries;

import com.thenewyue12.deltaspells.DeltaSpells;
import io.redspace.ironsspellbooks.api.attribute.MagicRangedAttribute;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.neoforge.event.entity.EntityAttributeModificationEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
@EventBusSubscriber(value = Dist.CLIENT, modid = DeltaSpells.MODID, bus = EventBusSubscriber.Bus.GAME)
public class DSAttributeRegistry {
    private static final DeferredRegister<Attribute> ATTRIBUTES = DeferredRegister.create(Registries.ATTRIBUTE, DeltaSpells.MODID);


    public static final DeferredHolder<Attribute, Attribute> RUDE_SPELL_POWER = newPowerAttribute("rude");

    public static final DeferredHolder<Attribute, Attribute> RUDE_MAGIC_RESIST = newResistanceAttribute("rude");


    @SubscribeEvent
    public static void modifyEntityAttributes(EntityAttributeModificationEvent e) {
        e.getTypes().forEach(entity -> ATTRIBUTES.getEntries().forEach(attribute -> e.add(entity, attribute)));
    }
    private static DeferredHolder<Attribute, Attribute> newResistanceAttribute(String id) {
        return ATTRIBUTES.register(id + "_magic_resist", () ->
                (new MagicRangedAttribute("attribute.deltaspells." + id + "_magic_resist",
                        1.0D, 0, 10).setSyncable(true)));  }

    private static DeferredHolder<Attribute, Attribute> newPowerAttribute(String id) {

        return  ATTRIBUTES.register(id + "_spell_power", () ->
                (new MagicRangedAttribute("attribute.deltaspells." + id + "_spell_power",
                        1.0D, 0, 10).setSyncable(true)));}

    public static void register(IEventBus eventBus)
    {
        ATTRIBUTES.register(eventBus);
    }
}
