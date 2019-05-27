package jdk.internal.dynalink;

import jdk.internal.dynalink.linker.GuardingDynamicLinker;

/**
 * Thrown at the invocation if the call site can not be linked by any available {@link GuardingDynamicLinker}.
 */
public class NoSuchDynamicMethodException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    /**
     * Creates a new NoSuchDynamicMethodException
     * @param message the message of the exception.
     */
    public NoSuchDynamicMethodException(String message) {
        super(message);
    }
}
