package com.lingxian.test.utils;

import com.lingxian.test.Test;
import com.lingxian.test.items.base.ItemBaseExtension;
import com.lingxian.test.items.base.ItemFoodBase;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.common.MinecraftForge;


/**
 * @since 2024-05-11 15:30   @author jcj  @version 1.00
 * Description 物品创建工具链
 */
public class ItemCreateUtils {
    //注册普通物品
    public static ItemBaseExtension buildItemBase(String name, int stackSize, CreativeTabs tab, String itemInfo) {
        return new ItemBaseExtension(name, stackSize, tab, itemInfo);
    }
    //注册食物
    /*public static ItemFoodBase buildItemFoodBase(String name, int stackSize, CreativeTabs tab, String itemInfo, int health, float saturation, boolean isWolfFood) {
        return new ItemFoodBase(name, stackSize, tab, itemInfo, health, saturation, isWolfFood);
    }*/






    /**
     * 设置项目基本信息公共方法
     *
     * @param item      传入项目
     * @param name      项目名称，传唯一项目名称代码
     * @param stackSize 最大堆叠数
     * @param tab       要把该项目放在创造模式背包里的哪个项目栏卡片中
     * @author: jcj
     * @since: 2024-05-11 14:51
     */
    /*public static void setItemBasicInformation(Item item, String name, int stackSize, CreativeTabs tab) {
        item.setUnlocalizedName(Test.MODID + "." + name);
        item.setRegistryName(Test.MODID, name);
        if (tab != null) {
            item.setCreativeTab(tab);
        }
        item.setMaxStackSize(stackSize);
        // 这是物品事件注册器，比如Coin物品里的TickEvent.PlayerTickEvent event，可以用于自定义事件
        MinecraftForge.EVENT_BUS.register(item);
    }

    //重载方法
    public static void setItemBasicInformation(Item item, String name, int stackSize) {
        setItemBasicInformation(item, name, stackSize, CreativeTabs.MISC);
    }*/
}
