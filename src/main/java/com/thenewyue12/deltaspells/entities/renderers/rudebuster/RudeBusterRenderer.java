package com.thenewyue12.deltaspells.entities.renderers.rudebuster;

import com.thenewyue12.deltaspells.entities.spells.rudebuster.RudeBusterModel;
import com.thenewyue12.deltaspells.entities.spells.rudebuster.RudeBusterProjectile;
import software.bernie.geckolib.renderer.GeoEntityRenderer;


import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;



public class RudeBusterRenderer extends GeoEntityRenderer<RudeBusterProjectile> {


    public RudeBusterRenderer(Context context) {
        super(context,new RudeBusterModel());
    }



}
