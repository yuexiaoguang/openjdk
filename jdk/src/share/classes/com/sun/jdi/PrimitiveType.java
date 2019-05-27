package com.sun.jdi;

/**
 * The type associated with non-object values in a target VM.
 * Instances of one of the sub-interfaces of this interface will be
 * returned from {@link Value#type} for all {@link PrimitiveValue} objects.
 */
@jdk.Exported
public interface PrimitiveType extends Type {
}
