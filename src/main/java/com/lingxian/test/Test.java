package com.lingxian.test;

import com.lingxian.test.command.ChangeDamage;
import com.lingxian.test.proxy.CommonProxy;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

@Mod(modid = Test.MODID, name = Test.NAME, version = Test.VERSION)
public class Test
{
    public static final String MODID = "test";
    public static final String NAME = "Test";
    public static final String VERSION = "1.0";

    // 客户端和服务端代理
    @SidedProxy(clientSide = "com.lingxian.test.proxy.ClientProxy", serverSide = "com.lingxian.test.proxy.ServerProxy")
    public static CommonProxy proxy;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event){
        proxy.preInit();
    }

    @Mod.EventHandler
    public void Init(FMLInitializationEvent event){
        proxy.init();
    }
    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event){
        proxy.postInit();
    }

    @Mod.EventHandler
    public void ServerInit(FMLServerStartingEvent event) {
        event.registerServerCommand(new ChangeDamage());
    }
}
