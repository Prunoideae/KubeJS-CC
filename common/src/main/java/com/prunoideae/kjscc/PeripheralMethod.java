package com.prunoideae.kjscc;

public record PeripheralMethod(String type, DynamicPeripheralJS.PeripheralCallback callback, boolean mainThread) {
    public PeripheralMethod(String type, DynamicPeripheralJS.PeripheralCallback callback) {
        this(type, callback, false);
    }
}
