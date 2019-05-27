package com.sun.jdi;

/**
 * The type of all primitive <code>int</code> values
 * accessed in the target VM. Calls to {@link Value#type} will return an
 * implementor of this interface.
 */
@jdk.Exported
public interface IntegerType extends PrimitiveType {
}
