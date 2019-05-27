package jdk.nashorn.internal.runtime;

import static jdk.nashorn.internal.runtime.ECMAErrors.typeError;
import static jdk.nashorn.internal.lookup.Lookup.MH;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import jdk.internal.dynalink.CallSiteDescriptor;
import jdk.internal.dynalink.linker.GuardedInvocation;
import jdk.internal.dynalink.support.CallSiteDescriptorFactory;
import jdk.internal.dynalink.support.Guards;

/**
 * Unique instance of this class is used to represent JavaScript undefined.
 */
public final class Undefined extends DefaultPropertyAccess {

    private Undefined() {
    }

    private static final Undefined UNDEFINED = new Undefined();
    private static final Undefined EMPTY     = new Undefined();

    // Guard used for indexed property access/set on the Undefined instance
    private static final MethodHandle UNDEFINED_GUARD = Guards.getIdentityGuard(UNDEFINED);

    /**
     * Get the value of {@code undefined}, this is represented as a global singleton
     * instance of this class. It can always be reference compared
     *
     * @return the undefined object
     */
    public static Undefined getUndefined() {
        return UNDEFINED;
    }

    /**
     * Get the value of {@code empty}. This is represented as a global singleton
     * instanceof this class. It can always be reference compared.
     * <p>
     * We need empty to differentiate behavior in things like array iterators
     * <p>
     * @return the empty object
     */
    public static Undefined getEmpty() {
        return EMPTY;
    }

    /**
     * Get the class name of Undefined
     * @return "Undefined"
     */
    @SuppressWarnings("static-method")
    public String getClassName() {
        return "Undefined";
    }

    @Override
    public String toString() {
        return "undefined";
    }

    /**
     * Lookup the appropriate method for an invoke dynamic call.
     * @param desc The invoke dynamic callsite descriptor.
     * @return GuardedInvocation to be invoked at call site.
     */
    public static GuardedInvocation lookup(final CallSiteDescriptor desc) {
        final String operator = CallSiteDescriptorFactory.tokenizeOperators(desc).get(0);

        switch (operator) {
        case "new":
        case "call":
            throw lookupTypeError("cant.call.undefined", desc);
        case "callMethod":
            throw lookupTypeError("cant.read.property.of.undefined", desc);
        // NOTE: we support getElem and setItem as JavaScript doesn't distinguish items from properties. Nashorn itself
        // emits "dyn:getProp:identifier" for "<expr>.<identifier>" and "dyn:getElem" for "<expr>[<expr>]", but we are
        // more flexible here and dispatch not on operation name (getProp vs. getElem), but rather on whether the
        // operation has an associated name or not.
        case "getProp":
        case "getElem":
        case "getMethod":
            if (desc.getNameTokenCount() < 3) {
                return findGetIndexMethod(desc);
            }
            throw lookupTypeError("cant.read.property.of.undefined", desc);
        case "setProp":
        case "setElem":
            if (desc.getNameTokenCount() < 3) {
                return findSetIndexMethod(desc);
            }
            throw lookupTypeError("cant.set.property.of.undefined", desc);
        default:
            break;
        }

        return null;
    }

    private static ECMAException lookupTypeError(final String msg, final CallSiteDescriptor desc) {
        return typeError(msg, desc.getNameTokenCount() > 2 ? desc.getNameToken(2) : null);
    }

    /**
     * Find the appropriate GETINDEX method for an invoke dynamic call.
     * @param desc The invoke dynamic callsite descriptor
     * @param args arguments
     * @return GuardedInvocation to be invoked at call site.
     */
    private static GuardedInvocation findGetIndexMethod(final CallSiteDescriptor desc, final Object... args) {
        final MethodType callType  = desc.getMethodType();
        final Class<?> returnClass = callType.returnType();
        final Class<?> keyClass    = callType.parameterType(1);

        String name = "get";
        if (returnClass.isPrimitive()) {
            //turn e.g. get with a double into getDouble
            final String returnTypeName = returnClass.getName();
            name += Character.toUpperCase(returnTypeName.charAt(0)) + returnTypeName.substring(1, returnTypeName.length());
        }
        MethodHandle methodHandle = findOwnMH(name, returnClass, keyClass);
        methodHandle = MH.asType(methodHandle, methodHandle.type().changeParameterType(0, Object.class));

        return new GuardedInvocation(methodHandle, UNDEFINED_GUARD);
    }

    /**
     * Find the appropriate SETINDEX method for an invoke dynamic call.
     * @param desc The invoke dynamic callsite descriptor
     * @return GuardedInvocation to be invoked at call site.
     */
    private static GuardedInvocation findSetIndexMethod(final CallSiteDescriptor desc) {
        final MethodType callType   = desc.getMethodType();
        final Class<?>   keyClass   = callType.parameterType(1);
        final Class<?>   valueClass = callType.parameterType(2);

        MethodHandle methodHandle = findOwnMH("set", void.class, keyClass, valueClass, boolean.class);
        methodHandle = MH.asType(methodHandle, methodHandle.type().changeParameterType(0, Object.class));
        methodHandle = MH.insertArguments(methodHandle, 3, false);

        return new GuardedInvocation(methodHandle, UNDEFINED_GUARD);
    }

    @Override
    public Object get(final Object key) {
        throw typeError("cant.read.property.of.undefined", ScriptRuntime.safeToString(key));
    }

    @Override
    public void set(final Object key, final Object value, final boolean strict) {
        throw typeError("cant.set.property.of.undefined", ScriptRuntime.safeToString(key));
    }

    @Override
    public boolean delete(final Object key, final boolean strict) {
        throw typeError("cant.delete.property.of.undefined", ScriptRuntime.safeToString(key));
    }

    @Override
    public boolean has(final Object key) {
        return false;
    }

    @Override
    public boolean hasOwnProperty(final Object key) {
        return false;
    }

    private static MethodHandle findOwnMH(final String name, final Class<?> rtype, final Class<?>... types) {
        return MH.findVirtual(MethodHandles.lookup(), Undefined.class, name, MH.type(rtype, types));
    }
}