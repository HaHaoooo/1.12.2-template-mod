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
        // 这是物品事件注册器，比如Coin物品里的TickEvent.PlayerTickEvent event，可以用于自定义事件
        MinecraftForge.EVENT_BUS.register(this);
    }

    @Override
    public void RegisterModel() {
        Test.proxy.registerItemRenderer(this, 0, "inventory");
    }
}