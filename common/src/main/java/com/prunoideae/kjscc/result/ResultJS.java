package com.prunoideae.kjscc.result;

import com.prunoideae.kjscc.peripheral.DynamicPeripheralJS;
import dev.latvian.mods.rhino.NativeArray;
import dev.latvian.mods.rhino.ScriptableObject;

import java.util.HashMap;
import java.util.Map;

public class ResultJS implements IResultJS {

    private final Object value;

    public ResultJS(Object value) {
        this.value = value;
    }

    @Override
    public Object getConvertedResult() {
        return this.value;
    }

    private static Object jsToLuaType(Object o) {
        if (o instanceof ScriptableObject scriptableObject) {
            if (o instanceof NativeArray array) {
                return array.stream().map(ResultJS::jsToLuaType).toArray();
            }
            Map<Object, Object> table = new HashMap<>();
            for (Object key : scriptableObject.getIds()) {
                table.put(key, jsToLuaType(scriptableObject.get(key)));
            }
            return table;
        }
        return o;
    }
}
