package com.lingxian.test.items.base;

import com.lingxian.test.utils.ItemBaseUtils;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

/**
 * @since 2024-05-11 14:05   @author jcj  @version 1.00
 * Description 制作食物物品的类
 */
public class ItemFoodBase extends ItemFood {

    //物品描述
    private final String itemInfo;

    private final List<PotionEffect> effects;

    /**
     * 食物物品构造函数
     *
     * @param name       项目名称，传唯一项目名称代码
     * @param stackSize  最大堆叠数
     * @param tab        要把该项目放在创造模式背包里的哪个项目栏卡片中
     * @param recoveryDose  回复饥饿值
     * @param saturation    回复饱和度（MC中的饱和度将会优先饥饿值被消耗。如果饱和度没了鸡腿ui就会开始抖动）
     * @param isWolfFood 是否是狼食？
     * &#064;author:  jcj
     * &#064;since:  2024-05-11 14:12
     */

    public ItemFoodBase(String name, int stackSize, CreativeTabs tab, String itemInfo, int recoveryDose, float saturation, boolean isWolfFood, List<PotionEffect> effects) {
        super(recoveryDose, saturation, isWolfFood);
        // 使用公共方法设置基础物品信息
        ItemBaseUtils.setItemBasicInformation(this, name, stackSize, tab);
        // 在饱食度满的情况下也能吃
        setAlwaysEdible();
        // 设置物品描述
        this.itemInfo = itemInfo;
        // 设置效果
        this.effects = effects;
    }

    //添加物品描述信息
    @Override
    public void addInformation(@Nonnull ItemStack stack, @Nullable World worldIn, List<String> tooltip, @Nonnull ITooltipFlag flagIn) {
        tooltip.add(itemInfo);
    }

    @Override
    protected void onFoodEaten(@Nonnull ItemStack stack, @Nonnull World worldIn, @Nonnull EntityPlayer player) {
        player.clearActivePotions();

        if (!worldIn.isRemote && effects != null && !effects.isEmpty()) {
            for (PotionEffect effect : effects) {
                player.addPotionEffect(effect);
            }
        }
    }
}
