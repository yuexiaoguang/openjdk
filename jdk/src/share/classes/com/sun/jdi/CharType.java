package com.sun.jdi;

/**
 * The type of all primitive char values accessed in
 * the target VM. Calls to {@link Value#type} will return an
 * implementor of this interface.
 */
@jdk.Exported
public interface CharType extends PrimitiveType {
}
