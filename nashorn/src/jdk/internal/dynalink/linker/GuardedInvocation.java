package jdk.internal.dynalink.linker;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.invoke.SwitchPoint;
import java.lang.invoke.WrongMethodTypeException;
import java.util.List;
import jdk.internal.dynalink.CallSiteDescriptor;
import jdk.internal.dynalink.support.Guards;

/**
 * Represents a conditionally valid method handle. It is an immutable triple of an invocation method handle, a guard
 * method handle that defines the applicability of the invocation handle, and a switch point that can be used for
 * external invalidation of the invocation handle. The invocation handle is suitable for invocation if the guard
 * handle returns true for its arguments, and as long as the switch point is not invalidated. Both the guard and the
 * switch point are optional; neither, one, or both can be present.
 */
public class GuardedInvocation {
    private final MethodHandle invocation;
    private final MethodHandle guard;
    private final SwitchPoint switchPoint;

    /**
     * Creates a new guarded invocation.
     *
     * @param invocation the method handle representing the invocation. Must not be null.
     * @param guard the method handle representing the guard. Must have the same method type as the invocation, except
     * it must return boolean. For some useful guards, check out the {@link Guards} class. It can be null to represent
     * an unconditional invocation, although that is unusual.
     * @throws NullPointerException if invocation is null.
     */
    public GuardedInvocation(MethodHandle invocation, MethodHandle guard) {
        this(invocation, guard, null);
    }

    /**
     * Creates a new guarded invocation.
     *
     * @param invocation the method handle representing the invocation. Must not be null.
     * @param guard the method handle representing the guard. Must have the same method type as the invocation, except
     * it must return boolean. For some useful guards, check out the {@link Guards} class. It can be null. If both it
     * and the switch point are null, this represents an unconditional invocation, which is legal but unusual.
     * @param switchPoint the optional switch point that can be used to invalidate this linkage.
     * @throws NullPointerException if invocation is null.
     */
    public GuardedInvocation(MethodHandle invocation, MethodHandle guard, SwitchPoint switchPoint) {
        invocation.getClass(); // NPE check
        this.invocation = invocation;
        this.guard = guard;
        this.switchPoint = switchPoint;
    }

    /**
     * Creates a new guarded invocation.
     *
     * @param invocation the method handle representing the invocation. Must not be null.
     * @param switchPoint the optional switch point that can be used to invalidate this linkage.
     * @param guard the method handle representing the guard. Must have the same method type as the invocation, except
     * it must return boolean. For some useful guards, check out the {@link Guards} class. It can be null. If both it
     * and the switch point are null, this represents an unconditional invocation, which is legal but unusual.
     * @throws NullPointerException if invocation is null.
     */
    public GuardedInvocation(MethodHandle invocation, SwitchPoint switchPoint, MethodHandle guard) {
        this(invocation, guard, switchPoint);
    }
    /**
     * Returns the invocation method handle.
     *
     * @return the invocation method handle. It will never be null.
     */
    public MethodHandle getInvocation() {
        return invocation;
    }

    /**
     * Returns the guard method handle.
     *
     * @return the guard method handle. Can be null.
     */
    public MethodHandle getGuard() {
        return guard;
    }

    /**
     * Returns the switch point that can be used to invalidate the invocation handle.
     *
     * @return the switch point that can be used to invalidate the invocation handle. Can be null.
     */
    public SwitchPoint getSwitchPoint() {
        return switchPoint;
    }

    /**
     * Returns true if and only if this guarded invocation has a switchpoint, and that switchpoint has been invalidated.
     * @return true if and only if this guarded invocation has a switchpoint, and that switchpoint has been invalidated.
     */
    public boolean hasBeenInvalidated() {
        return switchPoint != null && switchPoint.hasBeenInvalidated();
    }

    /**
     * Asserts that the invocation is of the specified type, and the guard (if present) is of the specified type with a
     * boolean return type.
     *
     * @param type the asserted type
     * @throws WrongMethodTypeException if the invocation and the guard are not of the expected method type.
     */
    public void assertType(MethodType type) {
        assertType(invocation, type);
        if(guard != null) {
            assertType(guard, type.changeReturnType(Boolean.TYPE));
        }
    }

    /**
     * Creates a new guarded invocation with different methods, preserving the switch point.
     *
     * @param newInvocation the new invocation
     * @param newGuard the new guard
     * @return a new guarded invocation with the replaced methods and the same switch point as this invocation.
     */
    public GuardedInvocation replaceMethods(MethodHandle newInvocation, MethodHandle newGuard) {
        return new GuardedInvocation(newInvocation, newGuard, switchPoint);
    }

    private GuardedInvocation replaceMethodsOrThis(MethodHandle newInvocation, MethodHandle newGuard) {
        if(newInvocation == invocation && newGuard == guard) {
            return this;
        }
        return replaceMethods(newInvocation, newGuard);
    }

    /**
     * Changes the type of the invocation, as if {@link MethodHandle#asType(MethodType)} was applied to its invocation
     * and its guard, if it has one (with return type changed to boolean, and parameter count potentially truncated for
     * the guard). If the invocation already is of the required type, returns this object.
     * @param newType the new type of the invocation.
     * @return a guarded invocation with the new type applied to it.
     */
    public GuardedInvocation asType(MethodType newType) {
        return replaceMethodsOrThis(invocation.asType(newType), guard == null ? null : Guards.asType(guard, newType));
    }

    /**
     * Changes the type of the invocation, as if {@link LinkerServices#asType(MethodHandle, MethodType)} was applied to
     * its invocation and its guard, if it has one (with return type changed to boolean, and parameter count potentially
     * truncated for the guard). If the invocation already is of the required type, returns this object.
     * @param linkerServices the linker services to use for the conversion
     * @param newType the new type of the invocation.
     * @return a guarded invocation with the new type applied to it.
     */
    public GuardedInvocation asType(LinkerServices linkerServices, MethodType newType) {
        return replaceMethodsOrThis(linkerServices.asType(invocation, newType), guard == null ? null :
            Guards.asType(linkerServices, guard, newType));
    }

    /**
     * Changes the type of the invocation, as if {@link MethodHandle#asType(MethodType)} was applied to its invocation
     * and its guard, if it has one (with return type changed to boolean for guard). If the invocation already is of the
     * required type, returns this object.
     * @param desc a call descriptor whose method type is adapted.
     * @return a guarded invocation with the new type applied to it.
     */
    public GuardedInvocation asType(CallSiteDescriptor desc) {
        return asType(desc.getMethodType());
    }

    /**
     * Applies argument filters to both the invocation and the guard (if there is one).
     * @param pos the position of the first argumen being filtered
     * @param filters the argument filters
     * @return a filtered invocation
     */
    public GuardedInvocation filterArguments(int pos, MethodHandle... filters) {
        return replaceMethods(MethodHandles.filterArguments(invocation, pos, filters), guard == null ? null :
            MethodHandles.filterArguments(guard, pos, filters));
    }

    /**
     * Makes an invocation that drops arguments in both the invocation and the guard (if there is one).
     * @param pos the position of the first argument being dropped
     * @param valueTypes the types of the values being dropped
     * @return an invocation that drops arguments
     */
    public GuardedInvocation dropArguments(int pos, List<Class<?>> valueTypes) {
        return replaceMethods(MethodHandles.dropArguments(invocation, pos, valueTypes), guard == null ? null :
            MethodHandles.dropArguments(guard, pos, valueTypes));
    }

    /**
     * Makes an invocation that drops arguments in both the invocation and the guard (if there is one).
     * @param pos the position of the first argument being dropped
     * @param valueTypes the types of the values being dropped
     * @return an invocation that drops arguments
     */
    public GuardedInvocation dropArguments(int pos, Class<?>... valueTypes) {
        return replaceMethods(MethodHandles.dropArguments(invocation, pos, valueTypes), guard == null ? null :
            MethodHandles.dropArguments(guard, pos, valueTypes));
    }


    /**
     * Composes the invocation, switchpoint, and the guard into a composite method handle that knows how to fall back.
     * @param fallback the fallback method handle in case switchpoint is invalidated or guard returns false.
     * @return a composite method handle.
     */
    public MethodHandle compose(MethodHandle fallback) {
        return compose(fallback, fallback);
    }

    /**
     * Composes the invocation, switchpoint, and the guard into a composite method handle that knows how to fall back.
     * @param switchpointFallback the fallback method handle in case switchpoint is invalidated.
     * @param guardFallback the fallback method handle in case guard returns false.
     * @return a composite method handle.
     */
    public MethodHandle compose(MethodHandle switchpointFallback, MethodHandle guardFallback) {
        final MethodHandle guarded =
                guard == null ? invocation : MethodHandles.guardWithTest(guard, invocation, guardFallback);
        return switchPoint == null ? guarded : switchPoint.guardWithTest(guarded, switchpointFallback);
    }

    private static void assertType(MethodHandle mh, MethodType type) {
        if(!mh.type().equals(type)) {
            throw new WrongMethodTypeException("Expected type: " + type + " actual type: " + mh.type());
        }
    }
}
