package jdk.internal.dynalink.beans;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles.Lookup;
import java.lang.invoke.MethodType;

/**
 * A dynamic method bound to exactly one Java method or constructor that is not caller sensitive. Since its target is
 * not caller sensitive, this class pre-caches its method handle and always returns it from the call to
 * {@link #getTarget(Lookup)}. Can be used in general to represents dynamic methods bound to a single method handle,
 * even if that handle is not mapped to a Java method, i.e. as a wrapper around field getters/setters, array element
 * getters/setters, etc.
 */
class SimpleDynamicMethod extends SingleDynamicMethod {
    private final MethodHandle target;

    /**
     * Creates a new simple dynamic method, with a name constructed from the class name, method name, and handle
     * signature.
     *
     * @param target the target method handle
     * @param clazz the class declaring the method
     * @param name the simple name of the method
     */
    SimpleDynamicMethod(MethodHandle target, Class<?> clazz, String name) {
        super(getName(target, clazz, name));
        this.target = target;
    }

    private static String getName(MethodHandle target, Class<?> clazz, String name) {
        return getMethodNameWithSignature(target.type(), getClassAndMethodName(clazz, name));
    }

    @Override
    boolean isVarArgs() {
        return target.isVarargsCollector();
    }

    @Override
    MethodType getMethodType() {
        return target.type();
    }

    @Override
    MethodHandle getTarget(Lookup lookup) {
        return target;
    }
}
