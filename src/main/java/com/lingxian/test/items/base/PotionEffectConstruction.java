package com.lingxian.test.items.base;

import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

/**
 * @since 2024-05-13 21:40   @author jcj  @version 1.00
 * Description 药水效果封装类，通常用于食物服用后的效果
 */
public class PotionEffectConstruction extends PotionEffect {

    //最大刻（每秒20刻）
    private final int durationMax;

    /**
     * @param potionIn    药水类型
     * @param durationMax 最大药水持续时间（秒）
     * @param potionLevel 药水等级
     * &#064;author:  jcj
     * &#064;since:  2024-05-13 22:20
     */
    public PotionEffectConstruction(Potion potionIn, int durationMax, int potionLevel) {
        super(potionIn, durationMax * 20, potionLevel <= 0 ? 0 : (potionLevel - 1));
        this.durationMax = durationMax * 20;
    }

    //刷新药水效果
    public void refreshDuration() {
        //判断是否需要刷新
        if (durationMax == getDuration()) return;
        //覆盖上去
        this.combine(new PotionEffect(this.getPotion(), durationMax, this.getAmplifier()));
    }

    public int getDurationMax() {
        return durationMax;
    }
}
