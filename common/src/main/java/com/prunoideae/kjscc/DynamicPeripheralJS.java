package com.prunoideae.kjscc;

import com.mojang.datafixers.util.Pair;
import dan200.computercraft.api.lua.IArguments;
import dan200.computercraft.api.lua.ILuaContext;
import dan200.computercraft.api.lua.LuaException;
import dan200.computercraft.api.lua.MethodResult;
import dan200.computercraft.api.peripheral.IComputerAccess;
import dan200.computercraft.api.peripheral.IDynamicPeripheral;
import dan200.computercraft.api.peripheral.IPeripheral;
import dev.latvian.mods.kubejs.level.BlockContainerJS;
import dev.latvian.mods.kubejs.util.MapJS;
import dev.latvian.mods.rhino.NativeArray;
import dev.latvian.mods.rhino.ScriptableObject;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DynamicPeripheralJS implements IDynamicPeripheral {
    protected final String[] names;
    protected final PeripheralCallback[] methods;
    protected final BlockContainerJS block;
    protected final Direction side;
    protected final String type;

    @FunctionalInterface
    public interface PeripheralCallback {
        Object call(BlockContainerJS block, Direction side, IArguments arguments);
    }

    public DynamicPeripheralJS(String type, Level world, BlockPos pos, Direction side, List<Pair<String, PeripheralCallback>> nameMethods) {
        this.block = new BlockContainerJS(world, pos);
        this.side = side;
        this.type = type;
        List<String> names = new ArrayList<>();
        List<PeripheralCallback> methods = new ArrayList<>();
        nameMethods.forEach(pair -> {
            names.add(pair.getFirst());
            methods.add(pair.getSecond());
        });
        this.names = names.toArray(String[]::new);
        this.methods = methods.toArray(PeripheralCallback[]::new);
    }

    @NotNull
    public String[] getMethodNames() {
        return names;
    }

    @NotNull
    @Override
    public MethodResult callMethod(@NotNull IComputerAccess computer, @NotNull ILuaContext context, int method, @NotNull IArguments arguments) throws LuaException {
        try {
            return MethodResult.of(jsToLuaType(methods[method].call(block, side, arguments)));
        } catch (Exception e) {
            throw new LuaException(e.getMessage());
        }
    }

    private static Object jsToLuaType(Object o) {
        if (o instanceof ScriptableObject scriptableObject) {
            if (o instanceof NativeArray array) {
                return array.stream().map(DynamicPeripheralJS::jsToLuaType).toArray();
            }
            Map<Object, Object> table = new HashMap<>();
            for (Object key : scriptableObject.getIds()) {
                table.put(key, jsToLuaType(scriptableObject.get(key)));
            }
            return table;
        }
        return o;
    }

    @NotNull
    public String getType() {
        return type;
    }

    @Override
    public boolean equals(@Nullable IPeripheral other) {
        return false;
    }
}
