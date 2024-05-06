package com.lingxian.test;

import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public interface IModel{
    // 基本物品模型接口
    void RegisterModel();
}
