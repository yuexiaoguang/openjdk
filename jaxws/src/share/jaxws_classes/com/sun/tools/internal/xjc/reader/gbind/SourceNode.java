package com.sun.tools.internal.xjc.reader.gbind;

/**
 * Source node of a graph.
 */
public final class SourceNode extends Element {
    public String toString() {
        return "#source";
    }

    @Override
    boolean isSource() {
        return true;
    }
}
