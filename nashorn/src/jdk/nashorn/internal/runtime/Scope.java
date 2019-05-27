package jdk.nashorn.internal.runtime;

import static jdk.nashorn.internal.codegen.CompilerConstants.interfaceCallNoLookup;

import jdk.nashorn.internal.codegen.CompilerConstants;

/**
 * Interface implemented by {@link ScriptObject}s that act as scope.
 */
public interface Scope {

    /** Method handle that points to {@link Scope#getSplitState}. */
    public static final CompilerConstants.Call GET_SPLIT_STATE = interfaceCallNoLookup(Scope.class, "getSplitState", int.class);

    /** Method handle that points to {@link Scope#setSplitState(int)}. */
    public static final CompilerConstants.Call SET_SPLIT_STATE = interfaceCallNoLookup(Scope.class, "setSplitState", void.class, int.class);

    /**
     * Get the scope's split method state.
     * @return the current state
     */
    public int getSplitState();

    /**
     * Set the scope's split method state.
     * @param state the new state.
     */
    public void setSplitState(int state);
}
