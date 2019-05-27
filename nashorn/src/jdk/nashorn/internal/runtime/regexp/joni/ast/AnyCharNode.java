package jdk.nashorn.internal.runtime.regexp.joni.ast;

public final class AnyCharNode extends Node {
    public AnyCharNode(){}

    @Override
    public int getType() {
        return CANY;
    }

    @Override
    public String getName() {
        return "Any Char";
    }

    @Override
    public String toString(int level) {
        String value = "";
        return value;
    }
}
