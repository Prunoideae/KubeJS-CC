package com.prunoideae.kjscc.fabric;

import com.prunoideae.kjscc.peripheral.DynamicPeripheralJS;
import com.prunoideae.kjscc.peripheral.PeripheralJS;
import com.prunoideae.kjscc.peripheral.PeripheralProviderBase;
import dan200.computercraft.api.peripheral.IPeripheral;
import dan200.computercraft.api.peripheral.IPeripheralProvider;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class FabricPeripheralProvider extends PeripheralProviderBase implements IPeripheralProvider {
    public FabricPeripheralProvider(List<PeripheralJS> peripherals) {
        super(peripherals);
    }

    @Nullable
    @Override
    public IPeripheral getPeripheral(@NotNull Level world, @NotNull BlockPos pos, @NotNull Direction side) {
        return getPeripheralJS(world.getBlockState(pos))
                .map(peripheralJS -> new DynamicPeripheralJS(peripheralJS.getType(), world, pos, side, peripheralJS.getMethods()))
                .orElse(null);
    }
}
