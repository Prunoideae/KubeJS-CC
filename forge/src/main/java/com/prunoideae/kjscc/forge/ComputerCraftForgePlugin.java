package com.prunoideae.kjscc.forge;

import com.prunoideae.kjscc.PeripheralRegisterEvent;
import dan200.computercraft.api.ComputerCraftAPI;
import dev.latvian.mods.kubejs.KubeJS;
import dev.latvian.mods.kubejs.KubeJSPlugin;
import dev.latvian.mods.kubejs.script.ScriptType;

import java.util.ArrayList;

public class ComputerCraftForgePlugin extends KubeJSPlugin {
    @Override
    public void afterInit() {
        PeripheralRegisterEvent event = new PeripheralRegisterEvent(new ArrayList<>());
        event.post(ScriptType.STARTUP, "computercraft.peripheral");
        KubeJS.LOGGER.info(event.getPeripherals().toString());
        ComputerCraftAPI.registerPeripheralProvider(new ForgePeripheralProvider(event.getPeripherals()));
    }
}
