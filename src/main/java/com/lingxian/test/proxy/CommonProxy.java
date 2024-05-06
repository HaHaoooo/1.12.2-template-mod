package com.lingxian.test.proxy;

import com.lingxian.test.entity.CoinEntity;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.FMLCommonHandler;

public class CommonProxy {
    public void preInit() {

    }
    public void init() {
        // 金币投掷物实体的跨版本自动修复
        CoinEntity.registerFixesCoinEntity(FMLCommonHandler.instance().getDataFixer());
    }
    public void postInit() {

    }
    public void registerItemRenderer(Item item, int meta, String id) {}
}
