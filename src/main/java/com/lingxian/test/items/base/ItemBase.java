package com.lingxian.test.items.base;


import com.lingxian.test.utils.ItemBaseUtils;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

// 基本物品框架
public class ItemBase extends Item {
    public ItemBase(String name, int stackSize, CreativeTabs tab) {
        ItemBaseUtils.setItemBasicInformation(this, name, stackSize, tab);
    }
}