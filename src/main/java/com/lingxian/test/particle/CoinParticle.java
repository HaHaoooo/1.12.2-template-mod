package com.lingxian.test.particle;

import com.lingxian.test.Test;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.IParticleFactory;
import net.minecraft.client.particle.Particle;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

// 金币爆开的粒子
@Mod.EventBusSubscriber(modid = Test.MODID, value = Side.CLIENT)
@SideOnly(Side.CLIENT)
public class CoinParticle extends Particle implements IParticleFactory {

    private static final ResourceLocation PARTICLE_TEXTURE = new ResourceLocation(Test.MODID, "textures/particle/coin.png");


    public CoinParticle(World worldIn, double posXIn, double posYIn, double posZIn) {
        super(worldIn, posXIn, posYIn, posZIn);
        // 在构造函数中直接设置粒子纹理
        this.setParticleTexture(Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite(PARTICLE_TEXTURE.toString()));
        this.particleScale = 0.5F;
        this.motionX = this.rand.nextGaussian() * 0.02D;
        this.motionY = this.rand.nextGaussian() * 0.02D;
        this.motionZ = this.rand.nextGaussian() * 0.02D;
    }

    @Nullable
    @Override
    public Particle createParticle(int particleID, @Nonnull World worldIn, double xCoordIn, double yCoordIn, double zCoordIn, double xSpeedIn, double ySpeedIn, double zSpeedIn, @Nonnull int... p_178902_15_) {
         return new CoinParticle(worldIn, xCoordIn, yCoordIn, zCoordIn);
    }

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public static void textureBinding(TextureStitchEvent.Pre event) {
        // 在 TextureStitchEvent.Pre 事件中注册粒子纹理
        event.getMap().registerSprite(PARTICLE_TEXTURE);
    }
}