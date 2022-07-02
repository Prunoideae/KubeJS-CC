package com.prunoideae.kjscc;

import com.mojang.datafixers.util.Pair;
import dev.latvian.mods.kubejs.block.state.BlockStatePredicate;
import dev.latvian.mods.kubejs.event.EventJS;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class PeripheralRegisterEvent extends EventJS {

    public PeripheralRegisterEvent(List<PeripheralJS> peripherals) {
        this.peripherals = peripherals;
    }

    private final List<PeripheralJS> peripherals;

    public List<PeripheralJS> getPeripherals() {
        return peripherals;
    }

    public PeripheralJS registerPeripheral(String type, BlockStatePredicate block) {
        PeripheralJS peripheral = new PeripheralJS(block, type, new ArrayList<>());
        peripherals.add(peripheral);
        return peripheral;
    }
}
