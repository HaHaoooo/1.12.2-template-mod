package com.lingxian.test.items;

import com.lingxian.test.entity.TennisBall;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

public class TennisBalls extends ItemBase {

    public TennisBalls(String name, int stackSize, CreativeTabs tab) {
        super(name, stackSize, tab);
    }

    @Override
    public void addInformation(@Nonnull ItemStack stack, @Nullable World worldIn, @Nonnull List<String> tooltip, @Nonnull ITooltipFlag flagIn) {
        if (stack.hasTagCompound()){
            if (stack.getTagCompound() != null) {
                tooltip.add("网球数量: " + stack.getTagCompound().getInteger("amount"));
            }
        } else {
            tooltip.add("网球数量: 0");
        }
        super.addInformation(stack, worldIn, tooltip, flagIn);
    }

    @SubscribeEvent
    public void onRightClickEntity(PlayerInteractEvent.EntityInteract event) {
        World world = event.getWorld();
        ItemStack itemStack = event.getItemStack();
        Entity entity = event.getTarget();

        if (!world.isRemote){
            if (itemStack.getItem() == this){
                NBTTagCompound itemStackTag = itemStack.getTagCompound();
                if (itemStackTag != null){
                    world.removeEntity(entity);
                    int amount = itemStackTag.getInteger("amount");
                    amount++;
                    itemStackTag.setInteger("amount", amount);
                } else {
                    itemStack.setTagCompound(new NBTTagCompound());
                }
            }
        }
    }

    @SubscribeEvent
    public void onRightClickBlock(PlayerInteractEvent.RightClickBlock event) {
        World world = event.getWorld();
        ItemStack itemStack = event.getItemStack();
        NBTTagCompound itemStackTag = itemStack.getTagCompound();

        Vec3d vec3d = event.getHitVec();
        if (!world.isRemote){
             if (itemStack.getItem() == this){
                 if (itemStackTag != null){
                     int amount = itemStackTag.getInteger("amount");
                     if (amount > 0){
                         TennisBall tennis = new TennisBall(world);
                         tennis.setPosition(vec3d.x, vec3d.y, vec3d.z);
                         world.spawnEntity(tennis);
                         amount--;
                         itemStackTag.setInteger("amount", amount);
                     }
                 }
             }
        }
    }
}
