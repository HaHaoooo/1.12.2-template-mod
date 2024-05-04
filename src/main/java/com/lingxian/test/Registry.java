package com.lingxian.test;

import com.lingxian.test.items.Coin;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.ArrayList;

@Mod.EventBusSubscriber(modid = Test.MODID)
public class Registry {
    public static ArrayList<Item> ITEMS = new ArrayList<>();

    public static final Coin coin = new Coin("coin", 16, CreativeTabs.MISC);

    @SubscribeEvent
    public static void registryItems(RegistryEvent.Register<Item> register){
        ITEMS.add(coin);
        for(Item item : ITEMS){
            register.getRegistry().register(item);
        }
    }
    @SubscribeEvent
	public static void onModelRegister(ModelRegistryEvent event) {
		for(Item item: ITEMS){
			if(item instanceof IModel) {
				((IModel)item).RegisterModel();
			}
		}
	}
}
