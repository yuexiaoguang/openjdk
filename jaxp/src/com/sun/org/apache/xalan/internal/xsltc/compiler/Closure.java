/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */
package com.sun.org.apache.xalan.internal.xsltc.compiler;

public interface Closure {

    /**
     * Returns true if this closure is compiled in an inner class (i.e.
     * if this is a real closure).
     */
    public boolean inInnerClass();

    /**
     * Returns a reference to its parent closure or null if outermost.
     */
    public Closure getParentClosure();

    /**
     * Returns the name of the auxiliary class or null if this predicate
     * is compiled inside the Translet.
     */
    public String getInnerClassName();

    /**
     * Add new variable to the closure.
     */
    public void addVariable(VariableRefBase variableRef);
}
