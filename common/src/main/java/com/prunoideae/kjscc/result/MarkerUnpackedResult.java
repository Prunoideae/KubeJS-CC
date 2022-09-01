package com.prunoideae.kjscc.result;

public class MarkerUnpackedResult {
    private final Object[] results;

    public MarkerUnpackedResult(Object... results) {
        this.results = results;
    }

    public Object[] getResults() {
        return results;
    }
}
