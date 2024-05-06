package com.lingxian.test.items;


import com.lingxian.test.IModel;
import com.lingxian.test.Test;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.common.MinecraftForge;

// 基本物品框架
public class ItemBase extends Item implements IModel {
    public ItemBase(String name, int stackSize, CreativeTabs tab) {
        setUnlocalizedName(Test.MODID + "." + name);
        setRegistryName(Test.MODID, name);
        setCreativeTab(tab);
        setMaxStackSize(stackSize);
        //这玩意报错，我给它注释掉了
        //MinecraftForge.EVENT_BUS.register(this);
    }

    @Override
    public void RegisterModel() {
        Test.proxy.registerItemRenderer(this, 0, "inventory");
    }
}