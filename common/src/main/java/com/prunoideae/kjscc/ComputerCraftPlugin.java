package com.prunoideae.kjscc;

import dev.latvian.mods.kubejs.KubeJSPlugin;
import dev.latvian.mods.kubejs.script.BindingsEvent;

public class ComputerCraftPlugin extends KubeJSPlugin {
    @Override
    public void addBindings(BindingsEvent event) {
        event.add("Lua", LuaJS.class);
    }
}
