/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */
package com.sun.org.apache.xalan.internal.xsltc.compiler;

abstract class RelativeLocationPath extends Expression {
    public abstract int getAxis();
    public abstract void setAxis(int axis);
}
