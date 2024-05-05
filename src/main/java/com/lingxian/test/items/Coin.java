package com.lingxian.test.items;

import net.minecraft.block.Block;
import net.minecraft.block.BlockColored;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class Coin extends ItemBase {

    private int metaData = -1;

    public Coin() {
        super("coin", 16, CreativeTabs.MISC);
    }

    @Nonnull
    @Override
    public ActionResult<ItemStack> onItemRightClick(@Nonnull World worldIn, @Nonnull EntityPlayer playerIn, @Nonnull EnumHand handIn) {
        if (!worldIn.isRemote) {
            RayTraceResult result = rayTrace(worldIn, playerIn, false);
            if (result.typeOfHit == RayTraceResult.Type.BLOCK) {
                BlockPos blockPos = result.getBlockPos();
                Block block = worldIn.getBlockState(blockPos).getBlock();
                if (block == Blocks.GOLD_BLOCK) {
                    changeWoolColor(worldIn, blockPos);
                }
            }
        }
        return super.onItemRightClick(worldIn, playerIn, handIn);
    }

    private void changeWoolColor(World world, BlockPos blockPos) {
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                if (metaData == 15) {
                    metaData = -1;
                    timer.cancel();
                }
                world.setBlockState(blockPos, Blocks.WOOL.getDefaultState().withProperty(BlockColored.COLOR, EnumDyeColor.byMetadata(metaData)));
                metaData++;
            }
        };
        // 设置时间为0.1秒一次
        timer.scheduleAtFixedRate(task, 0, 100);
    }

    @Override
    public void addInformation(@Nonnull ItemStack stack, @Nullable World worldIn, @Nonnull List<String> tooltip, @Nonnull ITooltipFlag flagIn) {
        tooltip.add("右键金块会出现彩虹哦！");
        super.addInformation(stack, worldIn, tooltip, flagIn);
    }
}