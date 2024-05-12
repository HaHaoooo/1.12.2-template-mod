package com.lingxian.test.items.batch;

import com.lingxian.test.items.base.ItemBaseExtension;
import com.lingxian.test.items.base.ItemFoodBase;
import com.lingxian.test.utils.ItemCreateUtils;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.MobEffects;
import net.minecraft.item.Item;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.text.TextFormatting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @since 2024-05-10 21:00   @author jcj  @version 1.0.0
 * Description 巨青物品系列批量物品实例生成
 */
public class JuQingCompositeObjects {

    public static List<Item> getJuQing() {
        ArrayList<Item> items = new ArrayList<>();
        //使用匿名内部类创建物品实例
        items.add(ItemFoodBase.build("ju_qing_stone",
                64,
                CreativeTabs.MISC,
                TextFormatting.GREEN + "这颗石头散发着清脆的力量！看上去很好吃的样子？",
                2,
                2,
                //400tick为20秒
                Collections.singletonList(new PotionEffect(MobEffects.REGENERATION, 20*20, 0,false,true))));
        return items;
    }
}
