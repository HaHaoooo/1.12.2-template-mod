package com.lingxian.test.items.base;


import com.lingxian.test.IModel;
import com.lingxian.test.Test;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.common.MinecraftForge;

// 基本物品框架
public class ItemBase extends Item/* implements IModel*/ {
    public ItemBase(String name, int stackSize, CreativeTabs tab) {
        ItemBaseInfoUtils.setItemBasicInformation(this, name, stackSize, tab);
    }


   /* @Override
    public void RegisterModel() {
        Test.proxy.registerItemRenderer(this, 0, "inventory");
    }*/
}