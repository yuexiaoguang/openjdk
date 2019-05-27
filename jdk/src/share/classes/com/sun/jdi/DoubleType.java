package com.sun.jdi;

/**
 * The type of all primitive double values accessed in
 * the target VM. Calls to {@link Value#type} will return an
 * implementor of this interface.
 */
@jdk.Exported
public interface DoubleType extends PrimitiveType {
}
