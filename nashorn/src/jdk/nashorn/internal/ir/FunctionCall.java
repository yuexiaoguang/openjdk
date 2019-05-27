package jdk.nashorn.internal.ir;

/**
 * Interface used by AccessNodes, IndexNodes and IdentNodes to signal
 * that they are function calls
 */
public interface FunctionCall {
    /**
     * Return true if this function call implementor is a function
     *
     * @return true if implements a function call
     */
    public boolean isFunction();
}
