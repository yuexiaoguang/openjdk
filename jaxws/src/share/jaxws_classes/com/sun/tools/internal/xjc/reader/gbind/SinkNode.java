package com.sun.tools.internal.xjc.reader.gbind;

/**
 * Sink node of a grpah.
 */
public final class SinkNode extends Element {
    public String toString() {
        return "#sink";
    }

    @Override
    boolean isSink() {
        return true;
    }
}
