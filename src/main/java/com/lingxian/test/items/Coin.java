package com.lingxian.test.items;

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
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

public class Coin extends ItemBase {

    private int metaData = -1;
    private int ticks = 0;
    private boolean start = false;
    private int clickTimes = 0;

    public Coin(String name, int stackSize, CreativeTabs tab) {
        super(name, stackSize, tab);
    }

    @Nonnull
    @Override
    public ActionResult<ItemStack> onItemRightClick(@Nonnull World worldIn, @Nonnull EntityPlayer playerIn, @Nonnull EnumHand handIn) {
        if (!worldIn.isRemote) {
            clickTimes++;
            start = clickTimes % 2 == 0;
            if (start){
                playerIn.sendMessage(new TextComponentString("彩虹模式启动！"));
            } else {
                playerIn.sendMessage(new TextComponentString("彩虹模式关闭！"));
            }
        }
        return super.onItemRightClick(worldIn, playerIn, handIn);
    }

    @SubscribeEvent
    public void onTick(TickEvent.PlayerTickEvent event) {
        EntityPlayer playerIn = event.player;
        if (start) {
            ticks++;
            if (ticks >= 10) {
                ticks = 0;
                if (metaData == 15) {
                    metaData = -1;
                }
                BlockPos blockPos = new BlockPos(playerIn.posX, playerIn.posY - 1, playerIn.posZ);
                playerIn.world.setBlockState(blockPos, Blocks.WOOL.getDefaultState().withProperty(BlockColored.COLOR, EnumDyeColor.byMetadata(metaData)));
                playerIn.world.setBlockState(blockPos.east(), Blocks.WOOL.getDefaultState().withProperty(BlockColored.COLOR, EnumDyeColor.byMetadata(metaData)));
                playerIn.world.setBlockState(blockPos.west(), Blocks.WOOL.getDefaultState().withProperty(BlockColored.COLOR, EnumDyeColor.byMetadata(metaData)));
                playerIn.world.setBlockState(blockPos.north(), Blocks.WOOL.getDefaultState().withProperty(BlockColored.COLOR, EnumDyeColor.byMetadata(metaData)));
                playerIn.world.setBlockState(blockPos.south(), Blocks.WOOL.getDefaultState().withProperty(BlockColored.COLOR, EnumDyeColor.byMetadata(metaData)));
                metaData++;
            }
        }
    }

    @Override
    public void addInformation(@Nonnull ItemStack stack, @Nullable World worldIn, @Nonnull List<String> tooltip, @Nonnull ITooltipFlag flagIn) {
        tooltip.add("右键后脚下会出现彩虹哦！");
        super.addInformation(stack, worldIn, tooltip, flagIn);
    }
}
