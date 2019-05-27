package jdk.nashorn.internal.runtime.regexp.joni.ast;

import jdk.nashorn.internal.runtime.regexp.joni.Option;
import jdk.nashorn.internal.runtime.regexp.joni.constants.EncloseType;

public final class EncloseNode extends StateNode implements EncloseType {

    public final int type;                // enclose type
    public int regNum;
    public int option;
    public Node target;             /* EncloseNode : ENCLOSE_MEMORY */
    public int callAddr;            // AbsAddrType
    public int minLength;           // OnigDistance
    public int maxLength;           // OnigDistance
    public int charLength;
    public int optCount;            // referenced count in optimize_node_left()

    // node_new_enclose / onig_node_new_enclose
    public EncloseNode(int type) {
        this.type = type;
        callAddr = -1;
    }

    // node_new_enclose_memory
    public EncloseNode() {
        this(MEMORY);
    }

    // node_new_option
    public EncloseNode(int option, int i) {
        this(OPTION);
        this.option = option;
    }

    @Override
    public int getType() {
        return ENCLOSE;
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
        return "Enclose";
    }

    @Override
    public String toString(int level) {
        StringBuilder value = new StringBuilder(super.toString(level));
        value.append("\n  type: " + typeToString());
        value.append("\n  regNum: " + regNum);
        value.append("\n  option: " + Option.toString(option));
        value.append("\n  target: " + pad(target, level + 1));
        value.append("\n  callAddr: " + callAddr);
        value.append("\n  minLength: " + minLength);
        value.append("\n  maxLength: " + maxLength);
        value.append("\n  charLength: " + charLength);
        value.append("\n  optCount: " + optCount);

        return value.toString();
    }

    public String typeToString() {
        StringBuilder types = new StringBuilder();
        if (isStopBacktrack()) types.append("STOP_BACKTRACK ");
        if (isMemory()) types.append("MEMORY ");
        if (isOption()) types.append("OPTION ");

        return types.toString();
    }

    public boolean isMemory() {
        return (type & MEMORY) != 0;
    }

    public boolean isOption() {
        return (type & OPTION) != 0;
    }

    public boolean isStopBacktrack() {
        return (type & STOP_BACKTRACK) != 0;
    }

}
