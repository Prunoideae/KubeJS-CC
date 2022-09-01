package com.prunoideae.kjscc;

import com.prunoideae.kjscc.result.IResultJS;
import com.prunoideae.kjscc.result.MarkerUnpackedResult;
import dan200.computercraft.api.lua.IArguments;
import dan200.computercraft.api.lua.ILuaFunction;

import java.util.function.Function;

public class LuaJS {
    public static MarkerUnpackedResult pack(Object... objects) {
        return new MarkerUnpackedResult(objects);
    }

    //Wraps js method into lua method because we need explicit annotation here.
    //Also, post-processing of result is important.
    public static ILuaFunction method(Function<IArguments, Object> method) {
        return arguments -> IResultJS.getLuaType(method.apply(arguments)).getResult();
    }
}
