package com.sun.xml.internal.bind.v2.runtime.unmarshaller;

/**
 * Pair of {@link Loader} and {@link Receiver}.
 *
 * Used by {@link StructureLoader}.
 */
public final class ChildLoader {
    public final Loader loader;
    public final Receiver receiver;

    public ChildLoader(Loader loader, Receiver receiver) {
        assert loader!=null;
        this.loader = loader;
        this.receiver = receiver;
    }
}
