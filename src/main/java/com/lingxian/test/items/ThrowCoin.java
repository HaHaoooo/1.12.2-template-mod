package com.lingxian.test.items;

import com.lingxian.test.entity.CoinEntity;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

// 可以扔的金币
public class ThrowCoin extends ItemBase {
    public ThrowCoin(String name, int stackSize, CreativeTabs tab) {
        super(name, stackSize, tab);
    }

    @Nonnull
    @Override
    public ActionResult<ItemStack> onItemRightClick(@Nonnull World worldIn, @Nonnull EntityPlayer playerIn, @Nonnull EnumHand handIn) {
        ItemStack item = playerIn.getHeldItem(handIn);
        worldIn.playSound(null, playerIn.posX, playerIn.posY, playerIn.posZ, SoundEvents.ENTITY_PLAYER_LEVELUP, SoundCategory.NEUTRAL, 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
        if (!worldIn.isRemote){
            CoinEntity entity = new CoinEntity(worldIn, playerIn);
            entity.shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 1.5F, 1.0F);
            worldIn.spawnEntity(entity);
            item.shrink(1);
        }
        return new ActionResult<>(EnumActionResult.SUCCESS, item);
    }
}
