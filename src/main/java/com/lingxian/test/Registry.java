package com.lingxian.test;

import com.lingxian.test.entity.*;
import com.lingxian.test.entity.renderer.CoinEntityRenderer;
import com.lingxian.test.entity.renderer.TennisBallRenderer;
import com.lingxian.test.sound.EnumSounds;
import com.lingxian.test.utils.ModelUtil;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
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

import java.util.HashMap;

@Mod.EventBusSubscriber(modid = Test.MODID)
public class Registry {

    public static HashMap<Class<? extends Entity>, IRenderFactory<? extends Entity>> ENTITY_RENDERERS = new HashMap<>();

    // 注册物品
    @SubscribeEvent
    public static void registryItems(RegistryEvent.Register<Item> register) {
        for (Item item : EnumAllModThing.getAllItems()) {
            register.getRegistry().register(item);
        }
    }

    // 注册方块物品
    @SubscribeEvent
    public static void registryItemBlocks(RegistryEvent.Register<Item> register) {
        for (Item itemBlock : EnumAllModThing.getAllItemBlocks()){
            register.getRegistry().register(itemBlock);
        }
    }

    // 注册方块
    @SubscribeEvent
    public static void registryBlocks(RegistryEvent.Register<Block> register) {
        for (Block block : EnumAllModThing.getAllBlocks()){
            register.getRegistry().register(block);
        }
    }

    // 注册模型
    @SubscribeEvent
    public static void registryModels(ModelRegistryEvent event) {
        // Item Model Registry
        for (Item item : EnumAllModThing.getAllItems()) {
            ModelUtil.renderItemModel(item, 0, "inventory");
        }

        // Item Block Model Registry
        for (Item itemBlock : EnumAllModThing.getAllItemBlocks()){
            ModelUtil.renderItemModel(itemBlock, 0, "inventory");
        }

        // Entity Model Registry
        registerEntityRenderingHandler(CoinEntity.class, new CoinEntityRenderer.Factory());
        registerEntityRenderingHandler(TennisBall.class, new TennisBallRenderer.Factory());

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

    public static <T extends Entity> void registerEntityRenderingHandler(Class<T> entityClass, IRenderFactory<? super T> renderFactory) {
        ENTITY_RENDERERS.put(entityClass, renderFactory);
        RenderingRegistry.registerEntityRenderingHandler(entityClass, renderFactory);
    }
}
