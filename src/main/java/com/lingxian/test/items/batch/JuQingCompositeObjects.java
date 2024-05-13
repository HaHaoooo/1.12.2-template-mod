package com.lingxian.test.items.batch;

import com.lingxian.test.items.base.ItemFoodBase;
import com.lingxian.test.potion.PotionEffectConstruction;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.MobEffects;
import net.minecraft.item.Item;
import net.minecraft.util.text.TextFormatting;

import java.util.Collections;

/**
 * @since 2024-05-10 21:00   @author jcj  @version 1.0.0
 * Description 巨青物品系列批量物品实例生成
 */
public class JuQingCompositeObjects {

    public static Item getJuQing() {
        return ItemFoodBase.build("ju_qing_stone",
                64,
                CreativeTabs.MISC,
                TextFormatting.GREEN + "这颗石头散发着清脆的力量！看上去很好吃的样子？",
                2,
                2,
                Collections.singletonList(new PotionEffectConstruction(MobEffects.REGENERATION, 30, 1)));
    }
}
