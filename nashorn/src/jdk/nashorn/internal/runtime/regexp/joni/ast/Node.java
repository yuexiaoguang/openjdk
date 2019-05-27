package jdk.nashorn.internal.runtime.regexp.joni.ast;

import java.util.Set;

import jdk.nashorn.internal.runtime.regexp.joni.Config;
import jdk.nashorn.internal.runtime.regexp.joni.WarnCallback;
import jdk.nashorn.internal.runtime.regexp.joni.constants.NodeType;

public abstract class Node implements NodeType {
    public Node parent;

    public abstract int getType();

    public final int getType2Bit() {
        return 1 << getType();
    }

    protected void setChild(Node tgt){}         // default definition
    protected Node getChild(){return null;}     // default definition

    public void swap(Node with) {
        Node tmp;

        //if (getChild() != null) getChild().parent = with;
        //if (with.getChild() != null) with.getChild().parent = this;

        //tmp = getChild();
        //setChild(with.getChild());
        //with.setChild(tmp);

        if (parent != null) parent.setChild(with);

        if (with.parent != null) with.parent.setChild(this);

        tmp = parent;
        parent = with.parent;
        with.parent = tmp;
    }

    // overridden by ConsAltNode and CallNode
    public void verifyTree(Set<Node> set, WarnCallback warnings) {
        if (!set.contains(this) && getChild() != null) {
            set.add(this);
            if (getChild().parent != this) {
                warnings.warn("broken link to child: " + this.getAddressName() + " -> " + getChild().getAddressName());
            }
            getChild().verifyTree(set, warnings);
        }
    }

    public abstract String getName();
    protected abstract String toString(int level);

    public String getAddressName() {
        return getName() + ":0x" + Integer.toHexString(System.identityHashCode(this));
    }

    @Override
    public final String toString() {
        StringBuilder s = new StringBuilder();
        s.append("<" + getAddressName() + " (" + (parent == null ? "NULL" : parent.getAddressName())  + ")>");
        return s + toString(0);
    }

    protected static String pad(Object value, int level) {
        if (value == null) return "NULL";

        StringBuilder pad = new StringBuilder("  ");
        for (int i=0; i<level; i++) pad.append(pad);

        return value.toString().replace("\n",  "\n" + pad);
    }

    public final boolean isInvalidQuantifier() {
        if (!Config.VANILLA) return false;

        ConsAltNode node;

        switch(getType()) {

        case ANCHOR:
            return true;

        case ENCLOSE:
            /* allow enclosed elements */
            /* return is_invalid_quantifier_target(NENCLOSE(node)->target); */
            break;

        case LIST:
            node = (ConsAltNode)this;
            do {
                if (!node.car.isInvalidQuantifier()) return false;
            } while ((node = node.cdr) != null);
            return false;

        case ALT:
            node = (ConsAltNode)this;
            do {
                if (node.car.isInvalidQuantifier()) return true;
            } while ((node = node.cdr) != null);
            break;

        default:
            break;
        }

        return false;
    }

    public final boolean isAllowedInLookBehind() {
        return (getType2Bit() & ALLOWED_IN_LB) != 0;
    }

    public final boolean isSimple() {
        return (getType2Bit() & SIMPLE) != 0;
    }
}
