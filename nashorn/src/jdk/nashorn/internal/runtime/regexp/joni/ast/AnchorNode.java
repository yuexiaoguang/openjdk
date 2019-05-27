package jdk.nashorn.internal.runtime.regexp.joni.ast;

import jdk.nashorn.internal.runtime.regexp.joni.constants.AnchorType;

public final class AnchorNode extends Node implements AnchorType {
    public int type;
    public Node target;
    public int charLength;

    public AnchorNode(int type) {
        this.type = type;
        charLength = -1;
    }

    @Override
    public int getType() {
        return ANCHOR;
    }

    @Override
    protected void setChild(Node newChild) {
        target = newChild;
    }

    @Override
    protected Node getChild() {
        return target;
    }

    public void setTarget(Node tgt) {
        target = tgt;
        tgt.parent = this;
    }

    @Override
    public String getName() {
        return "Anchor";
    }

    @Override
    public String toString(int level) {
        StringBuilder value = new StringBuilder();
        value.append("\n  type: " + typeToString());
        value.append("\n  target: " + pad(target, level + 1));
        return value.toString();
    }

    public String typeToString() {
        StringBuilder type = new StringBuilder();
        if (isType(BEGIN_BUF)) type.append("BEGIN_BUF ");
        if (isType(BEGIN_LINE)) type.append("BEGIN_LINE ");
        if (isType(BEGIN_POSITION)) type.append("BEGIN_POSITION ");
        if (isType(END_BUF)) type.append("END_BUF ");
        if (isType(SEMI_END_BUF)) type.append("SEMI_END_BUF ");
        if (isType(END_LINE)) type.append("END_LINE ");
        if (isType(WORD_BOUND)) type.append("WORD_BOUND ");
        if (isType(NOT_WORD_BOUND)) type.append("NOT_WORD_BOUND ");
        if (isType(WORD_BEGIN)) type.append("WORD_BEGIN ");
        if (isType(WORD_END)) type.append("WORD_END ");
        if (isType(PREC_READ)) type.append("PREC_READ ");
        if (isType(PREC_READ_NOT)) type.append("PREC_READ_NOT ");
        if (isType(LOOK_BEHIND)) type.append("LOOK_BEHIND ");
        if (isType(LOOK_BEHIND_NOT)) type.append("LOOK_BEHIND_NOT ");
        if (isType(ANYCHAR_STAR)) type.append("ANYCHAR_STAR ");
        if (isType(ANYCHAR_STAR_ML)) type.append("ANYCHAR_STAR_ML ");
        return type.toString();
    }

    private boolean isType(int type) {
        return (this.type & type) != 0;
    }

}
