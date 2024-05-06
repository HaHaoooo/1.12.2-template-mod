package com.lingxian.test.entity;

import com.lingxian.test.Registry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraftforge.fml.client.registry.IRenderFactory;

// 投掷物渲染
public class CoinEntityRenderer implements IRenderFactory<CoinEntity> {
    @Override
    public Render<? super CoinEntity> createRenderFor(RenderManager manager) {
        RenderItem renderItem = Minecraft.getMinecraft().getRenderItem();
        return new RenderSnowball<>(manager, Registry.throwCoin, renderItem);
    }
}
