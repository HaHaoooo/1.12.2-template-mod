package com.lingxian.test.entity;

import com.lingxian.test.particle.CoinParticle;
import com.lingxian.test.sound.EnumSounds;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;

public class CoinEntity extends EntityThrowable {

    public static float attackDamage = 3;

    // 必备构造函数，不可删
    @SideOnly(Side.CLIENT)
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
            CoinParticle.Factory coinParticle = new CoinParticle.Factory();
            coinParticle.createParticle(100, this.world, posX, posY, posZ, 0.1, 0.1, 0.1, 1);
            this.world.playSound(this.posX, this.posY, this.posZ, EnumSounds.COIN_SHOOT.getSoundEvent(), SoundCategory.MASTER, 1F, 1F, false);
        }
    }

    // 攻击产生的效果
    @Override
    protected void onImpact(@Nonnull RayTraceResult result) {
        if (result.entityHit != null) {
            result.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getThrower()), attackDamage);
        }
        if (!this.world.isRemote) {
            this.world.setEntityState(this, (byte)3);
            this.setDead();
        }
    }
}
