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

    public static class PeripheralMethodBuilder {
        private final List<Pair<String, DynamicPeripheralJS.PeripheralCallback>> methods = new ArrayList<>();

        public void register(String methodName, DynamicPeripheralJS.PeripheralCallback callback) {
            methods.add(new Pair<>(methodName, callback));
        }
    }

    private final List<PeripheralJS> peripherals;

    public List<PeripheralJS> getPeripherals() {
        return peripherals;
    }

    public void registerPeripheral(String type, BlockStatePredicate block, Consumer<PeripheralMethodBuilder> methodBuilder) {
        PeripheralMethodBuilder methodToBuild = new PeripheralMethodBuilder();
        methodBuilder.accept(methodToBuild);
        peripherals.add(new PeripheralJS(block, type, methodToBuild.methods));
    }
}
