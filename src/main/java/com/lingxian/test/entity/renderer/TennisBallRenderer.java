package com.lingxian.test.entity.renderer;

import com.lingxian.test.Test;
import com.lingxian.test.entity.TennisBall;
import com.lingxian.test.entity.model.TennisBallModel;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.*;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class TennisBallRenderer extends RenderLiving<TennisBall> {

    public TennisBallRenderer(RenderManager rendermanagerIn, ModelBase modelbaseIn, float shadowsizeIn) {
        super(rendermanagerIn, modelbaseIn, shadowsizeIn);
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(@Nonnull TennisBall entity) {
        return new ResourceLocation(Test.MODID, "textures/entity/tennis_ball.png");
    }

    @Override
    public void doRender(@Nonnull TennisBall entity, double x, double y, double z, float entityYaw, float partialTicks) {
        super.doRender(entity, x, y, z, entityYaw, partialTicks);
    }

    public static class Factory implements IRenderFactory<TennisBall> {
        @Override
        public Render<? super TennisBall> createRenderFor(RenderManager manager) {
            return new TennisBallRenderer(manager, new TennisBallModel(), 0.2F);
        }
    }
}
