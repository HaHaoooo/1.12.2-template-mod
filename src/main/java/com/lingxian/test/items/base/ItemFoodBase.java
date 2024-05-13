package com.lingxian.test.items.base;

import com.lingxian.test.IModel;
import com.lingxian.test.Test;
import javafx.beans.value.ChangeListener;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
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
    //效果
    private List<PotionEffect> potionEffects;

    /**
     * 食物物品构造函数
     *
     * @param name       项目名称，传唯一项目名称代码
     * @param stackSize  最大堆叠数
     * @param tab        要把该项目放在创造模式背包里的哪个项目栏卡片中
     * @param recoveryDose  回复饥饿值
     * @param saturation    回复饱和度（MC中的饱和度将会优先饥饿值被消耗。如果饱和度没了鸡腿ui就会开始抖动）
     * @param isWolfFood 是否是狼食？
     * @author: jcj
     * @since: 2024-05-11 14:12
     */
    public ItemFoodBase(String name, int stackSize, CreativeTabs tab, String itemInfo, int recoveryDose, float saturation, boolean isWolfFood, List<PotionEffect> potionEffects) {
        super(recoveryDose, saturation, isWolfFood);
        //使用公共方法设置基础物品信息
        ItemBaseInfoUtils.setItemBasicInformation(this, name, stackSize, tab);
        //在饱食度满的情况下也能吃
        setAlwaysEdible();
        //设置物品描述
        this.itemInfo = itemInfo;
        //设置药水效果
        this.potionEffects = potionEffects;
    }

    /* public ItemFoodBase(String name, int stackSize, int recoveryDose, float saturation, boolean isWolfFood) {
         //isWolfFood翻译过来是：是狼食，是否是用来控制狼的食物？
         super(recoveryDose, saturation, isWolfFood);
         //使用公共方法设置基础物品信息
         ItemBaseInfoUtils.setItemBasicInformation(this, name, stackSize);
         this.setAlwaysEdible();
     }*/
    public static ItemFoodBase build(String name, int stackSize, CreativeTabs tab, String itemInfo, int recoveryDose, float saturation) {
        return new ItemFoodBase(name, stackSize, tab, itemInfo, recoveryDose, saturation, false, null);
    }

    public static ItemFoodBase build(String name, int stackSize, CreativeTabs tab, String itemInfo, int recoveryDose, float saturation, List<PotionEffect> potionEffects) {
        return new ItemFoodBase(name, stackSize, tab, itemInfo, recoveryDose, saturation, false, potionEffects);
    }

    //添加物品描述信息
    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add(itemInfo);
    }

    //添加实用后的效果
    @Override
    protected void onFoodEaten(ItemStack stack, World worldIn, EntityPlayer player) {
        //非空判断
        if (potionEffects != null) {
            for (PotionEffect potionEffect : potionEffects) {
                player.addPotionEffect(potionEffect);
            }
        }
    }
}
