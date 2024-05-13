package com.lingxian.test;

import com.lingxian.test.blocks.batch.SkyCrystalBlock;
import com.lingxian.test.items.batch.Coin;
import com.lingxian.test.items.batch.JuQingCompositeObjects;
import com.lingxian.test.items.batch.TennisBalls;
import com.lingxian.test.items.batch.ThrowCoin;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;

import java.util.*;

public enum EnumAllModThing {
    // ITEMS
    COIN(Coin.INSTANCE),
    JU_QING_COMPOSITE_OBJECT(JuQingCompositeObjects.getJuQing()),
    TENNIS_BALLS(TennisBalls.INSTANCE),
    THROW_COIN(ThrowCoin.INSTANCE),

    // BLOCKS
    SKY_CRYSTAL_BLOCK(SkyCrystalBlock.INSTANCE);


    private final List<Item> itemList = new ArrayList<>();
    private final List<Block> blockList = new ArrayList<>();
    private final List<Item> itemBlockList = new ArrayList<>();

    EnumAllModThing(Item item) {
        this.itemList.add(item);
    }

    EnumAllModThing(Block block) {
        this.blockList.add(block);
        this.itemBlockList.add(new ItemBlock(block).setRegistryName(Objects.requireNonNull(block.getRegistryName())).setUnlocalizedName(block.getUnlocalizedName()));
    }

    public List<Item> getItemList() {
        return itemList;
    }

    public List<Block> getBlockList() {
        return blockList;
    }

    public List<Item> getItemBlockList() {
        return itemBlockList;
    }

    public static List<Item> getAllItems() {
        List<Item> items = new ArrayList<>();
        for (EnumAllModThing thing : EnumAllModThing.values()) {
            items.addAll(thing.getItemList());
        }
        return items;
    }

    public static List<Block> getAllBlocks() {
        List<Block> blocks = new ArrayList<>();
        for (EnumAllModThing thing : EnumAllModThing.values()) {
            blocks.addAll(thing.getBlockList());
        }
        return blocks;
    }

    public static List<Item> getAllItemBlocks() {
        List<Item> itemBlocks = new ArrayList<>();
        for (EnumAllModThing thing : EnumAllModThing.values()) {
            itemBlocks.addAll(thing.getItemBlockList());
        }
        return itemBlocks;
    }
}
