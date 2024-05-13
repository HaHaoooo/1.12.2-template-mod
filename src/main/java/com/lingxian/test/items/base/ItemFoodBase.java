package com.lingxian.test.items.base;

import com.lingxian.test.utils.ItemBaseUtils;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
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
    //效果
    private final List<PotionEffectConstruction> potionEffects;

    //附魔效果
    @Override
    public boolean hasEffect(@Nonnull ItemStack stack) {
        super.hasEffect(stack);
        return true;
    }

    /**
     * 食物物品构造函数
     *
     * @param name         项目名称，传唯一项目名称代码
     * @param stackSize    最大堆叠数
     * @param tab          要把该项目放在创造模式背包里的哪个项目栏卡片中
     * @param recoveryDose 回复饥饿值
     * @param saturation   回复饱和度（MC中的饱和度将会优先饥饿值被消耗。如果饱和度没了鸡腿ui就会开始抖动）
     * @param isWolfFood   是否是狼食？
     * &#064;author:  jcj
     * &#064;since:  2024-05-11 14:12
     */
    public ItemFoodBase(String name, int stackSize, CreativeTabs tab, String itemInfo, int recoveryDose, float saturation, boolean isWolfFood, List<PotionEffectConstruction> potionEffects) {
        super(recoveryDose, saturation, isWolfFood);
        //使用公共方法设置基础物品信息
        ItemBaseUtils.setItemBasicInformation(this, name, stackSize, tab);
        //在饱食度满的情况下也能吃
        setAlwaysEdible();
        //设置物品描述
        this.itemInfo = itemInfo;
        //设置药水效果
        this.potionEffects = potionEffects;
    }

    public static ItemFoodBase build(String name, int stackSize, CreativeTabs tab, String itemInfo, int recoveryDose, float saturation) {
        return new ItemFoodBase(name, stackSize, tab, itemInfo, recoveryDose, saturation, false, null);
    }

    public static ItemFoodBase build(String name, int stackSize, CreativeTabs tab, String itemInfo, int recoveryDose, float saturation, List<PotionEffectConstruction> potionEffects) {
        return new ItemFoodBase(name, stackSize, tab, itemInfo, recoveryDose, saturation, false, potionEffects);
    }

    //添加物品描述信息
    @Override
    public void addInformation(@Nonnull ItemStack stack, @Nullable World worldIn, @Nonnull List<String> tooltip, @Nonnull ITooltipFlag flagIn) {
        if (worldIn != null && !worldIn.isRemote) {
            tooltip.add(itemInfo);
        }
    }

    //添加实用后的效果
    @Override
    protected void onFoodEaten(@Nonnull ItemStack stack, World worldIn, @Nonnull EntityPlayer player) {
        //逻辑服务端和逻辑客户端好像会调用两次
        System.out.println(worldIn.isRemote);
        if (!worldIn.isRemote) {
            //判断非空
            if (potionEffects != null) {
                //遍历增加效果
                for (PotionEffectConstruction potionEffect : potionEffects) {
                    //每次调用判断是否需要刷新时长
                    potionEffect.refreshDuration();
                    player.addPotionEffect(potionEffect);
                }
            }
        }
    }

}
