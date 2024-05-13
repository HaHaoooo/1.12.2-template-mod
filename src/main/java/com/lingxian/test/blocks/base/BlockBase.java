package com.lingxian.test.blocks.base;

import com.lingxian.test.Test;
import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Test.MODID)
public class BlockBase extends Block{

    public BlockBase(Material blockMaterialIn, MapColor blockMapColorIn, String name, CreativeTabs tab) {
        super(blockMaterialIn, blockMapColorIn);
        this.setUnlocalizedName(Test.MODID + "." + name);
        this.setRegistryName(Test.MODID, name);
        this.setCreativeTab(tab);
        MinecraftForge.EVENT_BUS.register(this);
    }
}
