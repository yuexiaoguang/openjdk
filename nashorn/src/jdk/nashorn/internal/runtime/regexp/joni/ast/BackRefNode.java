package jdk.nashorn.internal.runtime.regexp.joni.ast;

import jdk.nashorn.internal.runtime.regexp.joni.ScanEnvironment;

public final class BackRefNode extends StateNode {
    public final int backRef;

    public BackRefNode(int backRef, ScanEnvironment env) {
        this.backRef = backRef;

        if (backRef <= env.numMem && env.memNodes[backRef] == null) {
            setRecursion(); /* /...(\1).../ */
        }
    }

    @Override
    public int getType() {
        return BREF;
    }

    @Override
    public String getName() {
        return "Back Ref";
    }

    @Override
    public String toString(int level) {
        StringBuilder value = new StringBuilder(super.toString(level));
        value.append("\n  back: ").append(backRef);
        return value.toString();
    }
}
