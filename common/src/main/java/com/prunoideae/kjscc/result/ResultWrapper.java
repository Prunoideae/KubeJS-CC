package com.prunoideae.kjscc.result;

import dan200.computercraft.api.lua.MethodResult;
import dev.latvian.mods.rhino.NativeArray;
import dev.latvian.mods.rhino.ScriptableObject;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ResultWrapper {
    public static IResultJS getLuaType(Object o) {
        if (o instanceof MarkerUnpackedResult results) {
            return new MultiResultJS(Arrays.stream(results.getResults()).map(ResultWrapper::getLuaType).toArray(IResultJS[]::new));
        }

        if (o instanceof ScriptableObject scriptableObject) {
            if (o instanceof NativeArray array) {
                return new ResultJS(array.stream().map(ResultWrapper::getLuaType).toArray());
            }
            Map<Object, Object> table = new HashMap<>();
            for (Object key : scriptableObject.getIds()) {
                table.put(key, getLuaType(scriptableObject.get(key)));
            }
            return new ResultJS(table);
        }

        return new ResultJS(o);
    }
}
