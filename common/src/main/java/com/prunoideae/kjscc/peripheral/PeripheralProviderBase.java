package com.prunoideae.kjscc.peripheral;

import com.prunoideae.kjscc.peripheral.PeripheralJS;
import net.minecraft.world.level.block.state.BlockState;

import java.util.List;
import java.util.Optional;

public class PeripheralProviderBase {
    protected final List<PeripheralJS> peripherals;

    public PeripheralProviderBase(List<PeripheralJS> peripherals) {
        this.peripherals = peripherals;
    }

    protected Optional<PeripheralJS> getPeripheralJS(BlockState state) {
        return peripherals.stream().filter(p -> p.test(state)).findFirst();
    }
}
