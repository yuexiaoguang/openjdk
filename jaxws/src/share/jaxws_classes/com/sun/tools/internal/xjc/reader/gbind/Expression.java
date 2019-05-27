package com.sun.tools.internal.xjc.reader.gbind;

import java.util.Set;

/**
 * This builds content models.
 */
public abstract class Expression {

    /**
     * Computes <tt>LAST(exp)</tt>
     */
    abstract ElementSet lastSet();

    /**
     * True of <tt>\epsilon \in L(exp)</tt>
     */
    abstract boolean isNullable();

    /**
     * Builds up a DAG among {@link Element}s in this expression.
     */
    abstract void buildDAG(ElementSet incoming);

    /**
     * {@link Expression} that represents epsilon, the length-0 string.
     */
    public static final Expression EPSILON = new Expression() {
        ElementSet lastSet() {
            return ElementSet.EMPTY_SET;
        }

        boolean isNullable() {
            return true;
        }

        void buildDAG(ElementSet incoming) {
            // noop
        }

        public String toString() {
            return "-";
        }
    };
}
