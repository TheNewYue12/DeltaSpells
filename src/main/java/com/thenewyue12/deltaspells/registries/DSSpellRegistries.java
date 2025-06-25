package com.thenewyue12.deltaspells.registries;
import com.thenewyue12.deltaspells.DeltaSpells;
import com.thenewyue12.deltaspells.spells.rude.RudebusterSpell;
import io.redspace.ironsspellbooks.api.spells.AutoSpellConfig;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.bus.api.IEventBus;
import io.redspace.ironsspellbooks.api.registry.SpellRegistry;
import io.redspace.ironsspellbooks.api.spells.AbstractSpell;

import java.util.function.Supplier;

@AutoSpellConfig
public class DSSpellRegistries {
    public static final DeferredRegister<AbstractSpell> SPELLS = DeferredRegister.create(SpellRegistry.SPELL_REGISTRY_KEY, DeltaSpells.MODID);

    public static void register(IEventBus eventBus) {
        SPELLS.register(eventBus);
    }

    public static Supplier<AbstractSpell> registerSpell(AbstractSpell spell) {
        return SPELLS.register(spell.getSpellName(), () -> spell);
    }

    public static final Supplier<AbstractSpell> RUDE_BUSTER = registerSpell(new RudebusterSpell());
}