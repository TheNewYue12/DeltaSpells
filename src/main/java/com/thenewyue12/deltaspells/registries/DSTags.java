package com.thenewyue12.deltaspells.registries;

import com.thenewyue12.deltaspells.DeltaSpells;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
public class DSTags {
    public static final TagKey<Item> RUDE_FOCUS = ItemTags.create(ResourceLocation.fromNamespaceAndPath(DeltaSpells.MODID, "rude_focus"));

}
