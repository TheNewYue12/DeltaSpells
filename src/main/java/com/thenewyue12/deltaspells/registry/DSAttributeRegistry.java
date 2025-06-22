package com.thenewyue12.deltaspells.registry;
import com.thenewyue12.deltaspellsmod.DeltaSpellsMod;
import io.redspace.ironsspellbooks.api.attribute.MagicRangedAttribute;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraftforge.event.entity.EntityAttributeModificationEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(modid = DeltaSpells.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DSAttributeRegistry {
    private static final DeferredRegister<Attribute> ATTRIBUTES = DeferredRegister.create(ForgeRegistries.ATTRIBUTES, DeltaSpellsMod.MODID);

    public static final RegistryObject<Attribute> RUDE_MAGIC_RESIST = registerResistanceAttribute("rude");
    public static final RegistryObject<Attribute> RUDE_MAGIC_POWER = registerPowerAttribute("rude");


    @SubscribeEvent
    public static void modifyEntityAttributes(EntityAttributeModificationEvent event)
    {
        event.getTypes().forEach(entity -> {
            event.add(entity, RUDE_MAGIC_RESIST.get());
            event.add(entity, RUDE_MAGIC_POWER.get());
        });

        event.getTypes().forEach(entity -> ATTRIBUTES.getEntries().forEach(attribute -> event.add(entity, attribute.get())));
    }

    // ;_;
    private static RegistryObject<Attribute> registerResistanceAttribute(String id)
    {
        return ATTRIBUTES.register(id + "_magic_resist", () ->
                (new MagicRangedAttribute("attribute.deltaspells." + id + "_magic_resist",
                        1.0D, 0, 10).setSyncable(true)));
    }

    private static RegistryObject<Attribute> registerPowerAttribute(String id)
    {
        return ATTRIBUTES.register(id + "_spell_power", () ->
                (new MagicRangedAttribute("attribute.deltaspells." + id + "_spell_power",
                        1.0D, 0, 10).setSyncable(true)));
    }

    public static void register(IEventBus eventBus)
    {
        ATTRIBUTES.register(eventBus);
    }
}