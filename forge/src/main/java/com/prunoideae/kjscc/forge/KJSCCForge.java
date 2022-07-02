package com.prunoideae.kjscc.forge;

import com.prunoideae.kjscc.KJSCC;
import dev.architectury.platform.forge.EventBuses;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(KJSCC.MOD_ID)
public class KJSCCForge {
    public KJSCCForge() {
        EventBuses.registerModEventBus(KJSCC.MOD_ID, FMLJavaModLoadingContext.get().getModEventBus());
        KJSCC.init();
    }
}
