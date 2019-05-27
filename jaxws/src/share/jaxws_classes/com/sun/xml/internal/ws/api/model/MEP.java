package com.sun.xml.internal.ws.api.model;

/**
 * Constants that represents four message exchange patterns.
 */
public enum MEP {
    REQUEST_RESPONSE(false),
    ONE_WAY(false),
    ASYNC_POLL(true),
    ASYNC_CALLBACK(true);

    /**
     * True for {@link #ASYNC_CALLBACK} and {@link #ASYNC_POLL}.
     */
    public final boolean isAsync;

    MEP(boolean async) {
        isAsync = async;
    }

    public final boolean isOneWay() {
        return this==ONE_WAY;
    }
}
