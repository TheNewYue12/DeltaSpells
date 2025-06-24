package com.thenewyue12.deltaspells.particles;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.server.level.ServerLevel;

import javax.annotation.Nullable;


public class RudeBusterParticles extends TextureSheetParticle {
    protected RudeBusterParticles(ClientLevel level, double x, double y, double z, SpriteSet spriteSet,
                                  double xSpeed, double ySpeed, double zSpeed) {
super(level, x,y,z,xSpeed,ySpeed,zSpeed);
this.friction = 0;
this.setSpriteFromAge(spriteSet);
this.lifetime = 2;
this.rCol = 1f;
        this.gCol = 1f;
        this.bCol = 1f;

    }
    @Override
    public ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
    }
    public static class Provider implements ParticleProvider<SimpleParticleType>{

        private final SpriteSet spriteSet;

public Provider(SpriteSet spriteSet){
    this.spriteSet = spriteSet;
};



@Nullable
@Override
        public Particle createParticle(SimpleParticleType simpeparticleType, ClientLevel clientLevel, double px, double py, double pz, double pxSpeed, double pySpeed, double pzSpeed){


    return new RudeBusterParticles(clientLevel, px,py,pz,this.spriteSet,pxSpeed,pySpeed,pzSpeed);
};






    }
}
