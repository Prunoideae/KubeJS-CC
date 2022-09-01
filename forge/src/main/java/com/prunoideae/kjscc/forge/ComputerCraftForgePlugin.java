package com.prunoideae.kjscc.forge;

import com.prunoideae.kjscc.ComputerCraftPlugin;
import com.prunoideae.kjscc.PeripheralRegisterEvent;
import com.prunoideae.kjscc.result.MarkerUnpackedResult;
import dan200.computercraft.api.ComputerCraftAPI;
import dev.latvian.mods.kubejs.KubeJSPlugin;
import dev.latvian.mods.kubejs.script.BindingsEvent;
import dev.latvian.mods.kubejs.script.ScriptType;

import java.util.ArrayList;

public class ComputerCraftForgePlugin extends ComputerCraftPlugin {
    @Override
    public void afterInit() {
        PeripheralRegisterEvent event = new PeripheralRegisterEvent(new ArrayList<>());
        event.post(ScriptType.STARTUP, "computercraft.peripheral");
        ComputerCraftAPI.registerPeripheralProvider(new ForgePeripheralProvider(event.getPeripherals()));
    }
}
