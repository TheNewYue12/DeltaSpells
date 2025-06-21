package com.thenewyue12.deltaspellsmod.registry;

import com.thenewyue12.deltaspellsmod.DeltaSpellsMod;
import io.redspace.ironsspellbooks.IronsSpellbooks;
import io.redspace.ironsspellbooks.api.registry.SpellRegistry;
import io.redspace.ironsspellbooks.api.spells.ISpellContainer;
import io.redspace.ironsspellbooks.capabilities.magic.SpellContainer;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;


@Mod.EventBusSubscriber(modid = IronsSpellbooks.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DSCreativeTabRegistry {
    private static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, DeltaSpellsMod.MODID);

    public static void register(IEventBus eventBus) {
        TABS.register(eventBus);
    }
    public static final RegistryObject<CreativeModeTab> SPELLS_TAB = TABS.register("spellbook_scrolls", () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup." + DeltaSpellsMod.MODID + ".spellbook_scrolls_tab"))
            .icon(() -> new ItemStack(DSItemRegistry.SPELL.get()))
            .build());
    public static void fillCreativeTabs(final BuildCreativeModeTabContentsEvent event) {


        if (event.getTab() == CreativeModeTabs.searchTab() || event.getTab() == SPELLS_TAB.get()) {
            SpellRegistry.getEnabledSpells().stream()
                    .filter(spellType -> spellType != SpellRegistry.none())
                    .forEach(spell -> {
                        for (int i = spell.getMinLevel(); i <= spell.getMaxLevel(); i++) {
                            var itemstack = new ItemStack(DSItemRegistry.SPELL.get());
                            var spellList = ISpellContainer.createScrollContainer(spell, i, itemstack);
                            spellList.save(itemstack);
                            event.accept(itemstack);
                        }
                    });
            if (event.getTab() == BuiltInRegistries.CREATIVE_MODE_TAB.get(CreativeModeTabs.NATURAL_BLOCKS)) {
                event.accept(DSItemRegistry.SPELL.get());
            }

        }

    }
}
