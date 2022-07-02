package com.prunoideae.kjscc;

import com.mojang.datafixers.util.Pair;
import dev.latvian.mods.kubejs.block.state.BlockStatePredicate;
import net.minecraft.world.level.block.state.BlockState;

import java.util.List;

public class PeripheralJS {
    private final BlockStatePredicate predicate;
    private final String type;
    private final List<Pair<String, DynamicPeripheralJS.PeripheralCallback>> methods;

    public PeripheralJS(BlockStatePredicate state, String type, List<Pair<String, DynamicPeripheralJS.PeripheralCallback>> methods) {
        this.predicate = state;
        this.type = type;
        this.methods = methods;
    }

    public boolean test(BlockState state) {
        return predicate.test(state);
    }

    public String getType() {
        return type;
    }

    public List<Pair<String, DynamicPeripheralJS.PeripheralCallback>> getMethods() {
        return methods;
    }

    public PeripheralJS method(String type, DynamicPeripheralJS.PeripheralCallback method) {
        methods.add(new Pair<>(type, method));
        return this;
    }
}
