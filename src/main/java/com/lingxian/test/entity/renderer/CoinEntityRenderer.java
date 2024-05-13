package com.lingxian.test.entity.renderer;

import com.lingxian.test.entity.CoinEntity;
import com.lingxian.test.items.batch.ThrowCoin;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraft.item.Item;
import net.minecraftforge.fml.client.registry.IRenderFactory;

import javax.annotation.Nonnull;

// 投掷物渲染
public class CoinEntityRenderer extends RenderSnowball<CoinEntity>{
    public CoinEntityRenderer(RenderManager renderManagerIn, Item itemIn, RenderItem itemRendererIn) {
        super(renderManagerIn, itemIn, itemRendererIn);
    }

    @Override
    public void doRender(@Nonnull CoinEntity entity, double x, double y, double z, float entityYaw, float partialTicks) {
        super.doRender(entity, x, y, z, entityYaw, partialTicks);
    }

    public static class Factory implements IRenderFactory<CoinEntity> {
        @Override
        public Render<? super CoinEntity> createRenderFor(RenderManager manager) {
            RenderItem renderItem = Minecraft.getMinecraft().getRenderItem();
            return new CoinEntityRenderer(manager, ThrowCoin.INSTANCE, renderItem);
        }
    }
}
