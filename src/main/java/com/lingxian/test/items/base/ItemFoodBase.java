package com.lingxian.test.items.base;

import com.lingxian.test.IModel;
import com.lingxian.test.Test;
import javafx.beans.value.ChangeListener;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

/**
 * @since 2024-05-11 14:05   @author jcj  @version 1.00
 * Description 制作食物物品的类
 */
public class ItemFoodBase extends ItemFood {
    //物品描述
    private String itemInfo;

    /**
     * 食物物品构造函数
     *
     * @param name       项目名称，传唯一项目名称代码
     * @param stackSize  最大堆叠数
     * @param tab        要把该项目放在创造模式背包里的哪个项目栏卡片中
     * @param health     瞬间恢复血量
     * @param saturation 瞬间恢复饱食度
     * @param isWolfFood 是否是狼食？
     * @author: jcj
     * @since: 2024-05-11 14:12
     */
    public ItemFoodBase(String name, int stackSize, CreativeTabs tab, String itemInfo, int health, float saturation, boolean isWolfFood) {
        super(health, saturation, isWolfFood);
        //使用公共方法设置基础物品信息
        ItemBaseInfoUtils.setItemBasicInformation(this, name, stackSize, tab);
        //在饱食度满的情况下也能吃
        setAlwaysEdible();
        //设置物品描述
        this.itemInfo = itemInfo;
    }

    /* public ItemFoodBase(String name, int stackSize, int health, float saturation, boolean isWolfFood) {
         //isWolfFood翻译过来是：是狼食，是否是用来控制狼的食物？
         super(health, saturation, isWolfFood);
         //使用公共方法设置基础物品信息
         ItemBaseInfoUtils.setItemBasicInformation(this, name, stackSize);
         this.setAlwaysEdible();
     }*/
    public static ItemFoodBase build(String name, int stackSize, CreativeTabs tab, String itemInfo, int health, float saturation) {
        return new ItemFoodBase(name, stackSize, tab, itemInfo, health, saturation, false);
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add(itemInfo);
    }


    @Override
    protected void onFoodEaten(ItemStack stack, World worldIn, EntityPlayer player) {
        super.onFoodEaten(stack, worldIn, player);
    }
}
