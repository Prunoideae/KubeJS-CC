package com.prunoideae.kjscc.fabric;

import com.prunoideae.kjscc.KJSCC;
import net.fabricmc.api.ModInitializer;

public class KJSCCFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        KJSCC.init();
    }
}
