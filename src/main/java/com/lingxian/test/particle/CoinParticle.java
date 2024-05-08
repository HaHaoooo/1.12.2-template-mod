package com.lingxian.test.particle;

import com.lingxian.test.Registry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.*;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

// 金币爆开的粒子
@SideOnly(Side.CLIENT)
public class CoinParticle extends Particle{

    public CoinParticle(World worldIn, double posXIn, double posYIn, double posZIn) {
        super(worldIn, posXIn, posYIn, posZIn);
        this.particleScale = 1.5F;
        this.motionX = this.rand.nextGaussian() * 0.1D;
        this.motionY = this.rand.nextGaussian() * 0.1D;
        this.motionZ = this.rand.nextGaussian() * 0.1D;
        this.setParticleTexture(Minecraft.getMinecraft().getRenderItem().getItemModelMesher().getParticleIcon(Registry.throwCoin));
    }

    // 粒子将渲染在前景图层上
    @Override
    public int getFXLayer() {
        return 1;
    }

    @Override
    public void onUpdate() {
        super.onUpdate();
        if (!this.onGround) {
            this.motionY -= 0.04D;
        }
    }

    public static class Factory implements IParticleFactory {

        @Nullable
        @Override
        public Particle createParticle(int particleID, @Nonnull World worldIn, double xCoordIn, double yCoordIn, double zCoordIn, double xSpeedIn, double ySpeedIn, double zSpeedIn, @Nonnull int... p_178902_15_) {
             for (int i = 0; i < 10; i++) { // 创建多个粒子实例
                CoinParticle particle = new CoinParticle(worldIn, xCoordIn, yCoordIn, zCoordIn);
                Minecraft.getMinecraft().effectRenderer.addEffect(particle); // 添加到渲染器中
             }
             return null; // 返回 null，因为粒子不再由这里创建
        }
    }
}