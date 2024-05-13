package com.lingxian.test.items;

import com.lingxian.test.entity.CoinEntity;
import com.lingxian.test.items.base.ItemBase;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

// 可以扔的金币
public class ThrowCoin extends ItemBase {
    public ThrowCoin(String name, int stackSize, CreativeTabs tab) {
        super(name, stackSize, tab);
    }

    @Nonnull
    @Override
    public ActionResult<ItemStack> onItemRightClick(@Nonnull World worldIn, @Nonnull EntityPlayer playerIn, @Nonnull EnumHand handIn) {
        ItemStack item = playerIn.getHeldItem(handIn);
        if (!worldIn.isRemote){
            CoinEntity entity = new CoinEntity(worldIn, playerIn);
            entity.shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 1.5F, 1.0F);
            worldIn.spawnEntity(entity);
            item.shrink(1);
        }
        return new ActionResult<>(EnumActionResult.SUCCESS, item);
    }

    @Override
    public void addInformation(@Nonnull ItemStack stack, @Nullable World worldIn, @Nonnull List<String> tooltip, @Nonnull ITooltipFlag flagIn) {
        tooltip.add(TextFormatting.GRAY + "投掷攻击力 " + TextFormatting.YELLOW + "+" + CoinEntity.attackDamage);
        super.addInformation(stack, worldIn, tooltip, flagIn);
    }
}
