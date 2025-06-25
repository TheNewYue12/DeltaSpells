package com.thenewyue12.deltaspells.util;

import net.minecraft.world.entity.LivingEntity;

public class DSUtils {
    public static double getEyeHeight(LivingEntity entity)
    {
        return entity.getY() + entity.getEyeHeight() - 0.2;
    }
}
