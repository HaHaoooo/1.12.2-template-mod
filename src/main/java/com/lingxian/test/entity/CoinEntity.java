package com.lingxian.test.entity;

import com.lingxian.test.particle.CoinParticle;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;

public class CoinEntity extends EntityThrowable {

    // 必备构造函数，不可删
    public CoinEntity(World worldIn) {
        super(worldIn);
    }


    public CoinEntity(World worldIn, EntityLivingBase throwerIn) {
        super(worldIn, throwerIn);
    }

    public static void registerFixesCoinEntity(DataFixer fixer) {
        EntityThrowable.registerFixesThrowable(fixer, "CoinEntity");
    }

    // 生成破碎粒子
    @SideOnly(Side.CLIENT)
    public void handleStatusUpdate(byte id) {
        if (id == 3) {
            CoinParticle coinParticle = new CoinParticle(world, posX, posY, posZ);
            for (int i = 0; i < 8; ++i) {
                Minecraft.getMinecraft().effectRenderer.addEffect(coinParticle);
            }
        }
    }

    // 攻击产生的效果
    @Override
    protected void onImpact(@Nonnull RayTraceResult result) {
        if (result.entityHit != null) {
            int i = 0;
            if (result.entityHit instanceof EntityPlayer) {
                i = 5;
            }
            result.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getThrower()), (float)i);
        }
        if (!this.world.isRemote) {
            this.world.setEntityState(this, (byte)3);
            this.setDead();
        }
    }
}
