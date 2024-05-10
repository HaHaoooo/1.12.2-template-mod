package com.lingxian.test.entity;

import net.minecraft.entity.EntityLiving;
import net.minecraft.world.World;

public class TennisBall extends EntityLiving {

    public TennisBall(World worldIn) {
        super(worldIn);
        this.setSize(0.6F, 0.6F);
    }
}
