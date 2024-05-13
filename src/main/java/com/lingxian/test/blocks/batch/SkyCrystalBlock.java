package com.lingxian.test.blocks.batch;

import com.lingxian.test.blocks.base.BlockBase;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

public class SkyCrystalBlock extends BlockBase {

    public static SkyCrystalBlock INSTANCE = new SkyCrystalBlock("sky_crystal");

    public SkyCrystalBlock(String name) {
        super(Material.GLASS, MapColor.AIR, name, CreativeTabs.BUILDING_BLOCKS);
    }
}
