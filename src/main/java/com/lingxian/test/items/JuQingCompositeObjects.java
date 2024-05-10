package com.lingxian.test.items;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

/**
 * @since 2024-05-10 21:00   @author jcj  @version 1.00
 * Description 巨青物品系列批量物品实例生成
 */
public class JuQingCompositeObjects {

    public static List<Item> getJuQing() {
        ArrayList<Item> items = new ArrayList<>();
        //使用匿名内部类创建物品实例
        items.add(new ItemBase("ju_qing_dtone", 64, CreativeTabs.MISC) {
            //增加物品描述
            @Override
            public void addInformation(@Nonnull ItemStack stack, @Nullable World worldIn, @Nonnull List<String> tooltip, @Nonnull ITooltipFlag flagIn) {
                String info = TextFormatting.GREEN + "这颗石头散发着清脆的力量！";
                System.out.println("巨青石描述：" + info);
                tooltip.add(info);
                super.addInformation(stack, worldIn, tooltip, flagIn);
            }
        });
        return items;
    }
}
