package com.lingxian.test.items;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public class Coin extends ItemBase {

    public Coin(String name, int stackSize, CreativeTabs tab) {
        super(name, stackSize, tab);
    }

    @Nonnull
    @Override
    public ActionResult<ItemStack> onItemRightClick(@Nonnull World worldIn, @Nonnull EntityPlayer playerIn, @Nonnull EnumHand handIn) {
        if (!worldIn.isRemote){
            BlockPos blockPos = playerIn.getPosition().down();
            Block block = worldIn.getBlockState(blockPos).getBlock();
            if (block == Blocks.GOLD_BLOCK){
                worldIn.setBlockState(blockPos, Blocks.DIAMOND_BLOCK.getDefaultState());
            }
        }
        return super.onItemRightClick(worldIn, playerIn, handIn);
    }
}
