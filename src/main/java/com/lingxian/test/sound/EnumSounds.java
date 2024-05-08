package com.lingxian.test.sound;

import com.lingxian.test.Test;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;

public enum EnumSounds {
    COIN_SHOOT("coin_shoot"),
    RAINBOW("rainbow");

    private final SoundEvent soundEvent;

    EnumSounds(String soundName) {
        ResourceLocation soundLocation = new ResourceLocation(Test.MODID, soundName);
        this.soundEvent = new SoundEvent(soundLocation).setRegistryName(soundLocation);
    }

    public SoundEvent getSoundEvent() {
        return soundEvent;
    }
}
