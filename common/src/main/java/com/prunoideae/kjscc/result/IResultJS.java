package com.prunoideae.kjscc.result;

import dan200.computercraft.api.lua.MethodResult;
import dev.latvian.mods.rhino.NativeArray;
import dev.latvian.mods.rhino.ScriptableObject;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public interface IResultJS {
    static IResultJS getLuaType(Object o) {
        if (o instanceof MarkerUnpackedResult results) {
            return new MultiResultJS(Arrays.stream(results.getResults()).map(IResultJS::getLuaType).toArray(IResultJS[]::new));
        }

        if (o instanceof ScriptableObject scriptableObject) {
            
            if (o instanceof NativeArray array) {
                return new ResultJS(array.stream().map(IResultJS::getLuaType).toArray());
            }
            Map<Object, Object> table = new HashMap<>();
            for (Object key : scriptableObject.getIds()) {
                table.put(key, getLuaType(scriptableObject.get(key)));
            }
            return new ResultJS(table);
        }

        return new ResultJS(o);
    }

    Object getConvertedResult();

    default MethodResult getResult() {
        return MethodResult.of(getConvertedResult());
    }
}
