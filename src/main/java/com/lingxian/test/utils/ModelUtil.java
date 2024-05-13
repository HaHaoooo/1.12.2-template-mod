package com.lingxian.test.utils;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;

import java.util.Objects;

public class ModelUtil {

    public static void renderItemModel(Item item, int meta, String id){
        ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(Objects.requireNonNull(item.getRegistryName()), id));
    }
}
