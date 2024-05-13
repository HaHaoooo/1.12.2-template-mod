package com.lingxian.test;

import com.lingxian.test.blocks.batch.SkyCrystalBlock;
import com.lingxian.test.entity.*;
import com.lingxian.test.entity.renderer.CoinEntityRenderer;
import com.lingxian.test.entity.renderer.TennisBallRenderer;
import com.lingxian.test.items.base.JuQingCompositeObjects;
import com.lingxian.test.items.batch.Coin;
import com.lingxian.test.items.batch.TennisBalls;
import com.lingxian.test.items.batch.ThrowCoin;
import com.lingxian.test.sound.EnumSounds;
import com.lingxian.test.utils.ModelUtil;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.EntityEntryBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

@Mod.EventBusSubscriber(modid = Test.MODID)
public class Registry {
    // 所有东西的整合在这
    public static ArrayList<Item> ITEMS = new ArrayList<>();
    public static ArrayList<Item> ITEM_BLOCKS = new ArrayList<>();
    public static ArrayList<Block> BLOCKS = new ArrayList<>();

    @SuppressWarnings("unsafe")
    public static HashMap<Class<? extends Entity>, IRenderFactory> ENTITY_RENDERERS = new HashMap<>();

    // 注册物品
    @SubscribeEvent
    public static void registryItems(RegistryEvent.Register<Item> register) {
        ITEMS.add(Coin.INSTANCE);
        ITEMS.add(ThrowCoin.INSTANCE);
        ITEMS.add(TennisBalls.INSTANCE);
        //巨青系列
        ITEMS.addAll(JuQingCompositeObjects.getJuQing());
        for (Item item : ITEMS) {
            register.getRegistry().register(item);
        }
    }

    // 注册方块物品
    @SubscribeEvent
    public static void registryItemBlocks(RegistryEvent.Register<Item> register) {
        for (Block block : BLOCKS) {
            ITEM_BLOCKS.add(new ItemBlock(block).setRegistryName(Objects.requireNonNull(block.getRegistryName())).setUnlocalizedName(block.getUnlocalizedName()));
        }
        for (Item item : ITEM_BLOCKS){
            register.getRegistry().register(item);
        }
    }

    // 注册方块
    @SubscribeEvent
    public static void registryBlocks(RegistryEvent.Register<Block> register) {
        BLOCKS.add(SkyCrystalBlock.INSTANCE);
        for (Block block : BLOCKS){
            register.getRegistry().register(block);
        }
    }

    // 注册模型
    @SubscribeEvent
    public static void registryModels(ModelRegistryEvent event) {
        // Item Model Registry
        for (Item item : ITEMS) {
            ModelUtil.renderItemModel(item, 0, "inventory");
        }

        // Item Block Registry
        for (Item item : ITEM_BLOCKS){
            ModelUtil.renderItemModel(item, 0, "inventory");
        }

        // Entity Model Registry
        ENTITY_RENDERERS.put(CoinEntity.class, new CoinEntityRenderer.Factory());
        ENTITY_RENDERERS.put(TennisBall.class, new TennisBallRenderer.Factory());
        ENTITY_RENDERERS.forEach(RenderingRegistry::registerEntityRenderingHandler);
    }

    // 注册实体
    @SubscribeEvent
    public static void registryEntities(RegistryEvent.Register<EntityEntry> event) {
        event.getRegistry().register(EntityEntryBuilder.create()
                .entity(CoinEntity.class)
                .id(new ResourceLocation(Test.MODID, "coin_entity"), 1000)
                .name("test.coin_entity")
                .tracker(64, 10, true)
                .build());
        event.getRegistry().register(EntityEntryBuilder.create()
                .entity(TennisBall.class)
                .id(new ResourceLocation(Test.MODID, "tennis_ball"), 1001)
                .name("test.tennis_ball")
                .tracker(64, 3, true)
                .egg(1, -6684928)
                .build());
    }

    // 注册声音
    @SubscribeEvent
    public static void registrySounds(RegistryEvent.Register<SoundEvent> event) {
        for (EnumSounds enumSounds : EnumSounds.values()){
            event.getRegistry().register(enumSounds.getSoundEvent());
        }
    }
}
