package com.sun.tools.internal.ws.processor.model;

/**
 * Async WSDLOperation type
 */
public final class AsyncOperationType {

    public static final AsyncOperationType POLLING = new AsyncOperationType();
    public static final AsyncOperationType CALLBACK = new AsyncOperationType();

    private AsyncOperationType() {
    }

}
