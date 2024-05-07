package com.lingxian.test.particle;

import com.lingxian.test.Registry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.IParticleFactory;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

// 金币爆开的粒子
@SideOnly(Side.CLIENT)
public class CoinParticle extends Particle implements IParticleFactory {

    public CoinParticle(World worldIn, double posXIn, double posYIn, double posZIn, Item itemIn, int meta) {
        super(worldIn, posXIn, posYIn, posZIn);
        this.particleScale = 0.5F;
        this.motionX = this.rand.nextGaussian() * 0.1D;
        this.motionY = this.rand.nextGaussian() * 0.1D;
        this.motionZ = this.rand.nextGaussian() * 0.1D;
        this.setParticleTexture(Minecraft.getMinecraft().getRenderItem().getItemModelMesher().getParticleIcon(itemIn, meta));
    }

    @Nullable
    @Override
    public Particle createParticle(int particleID, @Nonnull World worldIn, double xCoordIn, double yCoordIn, double zCoordIn, double xSpeedIn, double ySpeedIn, double zSpeedIn, @Nonnull int... p_178902_15_) {
         for (int i = 0; i < 20; i++) { // 创建多个粒子实例
            CoinParticle particle = new CoinParticle(worldIn, xCoordIn, yCoordIn, zCoordIn, Registry.throwCoin, 0);
            Minecraft.getMinecraft().effectRenderer.addEffect(particle); // 添加到渲染器中
         }
         return null; // 返回 null，因为粒子不再由这里创建
    }

    @Override
    public void renderParticle(@Nonnull BufferBuilder buffer, @Nonnull Entity entityIn, float partialTicks, float rotationX, float rotationZ, float rotationYZ, float rotationXY, float rotationXZ) {
        float f = ((float)this.particleTextureIndexX + this.particleTextureJitterX / 4.0F) / 16.0F;
        float f1 = f + 0.015609375F;
        float f2 = ((float)this.particleTextureIndexY + this.particleTextureJitterY / 4.0F) / 16.0F;
        float f3 = f2 + 0.015609375F;
        float f4 = 0.1F * this.particleScale;

        if (this.particleTexture != null) {
            f = this.particleTexture.getInterpolatedU(this.particleTextureJitterX / 4.0F * 16.0F);
            f1 = this.particleTexture.getInterpolatedU((this.particleTextureJitterX + 1.0F) / 4.0F * 16.0F);
            f2 = this.particleTexture.getInterpolatedV(this.particleTextureJitterY / 4.0F * 16.0F);
            f3 = this.particleTexture.getInterpolatedV((this.particleTextureJitterY + 1.0F) / 4.0F * 16.0F);
        }

        float f5 = (float)(this.prevPosX + (this.posX - this.prevPosX) * (double)partialTicks - interpPosX);
        float f6 = (float)(this.prevPosY + (this.posY - this.prevPosY) * (double)partialTicks - interpPosY);
        float f7 = (float)(this.prevPosZ + (this.posZ - this.prevPosZ) * (double)partialTicks - interpPosZ);
        int i = this.getBrightnessForRender(partialTicks);
        int j = i >> 16 & 65535;
        int k = i & 65535;
        buffer.pos(f5 - rotationX * f4 - rotationXY * f4, f6 - rotationZ * f4, f7 - rotationYZ * f4 - rotationXZ * f4).tex(f, f3).color(this.particleRed, this.particleGreen, this.particleBlue, 1.0F).lightmap(j, k).endVertex();
        buffer.pos(f5 - rotationX * f4 + rotationXY * f4, f6 + rotationZ * f4, f7 - rotationYZ * f4 + rotationXZ * f4).tex(f, f2).color(this.particleRed, this.particleGreen, this.particleBlue, 1.0F).lightmap(j, k).endVertex();
        buffer.pos(f5 + rotationX * f4 + rotationXY * f4, f6 + rotationZ * f4, f7 + rotationYZ * f4 + rotationXZ * f4).tex(f1, f2).color(this.particleRed, this.particleGreen, this.particleBlue, 1.0F).lightmap(j, k).endVertex();
        buffer.pos(f5 + rotationX * f4 - rotationXY * f4, f6 - rotationZ * f4, f7 + rotationYZ * f4 - rotationXZ * f4).tex(f1, f3).color(this.particleRed, this.particleGreen, this.particleBlue, 1.0F).lightmap(j, k).endVertex();
    }

    // 粒子将渲染在前景图层上
    @Override
    public int getFXLayer() {
        return 1;
    }
}