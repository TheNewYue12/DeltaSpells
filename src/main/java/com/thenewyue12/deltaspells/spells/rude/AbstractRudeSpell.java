package com.thenewyue12.deltaspells.spells.rude;
import io.redspace.ironsspellbooks.api.spells.AbstractSpell;

public abstract class AbstractRudeSpell extends AbstractSpell {

    // Yeah this is pretty much it
    @Override
    public boolean allowLooting() {
        return true;
    }
}