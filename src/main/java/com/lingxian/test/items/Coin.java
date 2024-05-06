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
import net.minecraft.util.text.TextFormatting;
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

    public Coin(String name, int stackSize, CreativeTabs tab) {
        super(name, stackSize, tab);
    }

    //重写右键点击事件？
    @Nonnull
    @Override
    public ActionResult<ItemStack> onItemRightClick(@Nonnull World worldIn, @Nonnull EntityPlayer playerIn, @Nonnull EnumHand handIn) {
        //todo 警告！World类的isRemote变量名虽然是 "是远程" 的意思，但请不要把它理解成是Server多人游戏启动。
        // 因为这个属性在被赋值时调用者的复制是反的。Client调用时会把它赋值为true
        //所以这里只有Server时启动才会能够使用
        if (!worldIn.isRemote) {
            start = !start;
            playerIn.sendMessage(new TextComponentString(start ? "彩虹模式启动！" : "彩虹模式关闭"));
        }
        return super.onItemRightClick(worldIn, playerIn, handIn);
    }

    @SubscribeEvent
    public void onTick(TickEvent.PlayerTickEvent event) {
        EntityPlayer playerIn = event.player;
        if (start) {
            ticks++;
            if (ticks >= 20) {
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
        if (start) {
            String rainbowText = "彩虹之力！！";
            StringBuilder result = new StringBuilder();
            for (int i = 0; i < rainbowText.length(); i++) {
                char c = rainbowText.charAt(i);
                String coloredChar = setRainBowColor(c, i);
                result.append(coloredChar);
            }
            tooltip.add(result.toString());
        } else {
            tooltip.add("右键后脚下会出现彩虹哦！");
        }
        super.addInformation(stack, worldIn, tooltip, flagIn);
    }

    private String setRainBowColor(char text, int index) {
        int colorIndex = (ticks / 5 + index) % 16;
        TextFormatting formatting = TextFormatting.fromColorIndex(colorIndex);
        return formatting + String.valueOf(text);
    }
}
