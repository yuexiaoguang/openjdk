package jdk.nashorn.internal.runtime.linker;

import jdk.internal.dynalink.beans.BeansLinker;

/**
 * Represents a Dynalink dynamic method bound to a receiver. Note that objects of this class are just the tuples of
 * a method and a bound this, without any behavior. All the behavior is defined in the {@code BoundDynamicMethodLinker}.
 */
final class BoundDynamicMethod {
    private final Object dynamicMethod;
    private final Object boundThis;

    BoundDynamicMethod(final Object dynamicMethod, final Object boundThis) {
        assert BeansLinker.isDynamicMethod(dynamicMethod);
        this.dynamicMethod = dynamicMethod;
        this.boundThis = boundThis;
    }

    Object getDynamicMethod() {
        return dynamicMethod;
    }

    Object getBoundThis() {
        return boundThis;
    }
}
