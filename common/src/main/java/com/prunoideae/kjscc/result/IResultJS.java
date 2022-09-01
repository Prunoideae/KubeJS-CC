package com.prunoideae.kjscc.result;

import dan200.computercraft.api.lua.MethodResult;

public interface IResultJS {
    Object getConvertedResult();

    default MethodResult getResult() {
        return MethodResult.of(getConvertedResult());
    }
}
