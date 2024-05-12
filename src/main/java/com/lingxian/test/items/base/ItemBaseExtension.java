package com.lingxian.test.items.base;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

/**
 * @since 2024-05-11 10:23   @author jcj  @version 1.00
 * Description 基本物品类扩展
 */
public class ItemBaseExtension extends ItemBase {
    //物品描述
    private String itemInfo;


    public ItemBaseExtension(String name, int stackSize, CreativeTabs tab) {
        super(name, stackSize, tab);
    }
    //基本项目注册信息
    public ItemBaseExtension(String name, int stackSize, CreativeTabs tab, String itemInfo) {
        super(name, stackSize, tab);
        //设置物品描述信息
        this.itemInfo = itemInfo;
    }

    //静态build构造方法
    public static ItemBaseExtension build(String name, int stackSize, CreativeTabs tab) {
        return new ItemBaseExtension(name, stackSize, tab);
    }

    public static ItemBaseExtension build(String name, int stackSize, CreativeTabs tab, String itemInfo) {
        return new ItemBaseExtension(name, stackSize, tab, itemInfo);
    }

    //描述
    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        //System.out.println("ItemBaseExtension:物品描述：" + itemInfo);
        tooltip.add(itemInfo);
    }
}
