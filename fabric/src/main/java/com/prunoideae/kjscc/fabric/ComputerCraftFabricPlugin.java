package com.prunoideae.kjscc.fabric;

import com.prunoideae.kjscc.PeripheralRegisterEvent;
import dan200.computercraft.api.ComputerCraftAPI;
import dev.latvian.mods.kubejs.KubeJSPlugin;
import dev.latvian.mods.kubejs.script.ScriptType;

import java.util.ArrayList;

public class ComputerCraftFabricPlugin extends KubeJSPlugin {
    @Override
    public void afterInit() {
        PeripheralRegisterEvent event = new PeripheralRegisterEvent(new ArrayList<>());
        event.post(ScriptType.STARTUP, "computercraft.peripheral");
        ComputerCraftAPI.registerPeripheralProvider(new FabricPeripheralProvider(event.getPeripherals()));
    }
}
