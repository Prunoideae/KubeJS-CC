package com.prunoideae.kjscc.forge;

import com.prunoideae.kjscc.DynamicPeripheralJS;
import com.prunoideae.kjscc.PeripheralJS;
import com.prunoideae.kjscc.PeripheralProviderBase;
import dan200.computercraft.api.peripheral.IPeripheral;
import dan200.computercraft.api.peripheral.IPeripheralProvider;
import dev.latvian.mods.kubejs.KubeJS;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ForgePeripheralProvider extends PeripheralProviderBase implements IPeripheralProvider {
    public ForgePeripheralProvider(List<PeripheralJS> peripherals) {
        super(peripherals);
    }

    @NotNull
    @Override
    public LazyOptional<IPeripheral> getPeripheral(@NotNull Level world, @NotNull BlockPos pos, @NotNull Direction side) {
        PeripheralJS peripheral = getPeripheralJS(world.getBlockState(pos)).orElse(null);
        KubeJS.LOGGER.info(pos.toString());
        KubeJS.LOGGER.info(peripherals.toString());
        if (peripheral != null)
            return LazyOptional.of(() -> new DynamicPeripheralJS(peripheral.getType(), world, pos, side, peripheral.getMethods()));
        else
            return LazyOptional.empty();
    }
}
