package com.prunoideae.kjscc.fabric;

import com.prunoideae.kjscc.PeripheralJS;
import com.prunoideae.kjscc.PeripheralProviderBase;
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
        return (IPeripheral) getPeripheralJS(world.getBlockState(pos)).orElse(null);
    }
}
