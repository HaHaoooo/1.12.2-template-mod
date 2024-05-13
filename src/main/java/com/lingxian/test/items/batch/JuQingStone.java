package com.lingxian.test.items.batch;

import com.lingxian.test.items.base.ItemFoodBase;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.text.TextFormatting;

import java.util.ArrayList;
import java.util.List;

public class JuQingStone extends ItemFoodBase {
    // 20 ticks == 1 sec
    // 400 ticks == 20 secs

    private static final ArrayList<PotionEffect> effects = new ArrayList<>();

    static {
        effects.add(new PotionEffect(MobEffects.REGENERATION, 400, 0,false,true));
        effects.add(new PotionEffect(MobEffects.SPEED, 400, 0,false,true));
    }

    public static JuQingStone INSTANCE = new JuQingStone(
            TextFormatting.GREEN + "这颗石头散发着清脆的力量！看上去很好吃的样子？",
            2,
            2,
            false,
            effects);

    public JuQingStone(String itemInfo, int recoveryDose, float saturation, boolean isWolfFood, List<PotionEffect> effectList) {
        super("ju_qing_stone", 64, CreativeTabs.MISC, itemInfo, recoveryDose, saturation, isWolfFood, effectList);
    }
}
