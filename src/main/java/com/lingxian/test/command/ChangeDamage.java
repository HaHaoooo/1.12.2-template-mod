package com.lingxian.test.command;

import com.lingxian.test.Registry;
import com.lingxian.test.entity.CoinEntity;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.*;
import net.minecraft.util.text.event.HoverEvent;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class ChangeDamage extends CommandBase {

    private static final List<String> itemNames = new ArrayList<>();

    static {
        // 初始化自动补全的物品名称列表
        itemNames.add("test:throw_coin");
    }

    @Nonnull
    @Override
    public String getName() {
        return "damage";
    }

    @Nonnull
    @Override
    public String getUsage(@Nonnull ICommandSender sender) {
        return "/damage <target> <amount>";
    }

    @Override
    public void execute(@Nonnull MinecraftServer server, @Nonnull ICommandSender sender, @Nonnull String[] args) {
        if (args.length < 2) {
            sender.sendMessage(new TextComponentString("Usage: /damage <target> <amount>"));
            return;
        }
        String itemName = args[0];
        int damageAmount = Integer.parseInt(args[1]);
        // 修改物品的属性（可以扔的硬币）
        if (itemName.equals("test:throw_coin")) {
            CoinEntity.attackDamage = damageAmount;
            EntityPlayer player = null;
            if (sender instanceof EntityPlayer){
                player = (EntityPlayer) sender;
            }
            Style style = new Style().setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new TextComponentString(new ItemStack(Registry.throwCoin).getTooltip(player, () -> false).get(1))));
            TextComponentString string1 = new TextComponentString("物品[");
            ITextComponent string2 = new TextComponentString(TextFormatting.YELLOW + Registry.throwCoin.getItemStackDisplayName(new ItemStack(Registry.throwCoin)) + TextFormatting.WHITE).setStyle(style);
            TextComponentString string3 = new TextComponentString("]的投掷伤害已修改为" + damageAmount);
            sender.sendMessage(string1.appendSibling(string2).appendSibling(string3));
        } else {
            sender.sendMessage(new TextComponentString("无效物品名"));
        }

    }

    @Nonnull
    @Override
    public List<String> getTabCompletions(@Nonnull MinecraftServer server, @Nonnull ICommandSender sender, @Nonnull String[] args, @Nullable BlockPos targetPos) {
        if (args.length == 1) { // 第一个参数（物品名称）的补全建议
            return getListOfStringsMatchingLastWord(args, itemNames);
        } else {
            return super.getTabCompletions(server, sender, args, targetPos);
        }
    }
}
