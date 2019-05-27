package jdk.nashorn.internal.runtime.regexp.joni.ast;

import java.util.Set;

import jdk.nashorn.internal.runtime.regexp.joni.WarnCallback;
import jdk.nashorn.internal.runtime.regexp.joni.exception.ErrorMessages;
import jdk.nashorn.internal.runtime.regexp.joni.exception.InternalException;

public final class ConsAltNode extends Node {
    public Node car;
    public ConsAltNode cdr;
    private int type;           // List or Alt

    private ConsAltNode(Node car, ConsAltNode cdr, int type) {
        this.car = car;
        if (car != null) car.parent = this;
        this.cdr = cdr;
        if (cdr != null) cdr.parent = this;

        this.type = type;
    }

    public static ConsAltNode newAltNode(Node left, ConsAltNode right) {
        return new ConsAltNode(left, right, ALT);
    }

    public static ConsAltNode newListNode(Node left, ConsAltNode right) {
        return new ConsAltNode(left, right, LIST);
    }

    public static ConsAltNode listAdd(ConsAltNode list, Node x) {
        ConsAltNode n = newListNode(x, null);

        if (list != null) {
            while (list.cdr != null) {
                list = list.cdr;
            }
            list.setCdr(n);
        }
        return n;
    }

    public void toListNode() {
        type = LIST;
    }

    public void toAltNode() {
        type = ALT;
    }

    @Override
    public int getType() {
        return type;
    }

    @Override
    protected void setChild(Node newChild) {
        car = newChild;
    }

    @Override
    protected Node getChild() {
        return car;
    }

    @Override
    public void swap(Node with) {
        if (cdr != null) {
            cdr.parent = with;
            if (with instanceof ConsAltNode) {
                ConsAltNode withCan = (ConsAltNode)with;
                withCan.cdr.parent = this;
                ConsAltNode tmp = cdr;
                cdr = withCan.cdr;
                withCan.cdr = tmp;
            }
        }
        super.swap(with);
    }

    @Override
    public void verifyTree(Set<Node> set, WarnCallback warnings) {
        if (!set.contains(this)) {
            set.add(this);
            if (car != null) {
                if (car.parent != this) {
                    warnings.warn("broken list car: " + this.getAddressName() + " -> " +  car.getAddressName());
                }
                car.verifyTree(set,warnings);
            }
            if (cdr != null) {
                if (cdr.parent != this) {
                    warnings.warn("broken list cdr: " + this.getAddressName() + " -> " +  cdr.getAddressName());
                }
                cdr.verifyTree(set,warnings);
            }
        }
    }

    public Node setCar(Node ca) {
        car = ca;
        ca.parent = this;
        return car;
    }

    public ConsAltNode setCdr(ConsAltNode cd) {
        cdr = cd;
        cd.parent = this;
        return cdr;
    }

    @Override
    public String getName() {
        switch (type) {
        case ALT:
            return "Alt";
        case LIST:
            return "List";
        default:
            throw new InternalException(ErrorMessages.ERR_PARSER_BUG);
        }
    }

    @Override
    public String toString(int level) {
        StringBuilder value = new StringBuilder();
        value.append("\n  car: " + pad(car, level + 1));
        value.append("\n  cdr: " + (cdr == null ? "NULL" : cdr.toString()));

        return value.toString();
    }

}
